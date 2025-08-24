package com.azkiservice.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PromptTokensDetails(@JsonProperty("cached_tokens")
                                  int cachedTokens,

                                  @JsonProperty("audio_tokens")
                                  int audioTokens) {
}
