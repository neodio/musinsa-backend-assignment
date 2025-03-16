package com.musinsa.global.exception;

import com.musinsa.global.common.ResourceConverter;
import com.musinsa.global.common.ResponseError;
import com.musinsa.global.common.ResponseObject;
import com.musinsa.global.util.LogUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Optional;


/**
 * [공통] 에러처리 : 서버에서 발생하는 모든 에러를 처리
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ResponseObject<ResponseError>> getHandleException(BindingResult br, HttpServletRequest request) {
        return this.getHandleResponseEntity(br.getFieldError(), request);
    }

    private ResponseEntity<ResponseObject<ResponseError>> getHandleResponseEntity(final FieldError fieldError, HttpServletRequest request) {
        ResponseObject<ResponseError> responseObject = new ResponseObject<>();
        ResponseError error = new ResponseError();

        StringBuilder errorMessage = new StringBuilder();
        String errorCode;
        String codeType;
        String errorDefMsg;
        Object[] errorArguments;

        if (fieldError != null) {
            codeType = Optional.ofNullable(fieldError.getCode()).orElseGet(() -> "");
            errorDefMsg = fieldError.getDefaultMessage();
            errorArguments = Optional.ofNullable(fieldError.getArguments()).orElseGet(() -> new Object[0]);

            Object errorArguments1 = errorArguments.length > 1 ? errorArguments[1] : "";
            Object errorArguments2 = errorArguments.length > 2 ? errorArguments[2] : "";

            switch (codeType) {
                case "NotNull":
                case "NotEmpty":
                    errorCode = ExceptionCode.ERROR_CODE_1002.getCode();
                    errorMessage.append("(").append(errorDefMsg).append(")").append(ExceptionCode.ERROR_CODE_1002.getDescription());
                    break;
                case "Size":
                    if (!ObjectUtils.isEmpty(errorArguments2) && (int) errorArguments2 > 0) {
                        errorCode = ExceptionCode.ERROR_CODE_1005.getCode();

                        errorMessage.append("(").append(errorDefMsg).append(")");
                        errorMessage.append(errorArguments2).append("~").append(errorArguments1).append(ExceptionCode.ERROR_CODE_1005.getDescription());

                    } else {
                        errorCode = ExceptionCode.ERROR_CODE_1006.getCode();

                        errorMessage.append("(").append(errorDefMsg).append(")");
                        errorMessage.append(errorArguments1).append(ExceptionCode.ERROR_CODE_1006.getDescription());
                    }
                    break;
                case "Min":
                    errorCode = ExceptionCode.ERROR_CODE_1007.getCode();
                    errorMessage.append("(").append(errorDefMsg).append(")").append(errorArguments1).append(ExceptionCode.ERROR_CODE_1007.getDescription());
                    break;
                case "Max":
                    errorCode = ExceptionCode.ERROR_CODE_1008.getCode();
                    errorMessage.append("(").append(errorDefMsg).append(")").append(errorArguments1).append(ExceptionCode.ERROR_CODE_1008.getDescription());
                    break;
                default:
                    errorCode = ExceptionCode.ERROR_CODE_1001.getCode();
                    errorMessage.append("(").append(errorDefMsg).append(")").append(ExceptionCode.ERROR_CODE_1001.getDescription());
                    break;
            }

            responseObject.addError(ResponseError.of(errorMessage, errorCode, HttpStatus.UNPROCESSABLE_ENTITY));
        } else {
            LogUtil.setBasicErrorLog("getHandleResponseEntityNULL", HttpStatus.INTERNAL_SERVER_ERROR.value(), GlobalExceptionHandler.getRequestInfo(request), error.toString());
            responseObject.addError(ResponseError.of(ExceptionCode.ERROR_CODE_0001, HttpStatus.UNPROCESSABLE_ENTITY));
        }
        return ResourceConverter.toResponseEntity(responseObject, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseObject<ResponseError>> handleBaseException(Throwable e, HttpServletRequest request) {
        LogUtil.setBasicErrorLog("Throwable", HttpStatus.INTERNAL_SERVER_ERROR.value(), GlobalExceptionHandler.getRequestInfo(request), ExceptionUtils.getStackTrace(e));
        return ResourceConverter.toResponseEntity(ResponseObject.from(ResponseError.of(ExceptionCode.ERROR_CODE_0001, HttpStatus.INTERNAL_SERVER_ERROR)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ResponseObject<ResponseError>> handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        LogUtil.setBasicDebugLog("NoHandlerFoundException", HttpStatus.NOT_FOUND.value(), GlobalExceptionHandler.getRequestInfo(request), ExceptionUtils.getStackTrace(e));
        return ResourceConverter.toResponseEntity(ResponseObject.from(ResponseError.of(ExceptionCode.ERROR_CODE_0002, HttpStatus.NOT_FOUND)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseObject<ResponseError>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        LogUtil.setBasicDebugLog("HttpRequestMethodNotSupportedException", HttpStatus.METHOD_NOT_ALLOWED.value(), GlobalExceptionHandler.getRequestInfo(request), ExceptionUtils.getStackTrace(e));
        return ResourceConverter.toResponseEntity(ResponseObject.from(ResponseError.of(ExceptionCode.ERROR_CODE_0003, HttpStatus.METHOD_NOT_ALLOWED)), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseObject<ResponseError>> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request) {
        LogUtil.setBasicErrorLog("DataIntegrityViolationException", HttpStatus.INTERNAL_SERVER_ERROR.value(), GlobalExceptionHandler.getRequestInfo(request), ExceptionUtils.getStackTrace(e));
        return ResourceConverter.toResponseEntity(ResponseObject.from(ResponseError.of(ExceptionCode.ERROR_CODE_0004, HttpStatus.INTERNAL_SERVER_ERROR)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseObject<ResponseError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        LogUtil.setBasicDebugLog("MethodArgumentNotValidException", HttpStatus.UNPROCESSABLE_ENTITY.value(), GlobalExceptionHandler.getRequestInfo(request), ExceptionUtils.getStackTrace(e));
        return this.getHandleException(e.getBindingResult(), request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseObject<ResponseError>> handleBusinessLogicException(
        BusinessException e, HttpServletRequest request) {
        LogUtil.setBasicDebugLog("BusinessException", HttpStatus.UNPROCESSABLE_ENTITY.value(), GlobalExceptionHandler.getRequestInfo(request), ExceptionUtils.getStackTrace(e));
        return ResourceConverter.toResponseEntity(ResponseObject.from(ResponseError.of(e, HttpStatus.UNPROCESSABLE_ENTITY)), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * 에러 로그에 추가될 request 정보를 추출하여 반환.
     */
    private static String getRequestInfo(HttpServletRequest request) {
        StringBuffer result = new StringBuffer();
        if (request != null) {
            result.append("[url:");
            result.append(request.getRequestURL().toString() + (StringUtils.isBlank(request.getQueryString()) ? "" : "?" + request.getQueryString()));
            result.append("]").append(",[requestMethod:").append(request.getMethod()).append("]");
        }
        return result.toString();
    }
}
