package com.azkiservice.dto.res;

public record MessageRes(String role,
                         String content,
                         Object refusal,   // nullable
                         Object reasoning) {
}
