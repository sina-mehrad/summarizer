package com.azkiservice.dto.res;

import java.util.List;

public record OpenAiSummarizationResponse(String id,
                                          String model,
                                          String object,
                                          long created,
                                          List<Choice> choices,
                                          Usage usage) {
}
