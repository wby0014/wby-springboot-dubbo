package com.wby.common.core.exception;

import com.wby.common.core.api.IErrorCode;

/**
 * @Description 自定义异常
 * @Author JacksonTu
 * @Date 2018/12/10 13:59
 */

public class BaseException extends RuntimeException {
    private IErrorCode errorCode;

    public BaseException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

}
