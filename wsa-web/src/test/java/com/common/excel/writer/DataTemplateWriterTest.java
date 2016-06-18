/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.excel.writer;

import org.junit.Test;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: DataTemplateWriterTest.java, v 0.1 2016年4月14日 上午11:24:42 ZHOUJINGWEI598 Exp $
 */
public class DataTemplateWriterTest {

    private DataTemplateWriter writer=new DataTemplateWriter();
    
    @Test
    public void testwriteDataTemplate() throws Exception{
        writer.writeDataTemplate("D:/test/temp/temp.xls", "1");
    }
}
