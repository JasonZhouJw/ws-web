/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.entities;

import com.common.enums.Operation;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: Expection.java, v 0.1 2016年5月5日 上午11:30:34 ZHOUJINGWEI598 Exp $
 */
public class Expection {

    private Operation operation;

    private Object    value;

    private String    type;

    private String    customerClazzName;

    private String    fieldName;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerClazzName() {
        return customerClazzName;
    }

    public void setCustomerClazzName(String customerClazzName) {
        this.customerClazzName = customerClazzName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Expection [operation=");
        builder.append(operation);
        builder.append(", value=");
        builder.append(value);
        builder.append(", type=");
        builder.append(type);
        builder.append(", customerClazzName=");
        builder.append(customerClazzName);
        builder.append(", fieldName=");
        builder.append(fieldName);
        builder.append("]");
        return builder.toString();
    }

}
