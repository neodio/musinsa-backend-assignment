package com.musinsa.global.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 로그관련 공통상수
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogConstants {
    public static final String MSG_PATTERN = "{},EXCEPTION:{},HTTP-STATUS:{},REQUEST-INFO:{},STACK-TRACE:{}";
    public static final String MSG_PREFIX = "musinsa-backend-assignment";
}
