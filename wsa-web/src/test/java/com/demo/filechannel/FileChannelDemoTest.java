/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.demo.filechannel;

import org.junit.Test;

/**
 * 
 * @author zhoujingwei598
 * @version $Id: FileChannelDemoTest.java, v 0.1 2016年5月26日 上午10:24:44 zhoujingwei598 Exp $
 */
public class FileChannelDemoTest {

    @Test
    public void testreadFile(){
        FileChannelDemo.readFile("D:/test/temp/temp.xml");
    }
    
    @Test
    public void check_security(){
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkRead("D:/Temp");
            security.checkWrite("D:/Temp");
        }
    }
}
