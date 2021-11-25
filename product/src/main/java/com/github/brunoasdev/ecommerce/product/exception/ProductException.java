package com.github.brunoasdev.ecommerce.product.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ProductException extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        var messageUser = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
        var messageDev = ex.getCause().toString();
        var errors = Arrays.asList(Error.builder().messageUser(messageUser).messageDev(messageDev).build());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        var errors = createListOfErrors(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);

    }

    private List<Error> createListOfErrors(BindingResult bindingResult) {
        var errors = new ArrayList<Error>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            var messageUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            var messageDev = fieldError.toString();
            errors.add(Error.builder().messageUser(messageUser).messageDev(messageDev).build());
        }

        return errors;

    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Error {

        private String messageUser;
        private String messageDev;

    }
}
