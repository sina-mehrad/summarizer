package com.azkiservice.service;

import com.azkiservice.dto.res.SummarizationResponse;

public interface SummarizationContext {
    SummarizationResponse summarize(String file);
}
