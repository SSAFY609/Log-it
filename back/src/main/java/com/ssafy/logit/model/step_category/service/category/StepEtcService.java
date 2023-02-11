package com.ssafy.logit.model.step_category.service.category;

import com.ssafy.logit.exception.DifferentUserException;
import com.ssafy.logit.model.step_category.dto.category.entire.AllCategoryRequest;
import com.ssafy.logit.model.step_category.dto.category.etc.CreateStepEtcRequest;
import com.ssafy.logit.model.step_category.dto.category.etc.UpdateStepEtcRequest;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.StepEtc;
import com.ssafy.logit.model.step_category.repository.category.StepEtcRepository;
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
public class StepEtcService {
    private final StepEtcRepository stepEtcRepository;

    /**
     * 채용전형(기타) 객체를 생성합니다.
     * @param user
     * @param stepCategory
     * @param request
     * @return
     */
    @Transactional
    public StepEtc create(User user, StepCategory stepCategory, CreateStepEtcRequest request) {
        checkUser(user, stepCategory);
        StepEtc stepEtc = StepEtc.create(stepCategory, request.getContent());
        StepEtc saveStepEtc = stepEtcRepository.save(stepEtc);
        return saveStepEtc;
    }

    /**
     * 채용전형(기타) 객체를 수정합니다.
     * @param user
     * @param id
     * @param request
     * @return
     */
    @Transactional
    public StepEtc update(User user, Long id, UpdateStepEtcRequest request) {
        StepEtc stepEtc = stepEtcRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepEtc);
        StepEtc updateStepEtc = stepEtc.update(request.getContent());
        return updateStepEtc;
    }

    /**
     * 채용전형(기타) 객체를 삭제합니다.
     * @param user
     * @param id
     */
    @Transactional
    public void delete(User user, Long id) {
        StepEtc stepEtc = stepEtcRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepEtc);
        stepEtcRepository.delete(stepEtc);
    }


    /**
     * 채용전형(기타) 객체를 조회합니다.
     * @param id
     * @return
     */
    public StepEtc get(Long id) {
        StepEtc stepEtc = stepEtcRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return stepEtc;
    }

    /**
     * 채용전형(기타) 리스트를 받아 생성, 수정을 진행합니다.
     * @param user
     * @param stepCategory
     * @param list
     */
    @Transactional
    public void createUpdateAll(User user, StepCategory stepCategory, List<UpdateStepEtcRequest> list) {
        checkUser(user, stepCategory);
        for (UpdateStepEtcRequest request : list) {
            if (request.getEtcId() == null) {
                StepEtc stepEtc = StepEtc.create(stepCategory, request.getContent());
                stepEtcRepository.save(stepEtc);
            } else {
                update(user, request.getEtcId(), request);
            }
        }
    }

    @Transactional
    public void createUpdateAll( StepCategory stepCategory, List<AllCategoryRequest> list) {
        for (AllCategoryRequest request : list) {
            if (request.getId() == null) {
                StepEtc stepEtc = StepEtc.create(stepCategory, request.getContent());
                stepEtcRepository.save(stepEtc);
            } else {
                StepEtc stepEtc = stepEtcRepository.findById(request.getId()).orElseThrow(NoSuchElementException::new);
                stepEtc.update(request.getContent());
            }
        }
    }


    // User 체크
    private static void checkUser(User user, StepEtc stepEtc) {
        if (user != stepEtc.getStepCategory().getJobEvent().getUser()) {
            throw new DifferentUserException();
        }
    }

    // User 체크
    private static void checkUser(User user, StepCategory stepCategory) {
        if (user != stepCategory.getJobEvent().getUser()) {
            throw new DifferentUserException();
        }
    }


}
