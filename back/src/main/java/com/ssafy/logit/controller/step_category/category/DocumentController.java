package com.ssafy.logit.controller.step_category.category;


import com.ssafy.logit.model.step_category.dto.category.document.CreateDocumentRequest;
import com.ssafy.logit.model.step_category.dto.category.document.DocumentResponse;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.Document;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
import com.ssafy.logit.model.step_category.service.category.DocumentService;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/step-category/document")
@Tag(name = "Document API", description = "채용전형(서류) API")
public class DocumentController {
    private final DocumentService documentService;
    private final UserService userService;
    private final StepCategoryService stepCategoryService;


    @GetMapping
    public ResponseEntity<DocumentResponse> get(Long id){
        Document document = documentService.get(id);
        DocumentResponse documentResponse = new DocumentResponse(document);
        return new ResponseEntity<>(documentResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DocumentResponse> create(@RequestAttribute String email,
                                                   @RequestBody @Validated CreateDocumentRequest request) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.get(request.getStepId());
        Document document = documentService.create(user, stepCategory, request);
        DocumentResponse documentResponse = new DocumentResponse(document);

        return new ResponseEntity<>(documentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponse> update(@RequestAttribute String email,
                                                   @PathVariable Long id,
                                                   @RequestBody @Validated CreateDocumentRequest request){
        User user = userService.getUser(email).toEntity();
        Document updateDocument = documentService.update(user, id, request);
        DocumentResponse documentResponse = new DocumentResponse(updateDocument);
        return new ResponseEntity<>(documentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestAttribute String email,
                                       @PathVariable Long id){
        User user = userService.getUser(email).toEntity();
        documentService.delete(user,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    private User getUser(String email) {
        return userService.getUserEntity(email);
    }
}
