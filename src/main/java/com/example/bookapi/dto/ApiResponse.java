package com.example.bookapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private String status;  
    private int code;        
    private String message;  
    private T data;          
}