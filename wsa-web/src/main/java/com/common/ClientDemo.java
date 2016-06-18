package com.common;

/**
 * 
 *	平安付
 * Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */

import java.lang.reflect.Method;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

/**
 * 
 * @author ZHOUJINGWEI598
 * @version $Id: ClientDemo.java, v 0.1 2016年3月31日 下午4:51:15 ZHOUJINGWEI598 Exp
 *          $
 */
public class ClientDemo {

	public static void main(String[] args) throws Exception {
		dynamic();
	}

	public static void dynamic() throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://192.168.1.234:33400/pvsn/services/estiAllotPlanFacade?wsdl");

		Object order = Thread.currentThread().getContextClassLoader()
				.loadClass("com.pinganfu.pvsn.common.facade.estimate.EstiAllotPlanPagingQueryReq").newInstance();
		Method m1 = order.getClass().getMethod("setStartPage", Integer.class);
		Method m2 = order.getClass().getMethod("setPageSize", Integer.class);
		m1.invoke(order, 1);
		m2.invoke(order, 10);
		Object[] response = client.invoke("pagingQueryEstiAllotPlan", order);
		System.out.println("Response is " + response[0]);
	}

	// public static void address(){
	// JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
	// factory.setServiceClass(EstiAllotPlanFacade.class);
	// factory.setAddress("http://192.168.1.234:33400/pvsn/services/estiAllotPlanFacade");
	// EstiAllotPlanFacade service = (EstiAllotPlanFacade)factory.create();
	//
	// EstiAllotPlanPagingQueryReq request=new EstiAllotPlanPagingQueryReq();
	//
	// PagingResp<EstiAllotPlanEntity> result =
	// service.pagingQueryEstiAllotPlan(request);
	// System.out.println("Result " + result);
	// }
}
