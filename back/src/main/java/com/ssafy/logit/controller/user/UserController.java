package com.ssafy.logit.controller.user;

import com.ssafy.logit.jwt.JwtUtil;
import com.ssafy.logit.model.user.dto.MailDto;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.service.ImageService;
import com.ssafy.logit.model.user.service.MailService;
import com.ssafy.logit.model.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name="user", description="회원 API")
public class UserController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String UNAUTHORIZED = "unauthorized";
    private static final String DELETED = "이미 삭제됨";
    private static final String NONE = "사용자 없음";
    private static final String PW_FAIL = "비밀번호 틀림";
    private static final String PRESENT = "이미 가입된 사용자";
    private static final String EXPIRED = "token expired";

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MailService mailService;

    @Autowired
    private ImageService imageService;

    // 회원 가입
    @Operation(summary = "회원가입", description = "회원 정보 저장 (JWT 인증x)")
    @PostMapping("/regist")
    public ResponseEntity<String> regist(@RequestBody UserDto userDto) throws Exception {
        try {
            Map<String, Object> resultMap = userService.registUser(userDto);
            if(resultMap.get("result").equals(PRESENT)) { // 이미 가입된 사용자
                return new ResponseEntity<String>(PRESENT, HttpStatus.NOT_ACCEPTABLE);
            } else { // 회원가입 성공
                return new ResponseEntity<String>(SUCCESS, HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    // 로그인
    @Operation(summary = "로그인", description = "회원 정보 저장 (JWT 인증x)")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> resultLogin = userService.login(userDto.getEmail(), userDto.getPw());

        if(resultLogin.get("type").equals(FAIL)) {
            if(resultLogin.get("result").equals(NONE)) { // 사용자 없음
                resultMap.put("result", NONE);
            } else { // 비밀번호 틀림
                resultMap.put("result", PW_FAIL);
            }
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        } else {
            // 생성된 토큰 정보를 클라이언트에게 전달
            resultMap.put("jwt-auth-token", resultLogin.get("authToken"));
            resultMap.put("jwt-refresh-token", resultLogin.get("refreshToken"));
            resultMap.put("id", resultLogin.get("id"));
            resultMap.put("email", resultLogin.get("email"));
            resultMap.put("name", resultLogin.get("name"));
            resultMap.put("pw", resultLogin.get("pw"));
            resultMap.put("flag", resultLogin.get("flag"));
            resultMap.put("studentNo", resultLogin.get("studentNo"));
            resultMap.put("image", resultLogin.get("image"));
            resultMap.put("deleted", resultLogin.get("deleted"));
            resultMap.put("createdTime", resultLogin.get("createdTime"));
            resultMap.put("loginTime", resultLogin.get("loginTime"));

            // 정보 확인을 위해 클라이언트로 전달
            Map<String, Object> authToken_info = jwtUtil.checkAndGetClaims((String)resultLogin.get("refreshToken"));
            resultMap.putAll(authToken_info);

            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
    }

    // 토큰 재발급
    @Operation(summary = "토큰 재발급", description = "refresh 토큰 확인 후 access 토큰 재발급")
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestParam String email) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if(userService.getUser(email) == null) {
                resultMap.put("result", NONE);
            } else {
                String token = userService.getRefreshToken(email);
                jwtUtil.checkAndGetClaims(token);

                // refresh-token 확인 후 auth-token 재발급
                if (token.equals(userService.getRefreshToken(email))) {
                    String authToken = jwtUtil.createAuthToken(email);
                    resultMap.put("jwt-auth-token", authToken);
                    Map<String, Object> info = jwtUtil.checkAndGetClaims(authToken);
                    resultMap.putAll(info);
                }
            }
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("result", EXPIRED);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
    }

    // 로그아웃
    @Operation(summary = "로그아웃", description = "로그아웃하고 토큰 null로 변환")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestAttribute String email) {
        try {
            log.debug("logout : {}", email);
            userService.logout(email);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.OK);
        }
    }

    // 비밀번호 찾기 (임시 비밀번호 발급 후 이메일 전송)
    @Operation(summary = "비밀번호 찾기", description = "임시 비밀번호 발급 후 이메일 전송 (JWT 인증x)")
    @PostMapping("/sendPw")
    public ResponseEntity<String> sendPwEmail(@RequestParam String email) {
        String tmpPw = userService.getTmpPw();
        String result = userService.updatePw(tmpPw, email);

        if(result.equals(SUCCESS)) {
            // 전송
            MailDto mailDto = mailService.createMail(tmpPw, email);
            log.info("생성된 mailDto : {}", mailDto.getToAddress() + mailDto.getFromAddress() + mailDto.getTitle());
            mailService.sendMail(mailDto);

            log.info("임시 비밀번호 전송 완료");
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    // 비밀번호 확인
    @Operation(summary = "비밀번호 확인", description = "비밀번호 변경을 위한 비밀번호 확인")
    @PostMapping("/pw_confirm")
    public ResponseEntity<String> confirmPw(@RequestPart String pw, @RequestAttribute String email) {
        return new ResponseEntity<String>(userService.confirmPw(pw, email), HttpStatus.OK);
    }

    // 비밀번호 수정
    @Operation(summary = "비밀번호 수정", description = "비밀번호 수정")
    @PostMapping("/pw_change")
    public ResponseEntity<String> updatePw(@RequestPart String pw, @RequestAttribute String email) {
        return new ResponseEntity<String>(userService.updatePw(pw, email), HttpStatus.OK);
    }

    // 프로필 수정
    @Operation(summary = "프로필 수정", description = "회원 정보 수정")
    @PostMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto, @RequestAttribute String email) throws Exception {
        try {
            Map<String, Object> resultMap = userService.updateUser(userDto, email);
            if(resultMap.get("result").equals(NONE)) { // 존재하지 않는 사용자
                return new ResponseEntity<String>(NONE, HttpStatus.NOT_ACCEPTABLE);
            } else {
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    // 전체 회원 조회
    @Operation(summary = "전체 회원 조회", description = "전체 회원 조회")
    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAllUser() throws Exception {
        return new ResponseEntity<List<UserDto>>(userService.getAllUser(), HttpStatus.OK);
    }

    // email로 회원 조회
    @Operation(summary = "회원 조회", description = "email로 회원 단건 조회")
    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestAttribute String email) throws Exception {
        return new ResponseEntity<UserDto>(userService.getUser(email), HttpStatus.OK);
    }

    // name으로 회원 조회
    @Operation(summary = "회원 조회", description = "name으로 단건 조회 (JWT 인증x)")
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUser(@RequestParam String name) throws Exception {
        return new ResponseEntity<List<UserDto>>(userService.searchUser(name), HttpStatus.OK);
    }

    // email로 회원 조회
    @Operation(summary = "이메일 중복 검사", description = "email로 이메일 중복 검사")
    @GetMapping("/check")
    public ResponseEntity<String> checkEmail(@RequestParam String email) throws Exception {
        UserDto userDto = userService.getUser(email);
        // 기존에 가입된 사용자와 이메일 중복 검사
        if(userDto == null) {
            return new ResponseEntity<String>(NONE, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(PRESENT, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // 회원 삭제 (실제 삭제x, deleted 1로 업데이트)
    @Operation(summary = "회원 삭제", description = "id로 회원 삭제 (실제 삭제x, deleted 1로 업데이트)")
    @PutMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id, @RequestAttribute String email) throws  Exception {
        try {
            if(userService.getUser(id) == null) { // 없는 회원
                return new ResponseEntity<>(NONE, HttpStatus.NOT_ACCEPTABLE);
            } else {
                // 토큰 사용자 인증 후 회원 삭제
                if(userService.getUser(id).getEmail().equals(email)) {
                    String result = userService.deleteUser(id);
                    if(result.equals(SUCCESS)) { // delete 성공
                        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
                    } else if(result.equals(DELETED)) { // 이미 delete 된 회원
                        return new ResponseEntity<String>(DELETED, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<String>(FAIL, HttpStatus.NOT_ACCEPTABLE);
                    }
                } else {
                    return new ResponseEntity<String>(UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    // 회원 삭제 (실제 삭제) - 회원 이용 불가 !!
    @Operation(summary = "회원 삭제", description = "id로 회원 실제 삭제 (DB에서 해당 회원 삭제)")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> dropUser(@PathVariable Long id) throws Exception {
        boolean result = userService.dropUser(id);
        try {
            if(result) {
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(NONE, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    @Operation(summary = "프로필 이미지 업로드", description = "프로필 이미지 업로드 (기존 프로필 사진 있을 경우 기존 사진 삭제 후 업로드)")
    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestPart MultipartFile multipartFile, @RequestPart String defaultImage, @RequestAttribute String email) throws Exception {
        try {
            if(!multipartFile.isEmpty()) { // 사용자가 프로필 이미지를 파일로 업로드 했을 때
                String fileUrl = imageService.uploadImage(multipartFile, userService.getUser(email));
                if(fileUrl.equals(FAIL)) {
                    return new ResponseEntity<String>(FAIL, HttpStatus.OK);
                } else {
                    log.info("프로필 이미지 업로드 성공 -> url : " + fileUrl);
                    return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
                }
            } else { // 사용자가 프로필 이미지를 default 이미지 중 골랐을 때
                System.out.println(">>>>>>>>>>>> multipartFile : " + multipartFile.isEmpty());
                System.out.println(">>>>>>>>>>>> multipartFile : " + multipartFile.getOriginalFilename());
                System.out.println(">>>>>>>>>>>> multipartFile : " + multipartFile.getContentType());
                System.out.println(">>>>>>>>>>>> multipartFile : " + multipartFile.getBytes());
                String defaultImageNum = imageService.uploadDefaultImage(defaultImage, userService.getUser(email));
                if(defaultImageNum.equals(FAIL)) {
                    return new ResponseEntity<String>(FAIL, HttpStatus.OK);
                } else {
                    log.info("프로필 이미지 업로드 성공 -> num : " + defaultImageNum);
                    return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    @Operation(summary = "프로필 이미지 삭제", description = "프로필 이미지 삭제")
    @DeleteMapping("/deleteImage")
    public ResponseEntity<String> dropImage(@RequestAttribute String email) throws Exception {
        try {
            Long id = userService.getUser(email).getId();
            imageService.dropImage(id);
            log.info("{}의 프로필 이미지 삭제 성공", email);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }
}