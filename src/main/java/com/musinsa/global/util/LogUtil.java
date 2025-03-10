package com.musinsa.global.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogUtil {

    public static <T> void setBasicErrorLog(final String exceptionName, final int httpStatus, final T paramInfo, final String stackTrace) {
        log.error(LogConstants.MSG_PATTERN, LogConstants.MSG_PREFIX, exceptionName, httpStatus, getParamInfo(paramInfo), stackTrace);
    }

    public static <T> void setBasicWarnLog(final String exceptionName, final int httpStatus, final T paramInfo, final String stackTrace) {
        log.warn(LogConstants.MSG_PATTERN, LogConstants.MSG_PREFIX, exceptionName, httpStatus, getParamInfo(paramInfo), stackTrace);
    }

    public static <T> void setBasicInfoLog(final String exceptionName, final int httpStatus, final T paramInfo, final String stackTrace) {
        log.info(LogConstants.MSG_PATTERN, LogConstants.MSG_PREFIX, exceptionName, httpStatus, getParamInfo(paramInfo), stackTrace);
    }

    public static <T> void setBasicDebugLog(final String exceptionName, final int httpStatus, final T paramInfo, final String stackTrace) {
        log.debug(LogConstants.MSG_PATTERN, LogConstants.MSG_PREFIX, exceptionName, httpStatus, getParamInfo(paramInfo), stackTrace);
    }

    public static <T> String getParamInfo(final T paramInfo) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(paramInfo);
        } catch (JsonProcessingException e) {
            try {
                return new Gson().toJson(paramInfo);
            } catch (Exception ex) {
                log.error("LogService getParamInfo paramInfo : {}, ERROR : {}", paramInfo, ExceptionUtils.getStackTrace(ex));
                return ExceptionUtils.getStackTrace(ex);
            }
        }
    }
}
