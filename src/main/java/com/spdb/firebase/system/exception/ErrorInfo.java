package com.spdb.firebase.system.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorInfo {
    private String id;
    private ErrorType type;
    private Integer code;
    private String message;
}
