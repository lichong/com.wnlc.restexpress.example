package com.echo.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class RemoteServerProxyHandler implements InvocationHandler, MethodInterceptor
{

	private Object bean;

	public RemoteServerProxyHandler(Object bean)
	{
		this.bean = bean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		return method.invoke(bean, args);
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy arg3) throws Throwable
	{
		return method.invoke(bean, args);
	}

}
