package com.common.datatemplate.model;

import java.util.Map;

public class Request {

    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Request [params=");
        builder.append(params);
        builder.append("]");
        return builder.toString();
    }

}
