package com.eaglesoar.payment.application.advisor;

import com.eaglesoar.payment.application.v1.resource.VoilationResource;
import com.eaglesoar.payment.domain.exception.BaseException;
import org.springframework.dao.DataIntegrityViolationException;
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
        return ResponseEntity.badRequest().body(e.getFieldErrors().stream().map((a) ->
                new VoilationResource().field(a.getField()).message(a.getDefaultMessage())).collect(Collectors.toList()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<List<VoilationResource>> handle(DataIntegrityViolationException e) {
        return ResponseEntity.badRequest().body(Collections.singletonList(new VoilationResource().message(e.getMessage())));
    }

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<List<VoilationResource>> handle(BaseException e) {
        return ResponseEntity.badRequest().body(Collections.singletonList(new VoilationResource().message(e.getMessage())));
    }


}
