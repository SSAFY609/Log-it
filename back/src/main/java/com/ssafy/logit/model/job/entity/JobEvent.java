package com.ssafy.logit.model.job.entity;


import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.util.dto.EventDate;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class JobEvent {
    @Id
    @GeneratedValue
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

}
