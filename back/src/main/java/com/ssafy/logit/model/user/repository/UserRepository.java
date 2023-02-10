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

    @Query(value = "select * from users where user_id in (select user_id from growth_user where growth_id = ?1 and type = true)", nativeQuery = true)
    public List<User> findAllUser(long growthId);

    @Query(value = "select * from users where user_id not in (select user_id from growth_user where growth_id = ?1) and user_id != ?2", nativeQuery = true)
    public List<User> findInviteUser(long growthId, long userId);

    @Query(value = "select * from users where user_id not in (select user_id from growth_user where growth_id = ?1) and user_id != ?2 and name like %?3%", nativeQuery = true)
    public List<User> searchInviteUser(long growthId, long userId, String userName);

    @Query(value = "select * from users where email like %?1%", nativeQuery = true)
    public Optional<List<User>> searchEmail(String keyword);

    @Query(value = "select * from users where name like %?1%", nativeQuery = true)
    public Optional<List<User>> searchName(String keyword);
}