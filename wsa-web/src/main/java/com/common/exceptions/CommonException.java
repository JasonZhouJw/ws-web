/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.exceptions;

import com.common.enums.Responses;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: CommonException.java, v 0.1 2016年5月5日 上午11:14:34 ZHOUJINGWEI598 Exp $
 */
public class CommonException extends Exception {

    /**  */
    private static final long serialVersionUID = 4146609812083577447L;

    private String            errorCode;

    private String            errorMsg;

    public CommonException() {
        super();
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression,
                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonException(String message) {
        super(message);
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CommonException(Responses response) {
        super(response.getRespMsg());
        this.errorCode = response.getRespCode();
        this.errorMsg = response.getRespMsg();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
