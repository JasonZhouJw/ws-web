/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.utils;

import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: WsdlGetterTest.java, v 0.1 2016年4月14日 下午4:38:19 ZHOUJINGWEI598 Exp $
 */
public class WsdlGetterTest {

    @Test
    public void testgetWsdl() throws Exception {
        Set<String> actual = WsdlGetter.getWsdl("http://192.168.1.234:33400/pvsn/services");
        for (String wsdlUrl : actual) {
            System.out.println(wsdlUrl);
        }
    }
}
