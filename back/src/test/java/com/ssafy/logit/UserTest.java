package com.ssafy.logit;

import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
// @Rollback(false)
public class UserTest {

    @Autowired
    UserRepo userRepo;

    @Test
    void insert() {
        User user = new User();
        user.setName("김싸피");
        user.setEmail("ssafy@ssafy.com");
        user.setPw("1234");
        user.setFlag(8);
        user.setStudent_no("0843613");
        userRepo.save(user);
    }
}
