package com.hand.train.springboot_demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class WebResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
    private Map<String, String> errors;

    public static <T> WebResponse<T> success(T data) {
        return WebResponse.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static WebResponse<Object> error(int status, String message, Map<String, String> errors) {
        return WebResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(message)
                .errors(errors)
                .build();
    }
}