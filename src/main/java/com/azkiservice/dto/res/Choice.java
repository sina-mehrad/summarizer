package com.azkiservice.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(int index,
                     Object logprobs,
                     MessageRes message,

                     @JsonProperty("finish_reason")
                     String finishReason,

                     @JsonProperty("native_finish_reason")
                     String nativeFinishReason) {
}
