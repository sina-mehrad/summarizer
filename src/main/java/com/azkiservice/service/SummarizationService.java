package com.azkiservice.service;

import com.azkiservice.client.OpenAiFeignClient;
import com.azkiservice.dto.req.SummarizationRequest;
import com.azkiservice.dto.res.MessageReq;
import com.azkiservice.dto.res.OpenAiSummarizationResponse;
import com.azkiservice.dto.res.SummarizationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class SummarizationService {

    private final OpenAiFeignClient openAiFeignClient;
    @Value("${openai.api-key}")
    private String apiKey;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.prompt}")
    private String prompt;

    public SummarizationService(OpenAiFeignClient openAiFeignClient) {
        this.openAiFeignClient = openAiFeignClient;
    }

    public SummarizationResponse summarize(MultipartFile file) {
        String fileAsString = extractedAsString(file);

        List<MessageReq> messageReqs = new ArrayList<>();
        messageReqs.add(new MessageReq("system", prompt));
        messageReqs.add(new MessageReq("user", fileAsString));

        SummarizationRequest request = new SummarizationRequest(model, messageReqs);

        OpenAiSummarizationResponse summarize = openAiFeignClient.summarize("Bearer " + apiKey, request);

        String content = summarize.choices().getFirst().message().content();

        return new SummarizationResponse(content);
    }

    private String extractedAsString(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Empty file");
        }

        try {
            return new String(file.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
