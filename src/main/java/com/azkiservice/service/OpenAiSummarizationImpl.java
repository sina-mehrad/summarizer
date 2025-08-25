package com.azkiservice.service;

import com.azkiservice.client.OpenAiFeignClient;
import com.azkiservice.dto.req.SummarizationRequest;
import com.azkiservice.dto.res.MessageReq;
import com.azkiservice.dto.res.OpenAiSummarizationResponse;
import com.azkiservice.dto.res.SummarizationResponse;
import com.azkiservice.exception.ApiKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class OpenAiSummarizationImpl implements SummarizationContext {

    private final OpenAiFeignClient openAiFeignClient;

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.prompt}")
    private String prompt;

    public OpenAiSummarizationImpl(OpenAiFeignClient openAiFeignClient) {
        this.openAiFeignClient = openAiFeignClient;
    }

    @Override
    public SummarizationResponse summarize(String file) {
        List<MessageReq> messageReqs = new ArrayList<>();
        messageReqs.add(new MessageReq("system", prompt));
        messageReqs.add(new MessageReq("user", file));

        SummarizationRequest request = new SummarizationRequest(model, messageReqs);

        OpenAiSummarizationResponse summarize;
        try {
            summarize = openAiFeignClient.summarize("Bearer " + apiKey, request);
        } catch (Exception e) {
            log.error("Error while calling openai api", e);
            throw new ApiKeyException("Invalid api key or something went wrong!");
        }

        String content = summarize.choices().getFirst().message().content();
        return new SummarizationResponse(content);
    }
}
