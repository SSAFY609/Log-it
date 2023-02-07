package com.ssafy.logit.controller.step_category.category;


import com.ssafy.logit.model.common.ResultDto;
import com.ssafy.logit.model.step_category.dto.category.document.CreateDocumentRequest;
import com.ssafy.logit.model.step_category.dto.category.document.CreateUpdateDocumentList;
import com.ssafy.logit.model.step_category.dto.category.document.DocumentResponse;
import com.ssafy.logit.model.step_category.dto.category.document.UpdateDocumentRequest;
import com.ssafy.logit.model.step_category.entity.StepCategory;
import com.ssafy.logit.model.step_category.entity.category.Document;
import com.ssafy.logit.model.step_category.repository.category.DocumentRepository;
import com.ssafy.logit.model.step_category.service.StepCategoryService;
import com.ssafy.logit.model.step_category.service.category.DocumentService;
import com.ssafy.logit.model.user.entity.User;
import com.ssafy.logit.model.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/step-category/document")
@Tag(name = "Document API", description = "채용전형(서류) API")
public class DocumentController {
    private final DocumentService documentService;
    private final UserService userService;
    private final StepCategoryService stepCategoryService;

    private final DocumentRepository documentRepository;


    @Operation(summary = "서류 조회", description = "id값을 이용하여 서류를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> get(Long id) {
        Document document = documentService.get(id);
        DocumentResponse documentResponse = new DocumentResponse(document);
        return new ResponseEntity<>(documentResponse, HttpStatus.OK);
    }

    @Operation(summary = "서류 생성", description = "request을 이용하여 서류를 생성합니다.")
    @PostMapping
    public ResponseEntity<DocumentResponse> create(@RequestAttribute String email,
                                                   @RequestBody @Validated CreateDocumentRequest request) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.get(request.getStepId());
        Document document = documentService.create(user, stepCategory, request);
        DocumentResponse documentResponse = new DocumentResponse(document);

        return new ResponseEntity<>(documentResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "서류 수정", description = "id값을 이용하여 서류를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponse> update(@RequestAttribute String email,
                                                   @PathVariable Long id,
                                                   @RequestBody @Validated UpdateDocumentRequest request) {
        User user = getUser(email);
        Document updateDocument = documentService.update(user, id, request);
        DocumentResponse documentResponse = new DocumentResponse(updateDocument);
        return new ResponseEntity<>(documentResponse, HttpStatus.OK);
    }

    @Operation(summary = "서류 삭제", description = "서류 생성")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestAttribute String email,
                                       @PathVariable Long id) {
        User user = getUser(email);
        documentService.delete(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "생성,수정 전부 처리", description = "request에 document객체 리스트를 담아 생성, 수정")
    @PostMapping("/create-update-all")
    public ResponseEntity<ResultDto> createUpdateAll(@RequestAttribute String email,
                                                     @Validated @RequestBody CreateUpdateDocumentList documents) {
        User user = getUser(email);
        StepCategory stepCategory = stepCategoryService.get(documents.getStepId());
        documentService.createUpdateAll(user, stepCategory, documents.getList());

        //  documents 리스트
        List<DocumentResponse> results = stepCategory
                .getDocumentList()
                .stream()
                .map(o -> new DocumentResponse(o))
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ResultDto(results.size(),results),HttpStatus.CREATED);
    }

    private User getUser(String email) {
        return userService.getUserEntity(email);
    }
}
