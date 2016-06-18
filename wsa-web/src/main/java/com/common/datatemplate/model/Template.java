package com.common.datatemplate.model;

/**
 * 数据模板
 * 
 * @author YAOHAISHI191
 * @version $Id: Template.java, v 0.1 2016年5月19日 下午2:09:36 YAOHAISHI191 Exp $
 */
public class Template {

    private Request request;
    private Verify  verify;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Verify getVerify() {
        return verify;
    }

    public void setVerify(Verify verify) {
        this.verify = verify;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Template [request=");
        builder.append(request);
        builder.append(", verify=");
        builder.append(verify);
        builder.append("]");
        return builder.toString();
    }
}
