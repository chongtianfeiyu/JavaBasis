package com.startcaft.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;




/**
 *动态代理不仅简化了编程工作，还提供了系统的扩展性和可维护性。
 *1，通过实现java.lang.reflect.invocationHandler接口提供一个执行处理器。
 *2，通过java.lang.reflect.Proxy得到一个代理对象。
 *通过这个代理对象来执行业务逻辑方法，在业务逻辑方法被调用的同时，自动会调用执行处理器。
 */
public class TestDymanicProxy {

	public static void main(String[] args) {
		
		BusinessObj obj = new BusinessObjImpl();
		
		//创建一个日志拦截器对象，并将被代理对象传入
		LogInterceptor interptor = new LogInterceptor(obj);
		
		//通过Proxy类的newProxyInstance()方法来获得动态的代理对象
		BusinessObj proxy = (BusinessObj) Proxy.newProxyInstance(BusinessObjImpl.class
				.getClassLoader(), BusinessObjImpl.class.getInterfaces(), interptor);
	
		//执行动态代理对象的业务逻辑方法
		proxy.process();
	}
}

/**日志拦截器**/
class LogInterceptor implements InvocationHandler{
	
	private Object delegate;
	
	public LogInterceptor(Object delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		Object result = null;
		
		try {
			System.out.println("befor process:" + method);
			//调用代理对象delegate的目标方法，并将args作为参数进行传入
			result = method.invoke(delegate, args);
			System.out.println("after process:" + method);
		} catch (Exception e) {
			System.err.println("发生异常:" + e.toString());
		}
		return result;
	}
}

/**业务接口**/
interface BusinessObj{
	void process();
}
/**业务逻辑实现类**/
class BusinessObjImpl implements BusinessObj{

	@Override
	public void process() {
		System.out.println("执行业务逻辑");
	}
}