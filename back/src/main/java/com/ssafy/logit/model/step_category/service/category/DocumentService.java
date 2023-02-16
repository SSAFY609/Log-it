package com.ssafy.logit.model.step_category.service.category;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongCategoryException;
import com.ssafy.logit.model.step_category.dto.category.document.CreateDocumentRequest;
import com.ssafy.logit.model.step_category.dto.category.document.UpdateDocumentRequest;
import com.ssafy.logit.model.step_category.dto.category.entire.AllCategoryRequest;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.Document;
import com.ssafy.logit.model.step_category.repository.category.DocumentRepository;
import com.ssafy.logit.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {
    private final DocumentRepository documentRepository;


    /**
     * request을 이용하여 채용전형(서류)를 생성합니다. (카테고리 체크) (유저체크)
     * @param user
     * @param stepCategory
     * @param request
     * @return
     */

    @Transactional
    public Document create(User user, StepCategory stepCategory, CreateDocumentRequest request){
        checkUser(user, stepCategory);
        checkCategory(stepCategory);
        Document document = Document.create(stepCategory, request.getQuestion(), request.getAnswer());
        Document saveDocument = documentRepository.save(document);

        return saveDocument;
    }


    /**
     * Document을 이용하여 서류를 수정합니다.(유저체크)
     * @param user
     * @param id
     * @param request
     * @return
     */
    @Transactional
    public Document update(User user, Long id, UpdateDocumentRequest request){
        Document document = documentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user,document);
        Document updateDocument = document.update( request.getQuestion(), request.getAnswer());
        return updateDocument;
    }

    /**
     * Document을 이용하여 서류를 삭제합니다.(유저체크)
     * @param user
     * @param id
     */
    @Transactional
    public void delete(User user, Long id){
        Document document = documentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, document);
        documentRepository.delete(document);
    }


    /**
     * 단일 서류를 가져옵니다.
     * @param id
     * @return
     */
    public Document get(Long id){
        Document document = documentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return document;
    }

    /**
     *  서류리스트를 받아 생성, 수정을 진행합니다.
     * @param user
     * @param stepCategory
     * @param list
     * @return
     */
    @Transactional
    public void  createUpdateAll(User user,StepCategory stepCategory, List<UpdateDocumentRequest> list) {
        checkUser(user,stepCategory);
        for (UpdateDocumentRequest request : list) {
            if(request.getDocumentId()==null){
                Document document = Document.create(stepCategory, request.getQuestion(), request.getAnswer());
                documentRepository.save(document);
            }else{
                update(user, request.getDocumentId(), request);
            }
        }
    }

    @Transactional
    public void  createUpdateAll(StepCategory stepCategory, List<AllCategoryRequest> list) {
        for (AllCategoryRequest request : list) {
            if(request.getId()==null){
                Document document = Document.create(stepCategory, request.getQuestion(), request.getAnswer());
                documentRepository.save(document);
            }else{
                Document document = documentRepository.findById(request.getId()).orElseThrow(NoSuchElementException::new);
                document.update(request.getQuestion(), request.getAnswer());
            }
        }
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

