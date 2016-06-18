/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.datatemplate.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.common.entities.ClassInfo;
import com.common.entities.FieldInfo;
import com.common.entities.WebserviceClassInfo;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: DataXmlTemplateWriter.java, v 0.1 2016年5月4日 上午10:27:38 ZHOUJINGWEI598 Exp $
 */
public class DataXmlTemplateWriter {

    public static void writeXml(String fileName, WebserviceClassInfo classInfo) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("Templates");
        Element template = root.addElement("Template");
        constructRequestStructure(template, classInfo.getInput());
        constructVerifyStructure(template, classInfo.getOutput());
        Writer fileWriter = null;
        try {
            OutputFormat format = new OutputFormat("    ", true);
            format.setEncoding("UTF-8");
            fileWriter = new FileWriter(fileName);
            XMLWriter xmlWriter = new XMLWriter(fileWriter, format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void constructRequestStructure(Element root, List<ClassInfo> classInfoList) {
        Element request = root.addElement("Request");
        for (int i = 0; i < classInfoList.size(); i++) {
            ClassInfo classInfo = classInfoList.get(i);
            Element argumentElement = request.addElement("Argument_" + i);
            argumentElement.addAttribute("class", classInfo.getClassName());
            Iterator<Entry<String, FieldInfo>> fieldIter = classInfo.getFieldMap().entrySet()
                .iterator();
            while (fieldIter.hasNext()) {
                Entry<String, FieldInfo> fieldEntry = fieldIter.next();
                XmlElementFactory.constructElement(argumentElement, fieldEntry.getValue());
            }
        }
    }

    /**
     * 构建响应数据模板
     * 
     * TODO:响应数据模板暂时不支持直接返回list、map等集合对象
     * 
     * @param root
     * @param classInfo
     */
    @SuppressWarnings("rawtypes")
    private static void constructResponseStructure(Element root, ClassInfo classInfo) {
        Element response = root.addElement("Response");
        response.addAttribute("class", classInfo.getClassName());
        Iterator<Entry<String, FieldInfo>> fieldIter = classInfo.getFieldMap().entrySet()
            .iterator();
        while (fieldIter.hasNext()) {
            Entry<String, FieldInfo> fieldEntry = fieldIter.next();
            XmlElementFactory.constructElement(response, fieldEntry.getValue());
        }
        if (classInfo.getFieldMap().isEmpty()) {
            response.setText("");
        }
        Iterator itr = response.elementIterator();
        while(itr.hasNext()){
            Element e = (Element) itr.next();
            e.addAttribute("operation", "EQ");
        }
    }
    
    private static void constructVerifyStructure(Element root, ClassInfo classInfo){
        Element verify = root.addElement("Verify");
        constructResponseStructure(verify, classInfo);
        constructSqlStructure(verify, classInfo);
    }
    
    private static void constructSqlStructure(Element root, ClassInfo classInfo){
        Element sql = root.addElement("Sql");
        Element query = sql.addElement("Query");
        query.addAttribute("size", "1");
        query.setText("");
        Element column = sql.addElement("Column");
        Element expect = column.addElement("Expect");
        expect.addAttribute("column", "");
        expect.addAttribute("operation", "");
        expect.setText("");
    }
}
