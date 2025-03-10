package com.musinsa.global.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 비즈니스 로직 Exception
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusinessException extends RuntimeException {

    private String errorCode;
    private String description = "";
    private String errorMsg = "";
    private String errorMsg2 = "";

    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getDescription());
        this.errorCode = exceptionCode.getCode();
        this.description = exceptionCode.getDescription();
    }

    public BusinessException(ExceptionCode exceptionCode, String errorMsg) {
        this.errorCode = exceptionCode.getCode();
        this.description = exceptionCode.getDescription();
        this.errorMsg = errorMsg;
    }

    public BusinessException(ExceptionCode exceptionCode, String errorMsg, String errorMsg2) {
        super(exceptionCode.getDescription());
        this.errorCode = exceptionCode.getCode();
        this.description = exceptionCode.getDescription();
        this.errorMsg = errorMsg;
        this.errorMsg2 = errorMsg2;
    }

    public <T extends Number> BusinessException(ExceptionCode exceptionCode, T errorMsg) {
        this.errorCode = exceptionCode.getCode();
        this.description = exceptionCode.getDescription();
        this.errorMsg = String.valueOf(errorMsg);
    }

    public <T extends Number> BusinessException(ExceptionCode exceptionCode, String errorMsg, T errorMsg2) {
        this.errorCode = exceptionCode.getCode();
        this.description = exceptionCode.getDescription();
        this.errorMsg = errorMsg;
        this.errorMsg2 = String.valueOf(errorMsg2);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
