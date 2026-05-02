package com.example.bookapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private String status;   // SUCCESS / ERROR
    private int code;        // HTTP status code
    private String message;  // response message
    private T data;          // actual response data
}