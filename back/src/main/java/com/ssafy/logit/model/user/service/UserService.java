package com.ssafy.logit.model.user.service;

import com.ssafy.logit.jwt.JwtUtil;
import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String DELETED = "이미 삭제됨";
    private static final String NONE = "사용자 없음";
    private static final String IS_LOGINED = "이미 로그인된 사용자";
    private static final String PW_FAIL = "비밀번호 틀림";
    private static final String PRESENT = "이미 가입된 사용자";

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public Map<String, Object> registUser(UserDto userDto) {
        Optional<User> user = userRepo.findByEmail(userDto.getEmail());
        Map<String, Object> resultMap = new HashMap<>();
        if(user.isPresent()) {
            System.out.println("regist : 이미 가입된 사용자");
            resultMap.put("result", PRESENT);
        } else {
            System.out.println("===== registUser =====");
            userDto.setPw(passwordEncoder.encode(userDto.getPw())); // 비밀번호 암호화
            userRepo.save(userDto.toEntity());
            resultMap.put("result", userDto);
        }
        return resultMap;
    }

    // 로그인
    public Map<String, Object> login(String email, String pw) {
        Optional<User> user = userRepo.findByEmail(email);

        // 해당 email의 회원이 존재하며, 입력받은 비밀번호가 db에 저장된 비밀번호(암호화된)와 matches 되면 로그인
        Map<String, Object> result = new HashMap<>();
        if(user.isPresent()) {
            UserDto userDto = user.get().toDto();
            if(!passwordEncoder.matches(pw, user.get().getPw())) {
                result.put("type", FAIL);
                result.put("result", PW_FAIL);
                System.out.println("login : 비밀번호 틀림");
            } else {
                // 인증 성공 시 auth-token과 refresh-token 함께 발급
                System.out.println("===== login =====");
                String authToken = jwtUtil.createAuthToken(email);
                String refreshToken = jwtUtil.createRefreshToken();
                saveRefreshToken(email, refreshToken);

                userDto.setRefreshToken(refreshToken);
                userDto.setAuthToken(authToken);
                result.put("type", SUCCESS);
                result.put("result", userDto);
                result.put("refreshToken", refreshToken);
                result.put("authToken", authToken);
                result.put("id", userDto.getId());
                result.put("name", userDto.getName());
                result.put("pw", userDto.getPw());
                result.put("flag", userDto.getFlag());
                result.put("student_no", userDto.getStudentNo());
                result.put("image", userDto.getImage());
                result.put("deleted", userDto.isDeleted());
                result.put("created_time", userDto.getCreatedTime());
                result.put("login_time", userDto.getLoginTime());
            }
        } else {
            result.put("type", FAIL);
            result.put("result", NONE);
            System.out.println("login : " + email + "에 해당하는 사용자 없음");
        }
        return result;
    }

    // refreshToken 저장
    @Transactional
    public void saveRefreshToken(String email, String refreshToken) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            System.out.println("===== saveRefreshToken =====");
            UserDto userDto = user.get().toDto();
            userDto.setRefreshToken(refreshToken);
            userRepo.save(userDto.toEntity());
       } else {
            System.out.println("saveRefreshToken : " + email + "에 해당하는 사용자 없음");
        }
    }

    // 로그아웃
    @Transactional
    public void logout(String email) {
        System.out.println("===== logout =====");
        if(userRepo.findByEmail(email).isPresent()) {
            saveRefreshToken(email, null);
        } else {
            System.out.println("logout : " + email + "에 해당하는 사용자 없음");
        }
    }

    // 토큰 재발급
    public String getRefreshToken(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            System.out.println("===== getRefreshToken =====");
            return user.get().toDto().getRefreshToken();
        } else {
            System.out.println("getRefreshToken : " + email + "에 해당하는 사용자 없음");
            return null;
        }
    }

    // 임시 비밀번호 생성
    public String getTmpPw() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String pw = "";

        // 문자 배열 길이의 값을 랜덤으로 10개 뽑아 조합
        int idx = 0;
        for(int i = 0; i < 10; i++) {
            idx = (int)(charSet.length * Math.random());
            pw += charSet[idx];
        }

        System.out.println("===== getTmpPw =====");
        return pw;
    }

    // 비밀번호 확인
    public String confirmPw(String pw, String email) {
        Optional<User> user = userRepo.findByEmail(email);

//        // 암호화 (입력받은 pw를 인코딩한 값이 기존에 저장된 값과 같은지 확인하기 위함)
//        String encodingPw = passwordEncoder.encode(pw);

        // 입력받은 비밀번호가 db에 저장된 비밀번호(암호화된)와 matches 되면 success
        UserDto userDto = user.get().toDto();
        if (passwordEncoder.matches(pw, userDto.getPw())) {
            return SUCCESS;
        }
        return PW_FAIL;
    }

    // 비밀번호 수정
    @Transactional
    public String updatePw(String pw, String email) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()) {
            UserDto userDto = user.get().toDto();
            userDto.setPw(passwordEncoder.encode(pw)); // 비밀번호 암호화
            userRepo.save(userDto.toEntity());
            return SUCCESS;
        } else {
            return NONE;
        }
    }

    // 프로필 수정
    @Transactional
    public Map<String, Object> updateUser(UserDto userDto, String email) {
        Optional<User> user = userRepo.findByEmail(email);
        Map<String, Object> resultMap = new HashMap<>();
        if(user.isPresent()) {
            System.out.println("===== updateUser =====");
            UserDto newUserDto = user.get().toDto();
            newUserDto.setName(userDto.getName());
            newUserDto.setStudentNo(userDto.getStudentNo());
            userRepo.save(newUserDto.toEntity());
            resultMap.put("result", newUserDto);
        } else {
            resultMap.put("result", NONE);
        }
        return resultMap;
    }

    // 회원 다건 조회
    public List<UserDto> getAllUser() {
        List<User> userList = userRepo.findAll();
        if(userList.size() > 0) {
            System.out.println("===== getAllUser =====");
            return userRepo.findAll().stream().map(UserDto::new).collect(Collectors.toList());
        } else  {
            System.out.println("getAllUser : 사용자 없음");
            return null;
        }
    }

    // email로 회원 단건 조회
    public UserDto getUser(String email) {
        if(userRepo.findByEmail(email).isPresent()) {
            System.out.println("===== getUser =====");
            return userRepo.findByEmail(email).get().toDto();
        } else {
            System.out.println("getUser : " + email + "에 해당하는 사용자 없음");
            return null;
        }
    }

    // id로 회원 단건 조회
    public UserDto getUser(long id) {
        if(userRepo.findById(id).isPresent()) {
            System.out.println("===== getUser =====");
            return userRepo.findById(id).get().toDto();
        } else {
            System.out.println("getUser : " + id + "에 해당하는 사용자 없음");
            return null;
        }
    }

    // 이름으로 회원 검색
    public List<UserDto> searchUser(String name) {
        List<User> userList = userRepo.findByName(name);
        if(userList.size() > 0) {
            System.out.println("===== searchUser =====");
            return userList.stream().map(UserDto::new).collect(Collectors.toList());
        } else {
            System.out.println("searchUser : " + name + "에 해당하는 사용자 없음");
            return null;
        }
    }

    // 회원 삭제 (db에서 deleted 속성 변경)
    @Transactional
    public String deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            UserDto userDto = user.get().toDto();
            boolean deletedStatus = userDto.isDeleted();
            if(!deletedStatus) {
                System.out.println("===== deleteUser (회원 삭제) =====");
                userDto.setDeleted(true);
                userRepo.save(userDto.toEntity());
                return SUCCESS;
            } else {
                System.out.println("===== deleteUser (이미 삭제된 회원) =====");
                return DELETED;
            }
        } else {
            System.out.println("deleteUser : " + id + "에 해당하는 사용자 없음");
            return NONE;
        }
    }

    // 회원 삭제 (db에서 삭제)
    @Transactional
    public boolean dropUser(Long id) {
        if(userRepo.findById(id).isPresent()) {
            System.out.println("===== dropUser =====");
            userRepo.deleteById(id);
            return true;
        } else {
            System.out.println("dropUser : " + id + "에 해당하는 사용자 없음");
            return false;
        }
    }

    // 회원 엔티티 반환
    public User getUserEntity(String email){
        return userRepo.findByEmail(email).orElseThrow(NoSuchElementException::new);
    }
}