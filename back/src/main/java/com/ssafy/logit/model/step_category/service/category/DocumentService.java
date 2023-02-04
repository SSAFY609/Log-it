package com.ssafy.logit.model.step_category.service.category;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongCategoryException;
import com.ssafy.logit.model.step_category.dto.category.document.CreateDocumentRequest;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.Document;
import com.ssafy.logit.model.step_category.repository.category.DocumentRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {
    private final DocumentRepository documentRepository;



    @Transactional
    public Document create(User user, StepCategory stepCategory, CreateDocumentRequest request){
        checkUser(user, stepCategory);
        checkCategory(stepCategory);
        // TODO 1개만 생성 가능하게 추가 예정
        Document document = Document.create(stepCategory, request.getQuestion(), request.getContent());
        Document saveDocument = documentRepository.save(document);

        return saveDocument;
    }


    @Transactional
    public Document update(User user, Long id, CreateDocumentRequest request){
        Document document = documentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user,document);
        Document updateDocument = document.update( request.getQuestion(), request.getContent());
        return updateDocument;
    }

    @Transactional
    public void delete(User user, Long id){
        Document document = documentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, document);
        documentRepository.delete(document);
    }


    public Document get(Long id){
        Document document = documentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return document;
    }


    //카테고리 체크
    private void checkCategory(StepCategory stepCategory) {
        if(stepCategory.getJobCategory()!= JobCategory.DOCUMENT){
            throw new WrongCategoryException();
        }
    }

    // User 체크
    private void checkUser(User user, Document document) {
        if(user != document.getStepCategory().getJobEvent().getUser()){
            throw new DifferentUserException();
        }
    }

    // User 체크
    private void checkUser(User user, StepCategory stepCategory) {
        if(user != stepCategory.getJobEvent().getUser()){
            throw new DifferentUserException();
        }
    }


}

