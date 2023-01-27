package com.ssafy.logit.model.user.service;

import com.ssafy.logit.model.user.dto.MailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    private static final String title = "[Log-it] 임시 비밀번호 안내 메일";
    private static final String message = "안녕하세요. Log-it 임시 비밀번호 안내 메일입니다. "
            +"\n" + "회원님의 임시 비밀번호는 아래와 같습니다. 로그인 후 반드시 비밀번호를 변경해주세요."+"\n";
    private static final String fromAddress = "98icecream17@gmail.com";

    // 이메일 생성
    public MailDto createMail(String tmpPw, String email) {

        MailDto mailDto = MailDto.builder()
                .toAddress(email)
                .title(title)
                .message(message + tmpPw)
                .fromAddress(fromAddress)
                .build();

        log.info("메일 생성 완료");
        return mailDto;
    }

    // 이메일 전송
    public void sendMail(MailDto mailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mailDto.getToAddress());
        mailMessage.setSubject(mailDto.getTitle());
        mailMessage.setText(mailDto.getMessage());
        mailMessage.setFrom(mailDto.getFromAddress());
        mailMessage.setReplyTo(mailDto.getFromAddress());

        mailSender.send(mailMessage);
        log.info("메일 전송 완료");
    }
}
