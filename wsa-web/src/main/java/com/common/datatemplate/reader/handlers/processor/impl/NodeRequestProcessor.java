package com.common.datatemplate.reader.handlers.processor.impl;

import com.common.datatemplate.model.Request;
import com.common.datatemplate.reader.handlers.XmlReaderHandler;
import com.common.datatemplate.reader.handlers.processor.IXmlNodeProcessor;

public class NodeRequestProcessor implements IXmlNodeProcessor {

    @Override
    public void processNode(XmlReaderHandler handler) {
    }

    @Override
    public void fillInObjectRoute(XmlReaderHandler handler) {
        Request request = handler.getTemplate().getRequest();
        if (null == request) {
            request = new Request();
            handler.getTemplate().setRequest(request);
        }
        handler.getObjectRoute().addFirst(request);
    }

}
