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
    private static final String DELETED = "deleted";
    private static final String NONE = "none";

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto login(String email, String pw) {
        Optional<User> user = userRepo.findByEmail(email);
        System.out.println("user 로그인 정보 !!! : " + user);
        System.out.println("입력한 pw : " + pw);

        // 암호화 (입력받은 pw를 인코딩한 값이 기존에 저장된 값과 같은지 확인하기 위함)
        String encodingPw = passwordEncoder.encode(pw);
        System.out.println("encodingPw !!! : " + encodingPw);

        // 해당 email의 회원이 존재하며, 입력받은 비밀번호가 db에 저장된 비밀번호(암호화된)와 matches 되면 로그인
        if(user.isPresent() && passwordEncoder.matches(pw, user.get().getPw())) {
            // 인증 성공 시 auth-token과 refresh-token 함께 발급
            System.out.println("===== login =====");
            String authToken = jwtUtil.createAuthToken(email);
            String refreshToken = jwtUtil.createRefreshToken();
            saveRefreshToken(email, refreshToken);
            return UserDto.builder().email(email).refreshToken(refreshToken).authToken(authToken).build();
        } else {
            throw new RuntimeException("login : " + email + "에 해당하는 사용자 없음");
        }
    }

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

    @Transactional
    public void logout(String email) {
        System.out.println("===== logout =====");
        if(userRepo.findByEmail(email).isPresent()) {
            saveRefreshToken(email, null);
        } else {
            System.out.println("logout : " + email + "에 해당하는 사용자 없음");
        }
    }

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

    // 회원이 있을 때는 update, 회원이 없을 때는 regist
    // => regist 변수로 판단
    @Transactional
    public UserDto saveUser(UserDto userDto, boolean regist) {
        // 객체 찾기(존재하는지 확인)
        Optional<User> user = userRepo.findByEmail(userDto.getEmail());
        System.out.println(user);
        
        // 비밀번호 암호화
        userDto.setPw(passwordEncoder.encode(userDto.getPw()));
        System.out.println("[saveUser] userDto : " + userDto);
        
        if(user.isPresent() && !regist) { // update
            System.out.println("===== updateUser =====");
            userRepo.save(userDto.updateUser(user.get().getId(), userDto));
        } else { // regist
            System.out.println("===== registUser =====");
            userRepo.save(userDto.toEntity());
        }
        return userDto;
    }

    public List<UserDto> getAllUser() {
        // findAll()의 반환형은 List<User>이므로, stream 사용하여 List<UserDto>로 변환
        System.out.println("===== getAllUser =====");
        return userRepo.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public UserDto getUser(String email) {
        if(userRepo.findByEmail(email).isPresent()) {
            System.out.println("===== getUser =====");
            return userRepo.findByEmail(email).get().toDto();
        } else {
            System.out.println("getUser : " + email + "에 해당하는 사용자 없음");
            return null;
        }
    }

    public UserDto getUser(long id) {
        if(userRepo.findById(id).isPresent()) {
            System.out.println("===== getUser =====");
            return userRepo.findById(id).get().toDto();
        } else {
            System.out.println("getUser : " + id + "에 해당하는 사용자 없음");
            return null;
        }
    }

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

    public User getUserEntity(String email){
        return userRepo.findByEmail(email).orElseThrow(NoSuchElementException::new);
    }
}