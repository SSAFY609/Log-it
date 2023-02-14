package com.ssafy.logit.model.job.entity;

public enum JobEventStatus {

    INPROGRESS("진행중"),
    COMPLETE("완료");

    private String value;

    JobEventStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static JobEventStatus nameOf(String value){
        for (JobEventStatus status : JobEventStatus.values()) {
            if(status.getValue().equals(value)){
                return status;
            }
        }
        return JobEventStatus.INPROGRESS;
    }



}
