package com.musinsa.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.musinsa.global.exception.BusinessException;
import com.musinsa.global.exception.ExceptionCode;
import com.musinsa.global.util.ResponseErrorUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseError {
    // HttpStatus 코드의 데이터
    private String status;
    // 각각의 Application에서 사용할 별도의 코드
    private String code;
    // 에러의 제목
    private String title;
    // 에러의 상세내용
    private String detail;

    public static ResponseError of(final ExceptionCode exceptionCode, final HttpStatus httpStatus) {
        return ResponseError.builder()
            .detail(exceptionCode.getDescription())
            .code(exceptionCode.getCode())
            .title(httpStatus.getReasonPhrase())
            .status(ResponseErrorUtil.getHttpStatus(httpStatus))
            .build();
    }

    public static ResponseError of(final StringBuilder errorMessage, String errorCode, final HttpStatus httpStatus) {
        return ResponseError.builder()
            .detail(errorMessage.toString())
            .code(errorCode)
            .title(httpStatus.getReasonPhrase())
            .status(ResponseErrorUtil.getHttpStatus(httpStatus))
            .build();
    }

    public static ResponseError of(final Exception exception, final HttpStatus httpStatus) {
        ResponseError responseError = new ResponseError();

        if (exception instanceof BusinessException) {
            responseError.setDetail(ResponseErrorUtil.getErrorMsgReplace(((BusinessException) exception).getDescription(), ((BusinessException) exception).getErrorMsg()
                , ((BusinessException) exception).getErrorMsg2()));
            responseError.setCode(((BusinessException) exception).getErrorCode());
            responseError.setTitle(httpStatus.getReasonPhrase());
        }
        responseError.setStatus(ResponseErrorUtil.getHttpStatus(httpStatus));

        return responseError;
    }


}
