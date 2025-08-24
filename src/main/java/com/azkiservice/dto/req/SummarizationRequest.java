package com.azkiservice.dto.req;

import com.azkiservice.dto.res.MessageReq;

import java.util.List;

public record SummarizationRequest(String model,
                                   List<MessageReq> messages) {
}
