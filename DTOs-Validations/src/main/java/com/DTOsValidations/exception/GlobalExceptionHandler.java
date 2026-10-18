package com.DTOsValidations.exception;

import com.DTOsValidations.dto.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI() );


        return  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);

    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateResourceException(DuplicateResourceException ex,HttpServletRequest request){

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI() );
        return  ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptionResponse);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDto> handleRunTimeException(MethodArgumentNotValidException ex,HttpServletRequest request){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI() );

        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleRunTimeException(RuntimeException ex,HttpServletRequest request){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI() );

        return  ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionResponse);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleRunTimeException(Exception ex,HttpServletRequest request){
        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI() );

          return  ResponseEntity
                  .status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(exceptionResponse);

    }

}
