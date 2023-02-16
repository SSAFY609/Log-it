package com.ssafy.logit.model.step_category.service.category;


import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.exception.WrongCategoryException;
import com.ssafy.logit.model.step_category.dto.category.codingtest.AlgoCategoryStatistics;
import com.ssafy.logit.model.step_category.dto.category.codingtest.CreateCodingTestRequest;
import com.ssafy.logit.model.step_category.dto.category.codingtest.UpdateCodingTestRequest;
import com.ssafy.logit.model.step_category.dto.category.entire.AllCategoryRequest;
import com.ssafy.logit.model.step_category.entity.JobCategory;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.AlgoCategory;
import com.ssafy.logit.model.step_category.entity.category.CodingTest;
import com.ssafy.logit.model.step_category.repository.StepCategoryRepository;
import com.ssafy.logit.model.step_category.repository.category.CodingTestRepository;
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
public class CodingTestService {
    private final StepCategoryRepository stepCategoryRepository;
    private final CodingTestRepository codingTestRepository;

    /**
     * 코딩테스트 객체 생성
     * @param user
     * @param request
     * @return
     */
    @Transactional
    public CodingTest create(User user, CreateCodingTestRequest request){
        StepCategory stepCategory = stepCategoryRepository.findById(request.getStepId()).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepCategory);
        checkCategory(stepCategory);
        CodingTest codingTest = CodingTest.createCodingTest(stepCategory, request.getAlgoContent(), request.getAlgoCategory());
        CodingTest saveCodingTest = codingTestRepository.save(codingTest);
        return saveCodingTest;
    }

    /**
     * 코딩 테스트 객체 조회
     * @param id
     * @return
     */
    public CodingTest get(Long id ){
        CodingTest codingTest = codingTestRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return codingTest;
    }

    /**
     * 코딩 테스트 객체 수정
     * @param user
     * @param id
     * @param request
     * @return
     */
    @Transactional
    public CodingTest update(User user , Long id, UpdateCodingTestRequest request){
        CodingTest codingTest = codingTestRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, codingTest);
        CodingTest updateCodingTest = codingTest.update(request.getAlgoContent(), request.getAlgoCategory());
        return updateCodingTest;
    }

    /**
     * 코딩 테스트 객체 삭제
     * @param user
     * @param id
     */
    @Transactional
    public void delete(User user,Long id){
        CodingTest codingTest = codingTestRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, codingTest);
        codingTestRepository.delete(codingTest);
    }

    /**
     * 코딩테스트 리스트를 받아 생성, 수정을 진행합니다.
     * @param user
     * @param stepCategory
     * @param list
     */
    @Transactional
    public void createUpdateAll(User user, StepCategory stepCategory, List<UpdateCodingTestRequest> list) {
        checkUser(user,stepCategory);
        for (UpdateCodingTestRequest request : list) {
            if(request.getCodingTestId()==null){
                CodingTest document = CodingTest.createCodingTest(stepCategory, request.getAlgoContent(), request.getAlgoCategory());
                codingTestRepository.save(document);
            }else{
                update(user, request.getCodingTestId(), request);
            }
        }
    }

    @Transactional
    public void createUpdateAll(StepCategory stepCategory, List<AllCategoryRequest> list) {
        for (AllCategoryRequest request : list) {
            AlgoCategory category = AlgoCategory.nameOf(request.getCategory());
            if(request.getId()==null){
                CodingTest document = CodingTest.createCodingTest(stepCategory, request.getContent(),category);
                codingTestRepository.save(document);
            }else{
                CodingTest codingTest = codingTestRepository.findById(request.getId()).orElseThrow(NoSuchElementException::new);
                codingTest.update(request.getContent(), category);
            }
        }
    }

    /**
     * 코딩테스트 카테고리 통계
     * @return
     */

    public List<AlgoCategoryStatistics> getAlgoStatics(){
        return codingTestRepository.getGroupByAlgoCategory();
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
