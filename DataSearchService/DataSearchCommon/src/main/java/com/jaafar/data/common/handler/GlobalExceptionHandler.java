package com.jaafar.data.common.handler;

import com.jaafar.data.common.exception.BaseBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice("com.jaafar")
@Slf4j
public class GlobalExceptionHandler {

    public static final String KEY_EXCEPTION_MESSAGE = "message";
    public static final String KEY_EXCEPTION_CAUSE   = "cause";

    @ExceptionHandler(Exception.class)
    public ResponseEntity otherExceptionHandler(HttpServletResponse response, Exception ex){
        log.error("otherExceptionHandler catch ex: " + ex.getMessage(), ex);
        return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseBusinessException.class)
    @SuppressWarnings({ "unchecked" })
    public ResponseEntity baseBusinessExceptionHandler(HttpServletResponse response, BaseBusinessException ex){
        log.error("baseBusinessExceptionHandler catch ex: " + ex.getMessage(), ex);
        return new ResponseEntity(this.buildHeaders(ex), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, String> buildHeaders(BaseBusinessException ex) {
        Map<String, String> headers = new HashMap<>();
        headers.put(KEY_EXCEPTION_MESSAGE, ex.getMessage());
        return headers;
    }

}
