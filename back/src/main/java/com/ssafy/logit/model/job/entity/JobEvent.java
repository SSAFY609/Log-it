package com.ssafy.logit.model.job.entity;


import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.common.EventDate;
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

    @Enumerated(EnumType.STRING)
    private ResultStatus resultStatus;
    @Embedded
    private EventDate eventDate;

    // 생성 메소드 //
    public static JobEvent createJobEvent(User user,String companyName, String type,EventDate eventDate){
        JobEvent jobEvent = new JobEvent();
        jobEvent.user = user;
        jobEvent.companyName = companyName;
        jobEvent.type = type;
        jobEvent.resultStatus = ResultStatus.INPROGRESS;
        jobEvent.eventDate = eventDate;

        return jobEvent;
    }

    // 수정 메소드 //
    public JobEvent updateInfo(String companyName,String type,ResultStatus resultStatus, EventDate eventDate){
        this.companyName = companyName;
        this.type = type;
        this.resultStatus = resultStatus;
        this.eventDate = eventDate;
        return this;
    }
}
