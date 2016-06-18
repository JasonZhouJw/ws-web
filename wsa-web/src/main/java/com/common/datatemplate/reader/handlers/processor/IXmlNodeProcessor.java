package com.common.datatemplate.reader.handlers.processor;

import com.common.datatemplate.reader.handlers.XmlReaderHandler;

public interface IXmlNodeProcessor {

    /**
     * <ul>
     * <li>将当前节点的对象实例化并填充到XmlReaderHandler的objectRoute中。</li>
     * <li>如有需要，新实例化的节点对象写入XmlReaderHandler的template也在这里执行，以确保objectRoute中的对象就是template属性中的对象</li>
     * </ul>
     * @param handler
     * @throws Exception 
     */
    void fillInObjectRoute(XmlReaderHandler handler) throws Exception;
    /**
     * 处理当前节点，并将处理结果写入XmlReaderHandler的template中。
     * 
     * @param handler
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    void processNode(XmlReaderHandler handler) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException;
}
