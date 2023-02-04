package com.ssafy.logit.model.step_category.service.category;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongCategoryException;
import com.ssafy.logit.model.step_category.dto.category.codingtest.CreateCodingTestRequest;
import com.ssafy.logit.model.step_category.dto.category.codingtest.UpdateCodingTestRequest;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import com.ssafy.logit.model.step_category.repository.StepCategoryRepository;
import com.ssafy.logit.model.step_category.repository.category.CodingTestRepository;
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
public class CodingTestService {
    private final StepCategoryRepository stepCategoryRepository;
    private final CodingTestRepository codingTestRepository;

    @Transactional
    public CodingTest create(User user, CreateCodingTestRequest request){
        StepCategory stepCategory = stepCategoryRepository.findById(request.getStepId()).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepCategory);
        checkCategory(stepCategory);

        CodingTest codingTest = CodingTest.createCodingTest(stepCategory, request.getAlgoContent(), request.getAlgoCategory());
        CodingTest saveCodingTest = codingTestRepository.save(codingTest);
        return saveCodingTest;
    }

    public CodingTest get(Long id ){
        CodingTest codingTest = codingTestRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return codingTest;
    }

    @Transactional
    public CodingTest update(User user , Long id, UpdateCodingTestRequest request){
        CodingTest codingTest = codingTestRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, codingTest);
        CodingTest updateCodingTest = codingTest.update(request.getAlgoContent(), request.getAlgoCategory());
        return updateCodingTest;
    }
    @Transactional
    public void delete(User user,Long id){
        CodingTest codingTest = codingTestRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, codingTest);
        codingTestRepository.delete(codingTest);
    }


    private void checkCategory(StepCategory stepCategory) {
        // 카테고리가 코딩테스트가 맞는지 체크
        if(stepCategory.getJobCategory()!= JobCategory.CODINGTEST){
            throw new WrongCategoryException();
        }
    }

    private void checkUser(User user, CodingTest codingTest) {
        // 해당 유저와 같은지 체크
        if (user != codingTest.getStepCategory().getJobEvent().getUser()) {
            throw new DifferentUserException();
        }
    }

    private void checkUser(User user, StepCategory stepCategory) {
        // 해당 유저와 같은지 체크
        if(user != stepCategory.getJobEvent().getUser()){
            throw new DifferentUserException();
        }
    }

}
