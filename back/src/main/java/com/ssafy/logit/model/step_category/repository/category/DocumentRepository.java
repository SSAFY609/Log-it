package com.ssafy.logit.model.step_category.repository.category;

import com.ssafy.logit.model.step_category.entity.category.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
}
