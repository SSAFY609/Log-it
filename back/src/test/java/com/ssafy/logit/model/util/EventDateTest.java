package com.ssafy.logit.model.util;

import com.ssafy.logit.model.common.EventDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class EventDateTest {

    @Test
    @DisplayName("날짜 유효한 EventDate 생성")
    public void test_생성날짜_유효() throws Exception{
        //given
        LocalDate now = LocalDate.now();
        LocalDate after = now.plusDays(5);
        //when
        EventDate eventDate = EventDate.createEventDate(now, after);

        //then
        Assertions.assertThat(eventDate.getStartDate()).isEqualTo(now);
        Assertions.assertThat(eventDate.getEndDate()).isEqualTo(after);

    }

    @Test
    @DisplayName("종료날짜가 시작날짜보다 빠르다")
    public void test_생성날짜_유효X() throws Exception{
        //given
        LocalDate now = LocalDate.now();
        LocalDate after = now.minusDays(5);
        //when
        EventDate eventDate = EventDate.createEventDate(now, after);

        //then
        Assertions.assertThat(eventDate).isNull();

    }

}