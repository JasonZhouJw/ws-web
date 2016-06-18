/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.datatemplate.writer.constructors;

import org.dom4j.Element;

import com.common.entities.FieldInfo;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: Constructor.java, v 0.1 2016年5月4日 上午11:00:29 ZHOUJINGWEI598 Exp $
 */
public interface Constructor {

    public final static String ATTRIBUTE_TYPE = "type";

    public void initElement(Element element, FieldInfo field);
}
