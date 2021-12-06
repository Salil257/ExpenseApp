package com.licious.expenseapp.advice;

import com.licious.expenseapp.advice.exception.BusinessException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException extends Exception {
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<String> errorHandle(BusinessException biz){
        return new ResponseEntity<>(biz.getErrorMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<String> errorNotFound(NotFoundException e){
        return new ResponseEntity<>(e.getMessage()+"this is happening due to no records",HttpStatus.NOT_FOUND);
    }
}
