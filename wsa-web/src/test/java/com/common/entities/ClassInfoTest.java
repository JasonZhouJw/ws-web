/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.entities;

import org.junit.Test;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ClassInfoTest.java, v 0.1 2016年4月18日 上午9:57:10 ZHOUJINGWEI598 Exp $
 */
public class ClassInfoTest {

    @Test
    public void testconstructor() {
        ClassInfo classInfo = new ClassInfo("com.common.entities.WsdlInfo");
        System.out.println(classInfo);
    }
}
