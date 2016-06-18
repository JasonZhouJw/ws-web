/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.enums;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: Responses.java, v 0.1 2016年5月5日 上午11:16:52 ZHOUJINGWEI598 Exp $
 */
public enum Responses {

    SUCCESS("000000", "Success"),
    NOT_EQUALS("000001", "Not Equals"),
    LIST_SIZE_NOT_MATCH("001001", "List Size Not Match"),
    SYSTEM_ERROR("999999", "ERROR");

    private String respCode;
    private String respMsg;

    private Responses(String respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

}
