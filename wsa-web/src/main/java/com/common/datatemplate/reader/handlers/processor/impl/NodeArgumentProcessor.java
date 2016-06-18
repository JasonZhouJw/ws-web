package com.common.datatemplate.reader.handlers.processor.impl;

import java.util.HashMap;
import java.util.Map;

import com.common.datatemplate.model.Request;
import com.common.datatemplate.reader.handlers.XmlReaderHandler;
import com.common.datatemplate.reader.handlers.processor.IXmlNodeProcessor;
import com.common.utils.XmlPropertyNameConstants;

public class NodeArgumentProcessor implements IXmlNodeProcessor {

    @Override
    public void fillInObjectRoute(XmlReaderHandler handler) {
        Map<String, Object> paramMap = getParams(handler);

        Object param = null;
        try {
            param = getParamInstance(handler.getCurrentAttributes().getValue(
                XmlPropertyNameConstants.CLASS));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        paramMap.put(handler.getNodeRoute().getFirst(), param);
        handler.getObjectRoute().addFirst(param);
    }

    @Override
    public void processNode(XmlReaderHandler handler) {
    }

    private Map<String, Object> getParams(XmlReaderHandler handler) {
        Request request = handler.getTemplate().getRequest();
        if (null == request.getParams()) {
            request.setParams(new HashMap<String, Object>());
        }
        
        return request.getParams();
    }

    private Object getParamInstance(String classType) throws ClassNotFoundException,
                                                             InstantiationException,
                                                             IllegalAccessException {
        Class<?> paramType = Class.forName(classType);
        Object param = paramType.newInstance();
        return param;
    }
}
