/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.excel.writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.common.datatemplate.writer.DataXmlTemplateWriter;
import com.common.entities.ChildInput;
import com.common.entities.ClassInfo;
import com.common.entities.Input;
import com.common.entities.WebserviceClassInfo;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: DataXmlTemplateWriterTest.java, v 0.1 2016年5月4日 上午10:32:08 ZHOUJINGWEI598 Exp $
 */
public class DataXmlTemplateWriterTest {

    @Test
    public void testwriteXml() throws Exception {
        WebserviceClassInfo webserviceClassInfo = new WebserviceClassInfo();
        ClassInfo input = new ClassInfo(Input.class);
        ClassInfo childInput = new ClassInfo(ChildInput.class);
        List<ClassInfo> inputs = new ArrayList<ClassInfo>();
        inputs.add(childInput);
        inputs.add(input);
        webserviceClassInfo.setInput(inputs);
        ClassInfo output = new ClassInfo(Map.class);
        webserviceClassInfo.setOutput(output);
        DataXmlTemplateWriter.writeXml("D:/test/temp/temp.xml", webserviceClassInfo);
    }
}
