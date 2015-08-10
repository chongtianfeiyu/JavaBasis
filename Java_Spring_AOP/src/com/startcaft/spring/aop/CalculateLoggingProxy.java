package com.startcaft.spring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class CalculateLoggingProxy {
	
	//要代理的对象
	private Calculate target;
	
	public CalculateLoggingProxy(Calculate target) {
		this.target = target;
	}
	
	/**
	 * 获取代理对象
	 * @return
	 */
	public Calculate getLogginProxy(){
		Calculate proxy = null;
		
		//代理对象由哪一个类加载器负责加载
		ClassLoader loader = target.getClass().getClassLoader();
		
		//代理对象的类型，即其中有哪些方法
		Class [] interfaces = new Class[]{Calculate.class};
		
		InvocationHandler h = new InvocationHandler() {
			
			/**
			 * proxy : 正在返回的那个代理对象，一般情况下，在invoke方法中都不是用该对象。
			 * method：正在调用的方法
			 * args：调用方法时，传入的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				
				//System.out.println(proxy.toString());//无限循环，调用了被代理的对象的方法了。
				
				String methodName = method.getName();
				//日志
				System.out.println("The Mehtod " + methodName + " begins with " + Arrays.asList(args));
				//执行方法
				Object result = method.invoke(target, args);
				//日志
				System.out.println("The Mehtod " + methodName + " result : " + result);
				return result;
			}
		};
		
		proxy = (Calculate) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
}
