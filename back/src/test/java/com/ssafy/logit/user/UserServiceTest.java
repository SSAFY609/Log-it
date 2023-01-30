package com.ssafy.logit.user;

import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.repository.UserRepository;
import com.ssafy.logit.model.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void regist() {
        UserDto userDto = UserDto.builder()
                            .name("테스트 회원")
                            .email("test@test.com")
                            .pw("1234")
                            .flag(8)
                            .studentNo("0800000")
                            .deleted(false).build();
        when(userRepo.save(any())).thenReturn(userDto.toEntity());

        userService.saveUser(userDto, true);
    }

    @Test
    void getAllUser() {
    }

    @Test
    void getUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void dropUser() {
    }
}