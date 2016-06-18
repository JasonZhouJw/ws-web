/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.common.datatemplate.writer.constructors;

import org.dom4j.Element;

import com.common.datatemplate.writer.XmlElementFactory;
import com.common.entities.FieldInfo;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: MapConstructor.java, v 0.1 2016年5月4日 上午11:04:29 ZHOUJINGWEI598 Exp $
 */
public class MapConstructor implements Constructor {

    /** 
     * @see com.common.datatemplate.writer.constructors.Constructor#initElement(org.dom4j.Element, com.common.entities.FieldInfo)
     */
    @Override
    public void initElement(Element element, FieldInfo field) {
        Element fieldElement = element.addElement(field.getName());
        fieldElement.addAttribute(ATTRIBUTE_TYPE, field.getType().getName());
        Element mapElement = fieldElement.addElement("Map");
        mapElement.addAttribute(ATTRIBUTE_TYPE, field.getChildClazz().getClassName());
        Element entryElement=mapElement.addElement("entry");
        entryElement.addAttribute("key", "");
        XmlElementFactory.constructElement(entryElement, field.getChildClazz());
    }

}
