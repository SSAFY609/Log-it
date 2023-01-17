package com.ssafy.logit.model.user.repository;

import com.ssafy.logit.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}