/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.exceptions.validations;

import com.common.enums.Responses;
import com.common.exceptions.CommonException;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ValidateCommonException.java, v 0.1 2016年5月5日 上午11:20:56 ZHOUJINGWEI598 Exp $
 */
public class ValidateCommonException extends CommonException {

    /**  */
    private static final long serialVersionUID = 245614552936762097L;

    public ValidateCommonException() {
        super();
    }

    public ValidateCommonException(Responses response) {
        super(response);
    }

    public ValidateCommonException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public ValidateCommonException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ValidateCommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateCommonException(String message) {
        super(message);
    }

    public ValidateCommonException(Throwable cause) {
        super(cause);
    }

    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
