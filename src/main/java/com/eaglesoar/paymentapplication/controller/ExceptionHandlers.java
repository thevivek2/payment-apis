package com.eaglesoar.paymentapplication.controller;

import com.eaglesoar.paymentapplication.exception.DuplicateDataException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<VoilationResource>> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getFieldErrors().stream().map((a) -> new VoilationResource(a.getField(),
                a.getDefaultMessage())).collect(Collectors.toList()));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<List<VoilationResource>> handle(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(Collections.singletonList(new VoilationResource(null, e.getMessage())));
    }

    @ExceptionHandler(value = DuplicateDataException.class)
    public ResponseEntity<List<VoilationResource>> handle(DuplicateDataException e) {
        return ResponseEntity.badRequest().body(Collections.singletonList(new VoilationResource(null, e.getMessage())));
    }


}
