package com.ssafy.logit.model.step_category.entity;

import com.ssafy.logit.exception.WrongCategoryException;

public enum JobCategory {
    DOCUMENT("서류"),
    CODINGTEST("코테"),
    INTERVIEW("면접"),
    ETC("기타");

    private String jobCategory;

    JobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    
    // STring -> enum type 변환
    public static JobCategory nameOf(String jobCategory){
        for(JobCategory category : JobCategory.values()){
            if(category.getJobCategory().equals(jobCategory)){
                return category;
            }
        }
        throw new WrongCategoryException("채용전형 카테고리가 없습니다.");
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }
}
