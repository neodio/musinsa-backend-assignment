package com.musinsa.global.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * Response Error Util
 */
@UtilityClass
public class ResponseErrorUtil {

    public static String getHttpStatus(final HttpStatus httpStatus) {
        return String.valueOf(httpStatus.value());
    }

    public static String getErrorMsgReplace(String description, String msg, String msg2) {
        try {
            String retMsg;

            if (!StringUtils.hasText(msg)) {
                retMsg = description;
            } else {
                retMsg = description.replace("%d", msg);
            }

            if (StringUtils.hasText(msg2)) {
                retMsg = retMsg.replace("%s", msg2);
            }

            return retMsg;
        } catch (Exception e) {
            return StringUtils.hasText(msg) ? "(" + msg + ")" + description : description;
        }
    }

    public static String getDetailErrorMsg(String keyName, String errorDescription) {
        return "(" + keyName + ")" + errorDescription;
    }
}
