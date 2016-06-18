package com.common.enums;

import java.util.List;

import com.common.datatemplate.reader.handlers.processor.IXmlNodeProcessor;
import com.common.datatemplate.reader.handlers.processor.impl.NodeArgumentFieldProcessor;
import com.common.datatemplate.reader.handlers.processor.impl.NodeArgumentProcessor;
import com.common.datatemplate.reader.handlers.processor.impl.NodeRequestProcessor;
import com.common.datatemplate.reader.handlers.processor.impl.NodeTemplateProcessor;
import com.common.datatemplate.reader.handlers.processor.impl.NodeVerifyProcessor;

public enum KeyNode {
    TEMPLATE("Template", new NodeTemplateProcessor()), 
    REQUEST("Request", new NodeRequestProcessor()), 
    ARGUMENT("Argument_", new NodeArgumentProcessor()), 
    ARGUMENT_FIELD("", new NodeArgumentFieldProcessor()),
    VERIFY("Verify", new NodeVerifyProcessor()), 
    RESPONSE("Response", null), 
    SQL("Sql", null);

    private String            node;
    private IXmlNodeProcessor processor;

    private KeyNode(String node, IXmlNodeProcessor processor) {
        this.node = node;
        this.processor = processor;
    }

    public String getNode() {
        return node;
    }

    public IXmlNodeProcessor getProcessor() {
        return processor;
    }

    public static KeyNode matchNode(List<String> nodeList) {
        KeyNode kn;
        kn = KeyNode.get(nodeList.get(0));
        if (null == kn) {
            kn = extraMatchingLogic(nodeList);
        }

        return kn;
    }

    private static KeyNode extraMatchingLogic(List<String> nodeList) {
        if (nodeList.get(0).startsWith(ARGUMENT.getNode())) {
            return ARGUMENT;
        }else if(nodeList.contains(REQUEST.getNode())){
            return ARGUMENT_FIELD;
        }
        return null;
    }

    public static KeyNode get(String node) {
        for (KeyNode kn : KeyNode.values()) {
            if (kn.getNode().equals(node)) {
                return kn;
            }
        }
        return null;
    }
}
