package com.spdb.firebase.system.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.transaction.SystemException;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ResponseExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorInfo handleBusinessException(BusinessException exception) {
        String message = messageSource.getMessage(exception.getMessage(), null, Locale.getDefault());

        StackTraceElement causeElement = exception.getStackTrace()[0];
        ErrorInfo errorInfo = ErrorInfo.builder()
                .id(UniqueIdGenerator.get())
                .type(ErrorType.BUSINESS)
                .code(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build();

        log.warn(
                String.format("[Business exception [%s]: %s [%s: %s]",
                        errorInfo.getId(), message, causeElement.getClassName(), causeElement.getLineNumber()
                )
        );

        return errorInfo;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorInfo handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String messages = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining());

        StackTraceElement causeElement = exception.getStackTrace()[0];
        ErrorInfo errorInfo = ErrorInfo.builder()
                .id(UniqueIdGenerator.get())
                .type(ErrorType.BUSINESS)
                .code(HttpStatus.BAD_REQUEST.value())
                .message(messages)
                .build();

        log.warn(String.format("[Validation exception [%s]: %s [%s: %s]",
                errorInfo.getId(), messages, causeElement.getClassName(), causeElement.getLineNumber()
                )
        );

        return errorInfo;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorInfo handleSystemException(SystemException exception) {
        return handleException(exception);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorInfo handleException(Exception exception) {
        String message = messageSource.getMessage("error.unexpected", null, Locale.getDefault());
        StackTraceElement causeElement = exception.getStackTrace()[0];
        ErrorInfo errorInfo = ErrorInfo.builder()
                .id(UniqueIdGenerator.get())
                .type(ErrorType.SYSTEM)
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(message)
                .build();

        String logMessage = String.format(
                "[System exception [%s]: %s [%s: %s]]",
                errorInfo.getId(), message, causeElement.getClassName(), causeElement.getLineNumber()
        );

        if(exception.getStackTrace() != null) {
            log.error(logMessage, exception);
        } else log.error(logMessage);

        return errorInfo;
    }
}
