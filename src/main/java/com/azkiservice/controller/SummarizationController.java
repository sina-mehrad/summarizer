package com.azkiservice.controller;

import com.azkiservice.dto.res.SummarizationResponse;
import com.azkiservice.service.SummarizationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/summarization")
public class SummarizationController {

    private final SummarizationService summarizationService;

    public SummarizationController(SummarizationService summarizationService) {
        this.summarizationService = summarizationService;
    }


    @PostMapping(value = "summarize", consumes = "multipart/form-data")
    @Operation(summary = "Summarize a long input file using OpenAI") // TODO: How to upload a file using Swagger?
    public ResponseEntity<SummarizationResponse> summarize(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(summarizationService.summarize(file));
    }
}
