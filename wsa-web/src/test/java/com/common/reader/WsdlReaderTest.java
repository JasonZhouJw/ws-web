/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

import com.common.entities.WsdlInfo;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: WsdlReaderTest.java, v 0.1 2016年4月13日 下午2:00:05 ZHOUJINGWEI598 Exp $
 */
public class WsdlReaderTest {

    @Test
    public void testWsdlReader() throws Exception {
        final List<WsdlInfo> wsdlInfoList = new ArrayList<WsdlInfo>();
        WsdlReader.readWsdl(
            "http://192.168.1.234:33400/pvsn/services/cashTransferLogQueryRemote?wsdl",
            new Consumer<WsdlInfo>() {

                @Override
                public void accept(WsdlInfo t) {
                    if (t != null) {
                        wsdlInfoList.add(t);
                    }
                }
            });
        for(WsdlInfo wsdlInfo:wsdlInfoList){
            System.out.println(wsdlInfo);
        }
    }
}
