package com.ssafy.logit.user;

import com.ssafy.logit.model.user.dto.UserDto;
import com.ssafy.logit.model.user.repository.UserRepository;
import com.ssafy.logit.model.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserService userService;

    @Spy
    private PasswordEncoder mockPasswordEncoder = new MockPasswordEncoder();

    @Test
    void insertUser() {
        // given
        UserDto userDto = UserDto.builder()
                .name("테스트 유저")
                .email("test@gmail.com")
                .pw("1234")
                .image("1").build();

        when(userRepo.save(any())).thenReturn(userDto.toEntity()); // Mock 객체 주입

        // when
        UserDto result = userService.saveUser(userDto, true);

        // then
        verify(userRepo, times(1)).save(any());
        assertThat(result).isEqualTo(userDto);
    }

    @Test
    void getUser() {
        // given
        UserDto userDto = UserDto.builder()
                .email("test@gmail.com")
                .pw("1234").build();

        when(userRepo.findByEmail("test@gmail.com")).thenReturn(Optional.ofNullable(userDto.toEntity()));

        // when
        UserDto result = userService.getUser("test@gmail.com");

        // then
        verify(userRepo, times(2)).findByEmail(any());
        assertThat(result).isEqualTo(userDto);
    }

    @Test
    void updateUser() {
        // given
        UserDto userDto = UserDto.builder()
                .name("테스트 유저")
                .email("test@gmail.com")
                .pw("1234")
                .image("1").build();

        when(userRepo.save(any())).thenReturn(userDto.toEntity()); // Mock 객체 주입

        // when
        userDto.setPw("0000");
        UserDto result = userService.saveUser(userDto, false);

        // then
        verify(userRepo, times(1)).save(any());
        assertThat(result).isEqualTo(userDto);
        assertThat(result.getPw()).isEqualTo("0000");
    }

    @Test
    void dropUser() {
        // given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .email("test@gmail.com")
                .pw("1234").build();
        given(userRepo.findById(anyLong())).willReturn(Optional.of(userDto.toEntity()));

        // when
        boolean result = userService.dropUser(1L);

        // then
        verify(userRepo).deleteById(anyLong());
    }

    // 테스트를 위한 MockPasswordEncoder 구현
    private class MockPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence rawPassword) {
            return new StringBuilder(rawPassword).reverse().toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return encode(rawPassword).equals(encodedPassword);
        }
    }
}