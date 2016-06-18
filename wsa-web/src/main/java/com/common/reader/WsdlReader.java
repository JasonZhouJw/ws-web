/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.reader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javax.wsdl.Binding;
import javax.wsdl.BindingOperation;
import javax.wsdl.Definition;
import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.soap.SOAPAddress;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;

import org.springframework.util.StringUtils;

import com.common.entities.WsdlInfo;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: WsdlReader.java, v 0.1 2016年4月13日 下午1:58:39 ZHOUJINGWEI598 Exp $
 */
public class WsdlReader {

    @SuppressWarnings("unchecked")
    public static void readWsdl(String wsdlUrl, Consumer<WsdlInfo> consumer) {
        try {
            WSDLFactory factory = WSDLFactory.newInstance();
            WSDLReader reader = factory.newWSDLReader();
            reader.setFeature("javax.wsdl.verbose", true);
            reader.setFeature("javax.wsdl.importDocuments", true);
            Definition def = reader.readWSDL(wsdlUrl);
            String namespace = def.getNamespace("tns");
            Map<QName, Service> serviceMap = def.getAllServices();
            Iterator<Entry<QName, Service>> serviceIter = serviceMap.entrySet().iterator();
            while (serviceIter.hasNext()) {
                Entry<QName, Service> serviceEntry = serviceIter.next();
                Map<String, Port> portMap = serviceEntry.getValue().getPorts();
                Iterator<Entry<String, Port>> portIter = portMap.entrySet().iterator();
                while (portIter.hasNext()) {
                    Entry<String, Port> portEntry = portIter.next();
                    Port port = portEntry.getValue();
                    WsdlInfo wsdlInfo = new WsdlInfo();
                    List<ExtensibilityElement> extensibilityElementList = port
                        .getExtensibilityElements();
                    for (ExtensibilityElement extensibilityElement : extensibilityElementList) {
                        if (extensibilityElement instanceof SOAPAddress) {
                            SOAPAddress soapAddress = (SOAPAddress) extensibilityElement;
                            wsdlInfo.setAddress(soapAddress.getLocationURI());
                        }
                    }
                    if (StringUtils.isEmpty(wsdlInfo.getAddress())) {
                        continue;
                    }
                    Binding binding = port.getBinding();
                    wsdlInfo.setWsdl(wsdlUrl);
                    wsdlInfo.initFacadeClass(namespace, binding.getPortType().getQName()
                        .getLocalPart());
                    List<BindingOperation> bindingOperationList = binding.getBindingOperations();
                    for (BindingOperation operation : bindingOperationList) {
                        wsdlInfo.addOperation(operation.getName());
                    }
                    consumer.accept(wsdlInfo);
                }
            }

        } catch (WSDLException e) {
            e.printStackTrace();
        }
    }

}
