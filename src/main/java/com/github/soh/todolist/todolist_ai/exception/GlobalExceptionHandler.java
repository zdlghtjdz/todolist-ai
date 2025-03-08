package com.github.soh.todolist.todolist_ai.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 Bad Request (잘못된 요청) - 데이터 유효성 문제
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
    }

    // 404 Not Found (존재하지 않는 리소스)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        System.out.println(ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 400 Bad Request ( 잘못된 데이터 타입 요청 )
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        //return buildErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 요청 형식입니다:" + ex.getMessage());
        // 고정 형태의 메시지 전달로 테스트 시 해당 메시지 체크.
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "잘못된 요청 형식입니다");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>> handleNotAllowMethod(HttpRequestMethodNotSupportedException ex) {
        return buildErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다.");
    }

    // 500 Internal Server Error(예상치 못한 서버 오류)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex, HttpServletRequest request) {

        if (request.getRequestURI().startsWith("/v3/api-docs") || request.getRequestURI().startsWith("/swagger-ui")) {
            return ResponseEntity.ok(Collections.emptyMap());
        }

        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.");
    }

    // 공통 에러 응답을 생성하는 메서드
    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", status.value());
        errorResponse.put("error", status.getReasonPhrase());
        errorResponse.put("message", message);
        return new ResponseEntity<>(errorResponse, status);
    }
}
