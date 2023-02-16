package com.ssafy.logit.model.step_category.dto.category.interview;


import com.ssafy.logit.model.step_category.entity.category.InterviewCategory;
import com.ssafy.logit.model.step_category.entity.category.Interview;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "채용전형(면접) 응답")
public class InterviewResponse {

    @Schema(description = "면접 id")
    private Long id;

    @Schema(description = "질문")
    private String question;
    @Schema(description = "대답")
    private String answer;

    @Schema(description = "면접 유형")
    private String category;

    // 생성자
    public InterviewResponse(Interview interview){
        this.id = interview.getId();
        this.question = interview.getQuestion();
        this.answer = interview.getAnswer();
        this.category = interview.getInterviewCategory().getValue();
    }


}
