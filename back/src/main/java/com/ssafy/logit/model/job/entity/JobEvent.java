package com.ssafy.logit.model.job.entity;

import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.util.dto.EventDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobEvent {

    @Id
    @GeneratedValue
    @Column(name="job_event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 30)
    private String companyName;

    @Column(length = 30)
    private String type;
    private boolean result;

    @Embedded
    private EventDate eventDate;

    // 생성 메소드 //
    public static JobEvent createJobEvent(User user,String companyName, String type, boolean result,EventDate eventDate){
        JobEvent jobEvent = new JobEvent();
        jobEvent.user = user;
        jobEvent.companyName = companyName;
        jobEvent.type = type;
        jobEvent.result = result;
        jobEvent.eventDate = eventDate;

        return jobEvent;
    }

    // 수정 메소드 //
    public JobEvent updateInfo(String companyName,String type,boolean result, EventDate eventDate){
        this.companyName = companyName;
        this.type = type;
        this.result = result;
        this.eventDate = eventDate;
        return this;
    }
}
