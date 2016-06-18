/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.datatemplate.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.common.datatemplate.model.Template;
import com.common.datatemplate.reader.handlers.XmlReaderHandler;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: DataXmlTemplateReader.java, v 0.1 2016年5月3日 下午3:39:44 ZHOUJINGWEI598 Exp $
 */
public class DataXmlTemplateReader {
    private XmlReaderHandler handler = new XmlReaderHandler();

    public void createXml(String fileName) {
        System.out.println("<<" + fileName + ">>");
    }

    public void parserXml(String fileName) {
        SAXParserFactory saxfac = SAXParserFactory.newInstance();
        try {
            SAXParser saxparser = saxfac.newSAXParser();
            InputStream is = new FileInputStream(fileName);
            saxparser.parse(is, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Template getDataTemplate(){
        return handler.getTemplate();
    }
}
