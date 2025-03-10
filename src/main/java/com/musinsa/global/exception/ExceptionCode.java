package com.musinsa.global.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * [공통] 에러코드 정의
 * 0001 ~ 1000 시스템에러
 * 1001 ~ 9999 사용자 정의 에러코드
 */
@RequiredArgsConstructor
public enum ExceptionCode {

		ERROR_CODE_0001("0001", "시스템 에러 : 관리자에게 문의해 주세요.")
	,   ERROR_CODE_0002("0002", "통신 에러 : 잘못된 URL을 호출 하였습니다.")
	,   ERROR_CODE_0003("0003", "통신 에러 : Http Method가 잘못 되었습니다.")
	,   ERROR_CODE_0004("0004", "DB 에러! 재시도 해주세요.")

	,   ERROR_CODE_1001("1001", "파라미터 유효성 검사 오류입니다.")
	,   ERROR_CODE_1002("1002", "필수값을 입력해주세요.")
	,   ERROR_CODE_1003("1003", "(%d) 필수값을 입력해주세요.")
	,   ERROR_CODE_1004("1004", "파라미터 타입 오류입니다.")
	,   ERROR_CODE_1005("1005", " 자 사이로 입력해주세요.")
	,   ERROR_CODE_1006("1006", " 자 이하로 입력해주세요.")
	,   ERROR_CODE_1007("1007", " 이상으로 입력해주세요.")
	,   ERROR_CODE_1008("1008", " 이하로 입력해주세요.")
	,   ERROR_CODE_1009("1009", "(%d) 데이터가 없습니다.")

	;

	@Getter
	private final String code;
	@Getter
	private final String description;
}
