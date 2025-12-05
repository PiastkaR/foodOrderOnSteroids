package com.food.order.application.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ErrorDTO {
    private final String code;
    private final String message;
}
