package com.echo.handler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.proxy.Enhancer;

import org.restexpress.Request;
import org.restexpress.Response;

public class ProxyFactory
{
	private static ProxyFactory INSTACE = new ProxyFactory();

	private Map<Class<?>, Object> beans = new ConcurrentHashMap<Class<?>, Object>();
	private Map<String, Class<?>> intf2Clazz = new ConcurrentHashMap<String, Class<?>>();
	private Map<String, Method> methods = new ConcurrentHashMap<String, Method>();

	private ProxyFactory()
	{
	}

	public static ProxyFactory getInstance()
	{
		return INSTACE;
	}

	public Object getProxy(Class<?> bean) throws Exception
	{
		Object result = beans.containsKey(bean);
		if (result == null)
		{
			throw new Exception("No Such Bean.");
		}
		return result;
	}

	public Object invoke(Request request, Response response) throws Exception
	{
		Set<String> hs = request.getHeaderNames();
		for (String h : hs)
		{
			Herders.addHeaders(h, request.getHeader(h));
		}

		String intf = request.getHeader("intf");
		String method = request.getHeader("method");
		Object bean = beans.get(intf2Clazz.get(intf));
		Method m = methods.get(intf + "." + method);
		Object[] args = null;
		try
		{
			if (m.getParameterTypes().length > 0)
			{
				args = new Object[] { request.getBodyAs(m.getParameterTypes()[0]) };
			}
			return m.invoke(bean, args);
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public void register(Object bean)
	{
		Class<?>[] clazzs = bean.getClass().getInterfaces();
		if (clazzs.length == 0)
		{
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(bean.getClass());// 设置代理目标
			enhancer.setCallback(new RemoteServerProxyHandler(bean));// 设置回调
			enhancer.setClassLoader(bean.getClass().getClassLoader());
			beans.put(bean.getClass(), enhancer.create());
			intf2Clazz.put(bean.getClass().getSimpleName(), bean.getClass());
			initMethod(bean.getClass());
		}
		else
		{
			for (Class<?> intf : clazzs)
			{
				beans.put(intf, Proxy.newProxyInstance(intf.getClassLoader(), new Class<?>[] { intf },
						new RemoteServerProxyHandler(bean)));
				intf2Clazz.put(intf.getSimpleName(), intf);
				initMethod(intf);
			}
		}
	}

	private void initMethod(Class<?> intf)
	{
		String intfName = intf.getSimpleName();
		Method[] ms = intf.getDeclaredMethods();
		for (Method m : ms)
		{
			methods.put(intfName + "." + m.getName(), m);
		}
	}
}
