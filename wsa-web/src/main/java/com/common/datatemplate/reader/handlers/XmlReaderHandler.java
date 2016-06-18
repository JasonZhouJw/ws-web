/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.datatemplate.reader.handlers;

import java.io.IOException;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.common.datatemplate.model.Template;
import com.common.enums.KeyNode;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: XmlReaderHandler.java, v 0.1 2016年5月3日 下午3:41:49 ZHOUJINGWEI598 Exp $
 */
public class XmlReaderHandler extends DefaultHandler {
    /**
     * <ol>
     * <li>nodeRoute用于记录从XML文档树的根到当前节点的路径，采用头插法，子节点在前，父节点在后。</li>
     * <li>在startElement方法中将节点名称插入列表，在endElement方法中将节点名称删除。</li>
     * <li>此路径对应的是文档树里面的结构，不一定与存储的数据对象结构相同，
     * 例如Request标签下可以有多个Argument，从数据结构来看是Request对象里有一个存储Argument的map，map里面才是Argument，
     * 但XML文档结构中不会体现出来，nodeRoute中的记录则是[Argument，Request……]，不会有map记录在里面。</li>
     * <ol>
     */
    private LinkedList<String> nodeRoute         = new LinkedList<String>();
    /**
     * <ol>
     * <li>objectRoute用于记录与nodeRoute匹配的对象，便于组装数据。</li>
     * <li>objectRoute的插入操作在startElement方法中执行，objectRoute的删除操作在XmlReaderHandler的endElement方法中执行。</li>
     * <li>注意到有些数据可能是基础数据类型的，这时也应当向objectRoute中插入一个对应的对象以保证objectRoute处理逻辑的一致性。</li>
     * </ol>
     */
    private LinkedList<Object> objectRoute       = new LinkedList<Object>();
    /**
     * 当前节点的值，运行到characters方法中才会被更新。
     */
    private String             currentvalue      = null;
    /**
     * 当前节点的属性，在startElement方法中更新。
     */
    private Attributes         currentAttributes = null;
    /**
     * 此handler组装的数据模板。
     */
    private Template           template          = null;

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws IOException,
                                                                      SAXException {
        System.out.println("Function:resolveEntity");
        System.out.println("publicId:" + publicId + ",systemId" + systemId);
        return super.resolveEntity(publicId, systemId);
    }

    @Override
    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        System.out.println("Function:notationDecl");
        System.out.println("name:" + name + ",publicId" + publicId + ",systemId" + systemId);
        super.notationDecl(name, publicId, systemId);
    }

    @Override
    public void unparsedEntityDecl(String name, String publicId, String systemId,
                                   String notationName) throws SAXException {
        System.out.println("Function:unparsedEntityDecl");
        System.out.println("name:" + name + ",publicId:" + publicId + ",systemId:" + systemId
                           + ",natationName:" + notationName);
        super.unparsedEntityDecl(name, publicId, systemId, notationName);
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        System.out.println("Function:setDocumentLocator");
        System.out.println("locator:" + locator);
        super.setDocumentLocator(locator);
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Function:startDocument");
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Function:endDocument");
        super.endDocument();
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        System.out.println("Function:startPrefixMapping");
        System.out.println("prefix:" + prefix + ",uri:" + uri);
        super.startPrefixMapping(prefix, uri);
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        System.out.println("Function:endPrefixMapping");
        System.out.println("prefix:" + prefix);
        super.endPrefixMapping(prefix);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
                                                                                               throws SAXException {
        System.out.println("Function:startElement");
        System.out.println("uri:" + uri + ",localName:" + localName + ",qName:" + qName
                           + ",attributes:" + attributes);
        System.out.println("qName=" + qName);
        System.out.println("value=" + attributes.getValue(qName));
        if (null != attributes) {
            for (int i = 0; i < attributes.getLength(); ++i) {
                System.out.println("property:" + attributes.getQName(i));
                System.out.println("value:" + attributes.getValue(i));
                System.out.println();
            }
        }
        super.startElement(uri, localName, qName, attributes);

        //填充nodeRoute和objectRoute
        nodeRoute.addFirst(qName);
        currentAttributes = attributes;
        fillInObjectRoute();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Function:endElement");
        System.out.println("uri:" + uri + ",localName:" + localName + ",qName:" + qName);
        super.endElement(uri, localName, qName);

        //移除nodeRoute和objectRoute中的当前
        nodeRoute.removeFirst();
        objectRoute.removeFirst();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("Function:characters");
//        System.out.println("ch:" + String.valueOf(ch));
        System.out.println("start:" + start + ",length:" + length);
        System.out.println("content=" + String.copyValueOf(ch, start, length));
        super.characters(ch, start, length);

        currentvalue = String.copyValueOf(ch, start, length);
        processEvent();
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        System.out.println("Function:ignorableWhitespace");
        System.out.println("ch:" + String.valueOf(ch) + ",start:" + start + ",length:" + length);
        super.ignorableWhitespace(ch, start, length);
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        System.out.println("Function:processingInstruction");
        System.out.println("target:" + target + ",data:" + data);
        super.processingInstruction(target, data);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        System.out.println("Function:skippedEntity");
        System.out.println("name:" + name);
        super.skippedEntity(name);
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.out.println("Function:warning");
        System.out.println("e:" + e);
        super.warning(e);
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("Function:error");
        System.out.println("e:" + e);
        super.error(e);
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Function:fatalError");
        System.out.println("e:" + e);
        super.fatalError(e);
    }

    public void processEvent() {
        KeyNode kn = KeyNode.matchNode(nodeRoute);
        if (null != kn) {
            try {
                kn.getProcessor().processNode(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void fillInObjectRoute(){
        KeyNode kn = KeyNode.matchNode(nodeRoute);
        if (null != kn) {
            try {
                kn.getProcessor().fillInObjectRoute(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getCurrentvalue() {
        return currentvalue;
    }

    public Attributes getCurrentAttributes() {
        return currentAttributes;
    }
    
    public LinkedList<String> getNodeRoute() {
        return nodeRoute;
    }

    public LinkedList<Object> getObjectRoute() {
        return objectRoute;
    }
}
