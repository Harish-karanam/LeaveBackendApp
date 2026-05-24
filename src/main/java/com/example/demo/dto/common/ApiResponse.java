package com.example.demo.dto.common;



public class ApiResponse<T> {

    private boolean success;

    private String message;

    private T data;
}