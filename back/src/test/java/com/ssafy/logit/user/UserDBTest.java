package com.ssafy.logit.user;

import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Rollback(false)
public class UserDBTest {

    @Autowired
    UserRepository userRepo;

    // 삽입
    @Test
    @Transactional
    void insert() {
        System.out.println("===== insert =====");
        User user = User.builder()
                        .name("김싸피")
                        .email("ssafy@ssafy.com")
                        .pw("1234")
                        .flag(8)
                        .studentNo("0812345").build();
        System.out.println(user.getName());
        userRepo.save(user);
    }

    // 조회
    @Test
    void select() {
        System.out.println("===== select =====");
        Optional<User> result = userRepo.findById(1L);
        if(result.isPresent()) {
            User user = result.get();
            System.out.println(user.getName());
        }
    }

    // 수정
    @Test
    @Transactional
    void update() {
        System.out.println("===== update =====");
        User user = User.builder()
                        .id(1L)
                        .name("수정된 김싸피")
                        .email("ssafy2@ssafy.com")
                        .pw("1234")
                        .flag(8)
                        .studentNo("0812345").build();
        System.out.println(user.getName());
        userRepo.save(user);
    }

    // 삭제 - 가장 먼저 실행되므로, 데이터 있는 상태에서 테스트 필요
    @Test
    @Transactional
    void delete() {
        System.out.println("===== delete =====");
        userRepo.deleteById(1L);
    }
}
