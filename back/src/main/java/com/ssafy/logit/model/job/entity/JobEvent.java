package com.ssafy.logit.model.job.entity;


import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.common.EventDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="jobevent")
public class JobEvent {

    @Id
    @GeneratedValue
    @Column(name="jobevent_id")
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



    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "jobEvent")
    private List<StepCategory> stepCategoryList = new ArrayList<>();



    // 생성 메소드 //
    public static JobEvent createJobEvent(User user,String companyName, String type,LocalDate startDate,LocalDate endDate){
        JobEvent jobEvent = new JobEvent();
        jobEvent.user = user;
        jobEvent.companyName = companyName;
        jobEvent.type = type;
        jobEvent.resultStatus = ResultStatus.INPROGRESS;
        jobEvent.startDate = startDate;
        jobEvent.endDate = endDate;
        return jobEvent;
    }

    // 수정 메소드 //
    public JobEvent updateInfo(String companyName,String type,ResultStatus resultStatus, LocalDate startDate,LocalDate endDate){
        this.companyName = companyName;
        this.type = type;
        this.resultStatus = resultStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }
}
