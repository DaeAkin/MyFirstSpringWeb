package com.min.www.Service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class SysoutAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("------- 프록시 테스트 중 -------");
		Object ret = invocation.proceed();
		return ret;
	}



}
