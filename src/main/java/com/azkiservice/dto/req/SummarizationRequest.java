package com.azkiservice.dto.req;

import java.util.List;

public record SummarizationRequest(String model,
                                   List<MessageReq> messages) {
}
