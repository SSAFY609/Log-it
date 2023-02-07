package com.ssafy.logit.model.growth.repository;

import com.ssafy.logit.model.growth.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Optional<Category> findByCategoryName(String name);
}
