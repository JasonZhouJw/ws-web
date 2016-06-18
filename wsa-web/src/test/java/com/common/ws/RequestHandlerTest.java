/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.ws;

import org.junit.Test;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: RequestHandlerTest.java, v 0.1 2016年4月15日 下午2:13:04 ZHOUJINGWEI598 Exp $
 */
public class RequestHandlerTest {

    @Test
    public void testrequest() throws Exception {
        RequestHandler.request("http://192.168.1.234:33400/pvsn/services");
    }
}
