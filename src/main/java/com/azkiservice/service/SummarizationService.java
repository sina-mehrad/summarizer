package com.azkiservice.service;

import com.azkiservice.dto.res.SummarizationResponse;
import com.azkiservice.exception.RateLimitException;
import com.azkiservice.service.specification.SummarizationContext;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class SummarizationService {

    private final SummarizationContext context;

    public SummarizationService(SummarizationContext context) {
        this.context = context;
    }

    @RateLimiter(name = "summarizeServiceLimiter", fallbackMethod = "rateLimitFallback")
    public SummarizationResponse summarize(MultipartFile file) {
        String fileAsString = readFileAsString(file);
        return context.summarize(fileAsString);
    }

    public SummarizationResponse rateLimitFallback(String text, Throwable t) {
        throw new RateLimitException();
    }

    private String readFileAsString(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Empty file");
        }

        try {
            return new String(file.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Error while converting file to string", e);
            throw new RuntimeException(e);
        }
    }
}
