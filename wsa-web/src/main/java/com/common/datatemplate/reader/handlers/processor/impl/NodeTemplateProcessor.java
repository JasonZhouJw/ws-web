package com.common.datatemplate.reader.handlers.processor.impl;

import com.common.datatemplate.model.Template;
import com.common.datatemplate.reader.handlers.XmlReaderHandler;
import com.common.datatemplate.reader.handlers.processor.IXmlNodeProcessor;

public class NodeTemplateProcessor implements IXmlNodeProcessor {

    @Override
    public void processNode(XmlReaderHandler handler) {
    }

    @Override
    public void fillInObjectRoute(XmlReaderHandler handler) {
        Template template = handler.getTemplate();
        if (null == template) {
            template = new Template();
            handler.setTemplate(template);
        }
        handler.getObjectRoute().addFirst(template);
    }

}
