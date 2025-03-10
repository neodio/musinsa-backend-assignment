package com.musinsa.global.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceConverter {

    /**
     * T data 를 입력하면 응답할 ResponseObject 객체를 반환한다.
     *
     * @param data - ResponseObject 에 입력할 data 객체.
     * @return ResponseObject<T> - 입력된 data 를 data 로 가지는 ResponseObject
     */
    public static <T> ResponseObject<T> toResponseObject(T data) {
        ResponseObject<T> responseObject = new ResponseObject<>();
        responseObject.setData(data);
        return responseObject;
    }

    /**
     * ResponseEntity 객체의 body 에 ResponseObject 를 입력하여 반환한다.
     *
     * @param responseObject - ResponseEntity 에 입력할 body 객체.
     * @param httpStatus - response 결과의 HttpStatus code.
     * @return ResponseEntity<ResponseObject<T>> - ResponseObject<T> 를 body 로 가진 ResponseEntity.
     */
    public static <T> ResponseEntity<ResponseObject<T>> toResponseEntity(ResponseObject<T> responseObject, HttpStatus httpStatus){
        return new ResponseEntity<>(responseObject, httpStatus);
    }
}
