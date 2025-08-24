package com.azkiservice.client;

import com.azkiservice.dto.req.SummarizationRequest;
import com.azkiservice.dto.res.OpenAiSummarizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "open-ai", url = "${openai.url}")
public interface OpenAiFeignClient {

    @PostMapping(consumes = "application/json", produces = "application/json")
    OpenAiSummarizationResponse summarize(@RequestHeader("Authorization") String apiKey,
                                          @RequestBody SummarizationRequest request);
}
