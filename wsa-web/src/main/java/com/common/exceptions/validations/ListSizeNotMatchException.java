/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.exceptions.validations;

import com.common.enums.Responses;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ListSizeNotMatchException.java, v 0.1 2016年5月16日 上午10:45:49 ZHOUJINGWEI598 Exp $
 */
public class ListSizeNotMatchException extends ValidateCommonException {

    /**  */
    private static final long serialVersionUID = 2393355097208325014L;

    public ListSizeNotMatchException() {
        super();
    }

    public ListSizeNotMatchException(Responses response) {
        super(response);
    }

    public ListSizeNotMatchException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public ListSizeNotMatchException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ListSizeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListSizeNotMatchException(String message) {
        super(message);
    }

    public ListSizeNotMatchException(Throwable cause) {
        super(cause);
    }

}
