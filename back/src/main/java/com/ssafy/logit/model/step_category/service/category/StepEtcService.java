package com.ssafy.logit.model.step_category.service.category;

import com.ssafy.logit.exception.DifferentUserException;
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

    @Transactional
    public StepEtc create(User user, StepCategory stepCategory, CreateStepEtcRequest request) {
        checkUser(user, stepCategory);
        StepEtc stepEtc = StepEtc.create(stepCategory, request.getContent());
        StepEtc saveStepEtc = stepEtcRepository.save(stepEtc);
        return saveStepEtc;
    }

    @Transactional
    public StepEtc update(User user, Long id, UpdateStepEtcRequest request) {
        StepEtc stepEtc = stepEtcRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepEtc);
        StepEtc updateStepEtc = stepEtc.update(request.getContent());
        return updateStepEtc;
    }

    @Transactional
    public void delete(User user, Long id) {
        StepEtc stepEtc = stepEtcRepository.findById(id).orElseThrow(NoSuchElementException::new);
        checkUser(user, stepEtc);
        stepEtcRepository.delete(stepEtc);
    }


    public StepEtc get(Long id) {
        StepEtc stepEtc = stepEtcRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return stepEtc;
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


    public void createUpdateAll(User user, StepCategory stepCategory, List<UpdateStepEtcRequest> list) {
        checkUser(user,stepCategory);
        for (UpdateStepEtcRequest request : list) {
            if(request.getEtcId()==null){
                StepEtc stepEtc = StepEtc.create(stepCategory, request.getContent());
                stepEtcRepository.save(stepEtc);
            }else{
                update(user, request.getEtcId(), request);
            }
        }
    }
}
