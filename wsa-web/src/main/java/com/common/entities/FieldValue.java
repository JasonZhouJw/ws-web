/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.entities;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: FieldValue.java, v 0.1 2016年5月12日 上午10:48:10 ZHOUJINGWEI598 Exp $
 */
public class FieldValue extends FieldInfo {

    private Object value;
    
    public FieldValue(){
        super();
    }
    
    public FieldValue(FieldInfo fieldInfo){
        this.childClazz=fieldInfo.getChildClazz();
        this.name=fieldInfo.getName();
        this.type=fieldInfo.getType();
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
