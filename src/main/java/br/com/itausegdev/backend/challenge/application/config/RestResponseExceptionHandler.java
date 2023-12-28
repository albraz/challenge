package br.com.itausegdev.backend.challenge.application.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static br.com.itausegdev.backend.challenge.domain.utils.Message.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class RestResponseExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class, IllegalStateException.class})
    protected ResponseEntity<Object> response() {
        return ResponseEntity.status(500).body(INTERNAL_SERVER_ERROR);
    }
}