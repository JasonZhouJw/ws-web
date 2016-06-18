/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import com.common.assemble.ClassAssemblor;
import com.common.datatemplate.writer.DataXmlTemplateWriter;
import com.common.entities.WebserviceClassInfo;
import com.common.entities.WsdlInfo;
import com.common.reader.WsdlReader;
import com.common.utils.WsdlGetter;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: RequestFactory.java, v 0.1 2016年4月15日 下午2:08:24 ZHOUJINGWEI598 Exp $
 */
public class RequestHandler {

    public static void request(String serviceUrl) throws Exception {
        Set<String> wsdlSet = WsdlGetter.getWsdl(serviceUrl);
        final List<WsdlInfo> wsdlInfoList = new ArrayList<WsdlInfo>();
        for (String wsdlUrl : wsdlSet) {
            WsdlReader.readWsdl(wsdlUrl, new Consumer<WsdlInfo>() {

                @Override
                public void accept(WsdlInfo t) {
                    if (t != null) {
                        wsdlInfoList.add(t);
                    }
                }
            });
        }
        List<WebserviceClassInfo> webserviceClassInfoList = new ArrayList<WebserviceClassInfo>();
        for (WsdlInfo wsdlInfo : wsdlInfoList) {
            webserviceClassInfoList.addAll(ClassAssemblor.assembleClass(wsdlInfo));
        }

        for (WebserviceClassInfo webserviceClassInfo : webserviceClassInfoList) {
            DataXmlTemplateWriter.writeXml("D:/test/temp/" + webserviceClassInfo.getMethodName()+".xml",
                webserviceClassInfo);
        }
    }
}
