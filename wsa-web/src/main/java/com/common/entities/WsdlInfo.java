/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.entities;

import java.util.ArrayList;
import java.util.List;

import com.common.utils.Constants;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: WsdlInfo.java, v 0.1 2016年4月14日 下午2:46:36 ZHOUJINGWEI598 Exp $
 */
public class WsdlInfo {

    private String       wsdl;

    private String       address;

    private String       facadeClass;

    private List<String> operationList = new ArrayList<String>();

    public String getWsdl() {
        return wsdl;
    }

    public void setWsdl(String wsdl) {
        this.wsdl = wsdl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFacadeClass() {
        return facadeClass;
    }

    public void setFacadeClass(String facadeClass) {
        this.facadeClass = facadeClass;
    }

    public List<String> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<String> operationList) {
        this.operationList = operationList;
    }

    public void initFacadeClass(String namespace, String className) {
        namespace = namespace.substring(namespace.indexOf(Constants.HTTP_PREFIX)
                                        + Constants.HTTP_PREFIX.length(),
            namespace.lastIndexOf(Constants.HTTP_SUFFIX));
        String[] packages = namespace.split(Constants.SEPERATE_DOT);
        StringBuffer packageSb = new StringBuffer();
        for (int i = packages.length - 1; i >= 0; i--) {
            packageSb.append(packages[i]).append(Constants.DOT);
        }
        packageSb.append(className);
        this.facadeClass = packageSb.toString();
    }

    public void addOperation(String operation) {
        if (!this.operationList.contains(operation)) {
            this.operationList.add(operation);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WsdlInfo [wsdl=");
        builder.append(wsdl);
        builder.append(", address=");
        builder.append(address);
        builder.append(", facadeClass=");
        builder.append(facadeClass);
        builder.append(", operationList=");
        builder.append(operationList);
        builder.append("]");
        return builder.toString();
    }

}
