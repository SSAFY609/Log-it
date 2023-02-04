package com.ssafy.logit.model.step_category.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.logit.model.common.ResultStatus;
import com.ssafy.logit.model.job.entity.JobEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PRIVATE)
public class StepCategory {

    @Id
    @GeneratedValue
    @Column(name = "step_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobevent_id")
    private JobEvent jobEvent;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate typeDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ResultStatus resultStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private JobCategory jobCategory;

    // 생성 메소드 //
    public static StepCategory createCategory(JobEvent jobEvent,LocalDate typeDate,JobCategory jobCategory){
        StepCategory stepCategory = new StepCategory();
        stepCategory.jobEvent=jobEvent;
        stepCategory.setTypeDate(typeDate);
        stepCategory.setResultStatus(ResultStatus.INPROGRESS);
        stepCategory.setJobCategory(jobCategory);
        return stepCategory;
    }

    // 수정 메소드 //
    public StepCategory updateCategory(LocalDate typeDate,ResultStatus resultStatus){
        this.setTypeDate(typeDate);
        this.setResultStatus(resultStatus);
        return this;
    }


}
