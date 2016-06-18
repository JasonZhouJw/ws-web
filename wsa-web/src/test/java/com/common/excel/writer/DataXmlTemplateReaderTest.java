/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.excel.writer;

import org.junit.Test;

import com.common.datatemplate.reader.DataXmlTemplateReader;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: DataXmlTemplateReaderTest.java, v 0.1 2016年5月4日 上午9:37:55 ZHOUJINGWEI598 Exp $
 */
public class DataXmlTemplateReaderTest {

    @Test
    public void testparserXml() throws Exception{
        DataXmlTemplateReader writer=new DataXmlTemplateReader();
        writer.parserXml("D:/test/temp/temp2.xml");
    }
}
