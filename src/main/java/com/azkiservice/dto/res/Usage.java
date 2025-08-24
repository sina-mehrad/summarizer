package com.azkiservice.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Usage(@JsonProperty("prompt_tokens")
                    int promptTokens,

                    @JsonProperty("completion_tokens")
                    int completionTokens,

                    @JsonProperty("total_tokens")
                    int totalTokens,

                    @JsonProperty("prompt_tokens_details")
                    PromptTokensDetails promptTokensDetails,

                    @JsonProperty("completion_tokens_details")
                    CompletionTokensDetails completionTokensDetails) {
}
