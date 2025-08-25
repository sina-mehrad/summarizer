package com.azkiservice.service.specification;

import com.azkiservice.dto.res.SummarizationResponse;

public interface SummarizationContext {
    SummarizationResponse summarize(String file);
}
