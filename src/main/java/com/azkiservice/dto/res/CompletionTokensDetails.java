package com.azkiservice.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CompletionTokensDetails(@JsonProperty("reasoning_tokens")
                                      int reasoningTokens) {
}
