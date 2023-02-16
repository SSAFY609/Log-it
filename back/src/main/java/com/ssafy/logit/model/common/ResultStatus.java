package com.ssafy.logit.model.common;

public enum ResultStatus {
    FAIL("불합"),
    INPROGRESS("진행"),
    PASS("합격");

    public String value;

    ResultStatus(String value) {
        this.value = value;
    }
    // String -> enum
    public static ResultStatus nameOf(String value){
        for(ResultStatus status : ResultStatus.values()){
            if(status.getValue().equals(value)){
                return status;
            }
        }
        return ResultStatus.INPROGRESS;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
