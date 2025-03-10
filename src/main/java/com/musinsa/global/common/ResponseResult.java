package com.musinsa.global.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseResult {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String returnKey;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int returnCnt;

	private String returnMsg;

	public <T extends Number> ResponseResult(T returnKey, int returnCnt, String returnMsg) {
		this.returnKey = String.valueOf(returnKey);
		this.returnCnt = returnCnt;
		this.returnMsg = returnMsg;
	}

	public static <T extends Number> ResponseResult getResponseResult(T returnKey, int returnCnt, String returnMsg) {
		return new ResponseResult(returnKey, returnCnt, returnMsg);
	}
}
