package com.ssafy.logit.model.user.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DateTime {

    @Column(name = "created_time", nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(name = "login_time", nullable = false)
    private LocalDateTime loginTime;

    @PrePersist
    public void prePersist(){
        this.createdTime = LocalDateTime.now();
        this.loginTime = this.createdTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.loginTime = LocalDateTime.now();
    }
}