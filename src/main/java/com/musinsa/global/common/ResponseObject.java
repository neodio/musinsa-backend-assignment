package com.musinsa.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResponseObject<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ResponseError> errors = null;

    public void addError(ResponseError error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

    public static ResponseObject<ResponseError> from(final ResponseError responseError) {
        ResponseObject<ResponseError> responseObject = new ResponseObject<>();
        responseObject.addError(responseError);
        return responseObject;
    }
}
