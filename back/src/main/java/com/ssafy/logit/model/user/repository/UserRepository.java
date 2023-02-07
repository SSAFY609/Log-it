package com.ssafy.logit.model.user.repository;

import com.ssafy.logit.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);

    public List<User> findByName(String name);

    @Query(value = "select * from users where user_id not in (select user_id from growth_user where growth_id = ?1) and user_id != ?2", nativeQuery = true)
    public List<User> findInviteUser(long growthId, long userId);

    @Query(value = "select * from users where user_id not in (select user_id from growth_user where growth_id = ?1) and user_id != ?2 and name like %?3%", nativeQuery = true)
    public List<User> searchInviteUser(long growthId, long userId, String userName);
}