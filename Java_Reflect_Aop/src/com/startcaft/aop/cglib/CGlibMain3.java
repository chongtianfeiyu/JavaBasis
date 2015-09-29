package com.startcaft.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

/**
 * OK,CGlibMain中的"张三"正常执行，"李四"的没有执行，这就是简单的AOP。
 * 现在新的需求又来了，必须开放查询功能。简单，修改拦截器嘛，不过这样会逻辑变得复杂，不利于维护。
 * CGlib还提供了方法过滤器【CallbackFilter】，
 * CallbackFilter可以明确表明，被代理的类中不同的方法被哪个拦截器所拦截。
 */
public class CGlibMain3 {

	public static void main(String[] args) {
		
		TableDAO3 dao = TableDAOFactory3.getInstance(new AuthInterceptor2("张三"));
		doMethod(dao);
		
		System.out.println("--------------------------");
		dao = TableDAOFactory3.getInstance(new AuthInterceptor2("李四"));
		doMethod(dao);
	}
	public static void doMethod(TableDAO3 dao){
		dao.create();
		dao.query();
		dao.update();
		dao.delete();
	}
}
/**使用CallbackFilter来过滤query方法**/
class AtuhInterceptorFilter implements CallbackFilter{

	@Override
	public int accept(Method arg0) {
		if (!"query".equalsIgnoreCase(arg0.getName())) {
			return 0;
		}
		return 1;
	}
	
}
/**拦截器，用于验证用户名**/
class AuthInterceptor2 implements MethodInterceptor{
	
	private String name;//传入的用户名
	
	public AuthInterceptor2(String name) {
		this.name = name;
	}

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		
		//对用户进行判断
		if(!"张三".equals(name)){
			System.out.println("你没有权限!");
			return null;
		}
		return arg3.invokeSuper(arg0, arg2);
	}
}
class TableDAO3{
	
	void create(){
		System.out.println("create() is running !");
	}
	void query(){
		System.out.println("query() is running !");
	}
	void delete(){
		System.out.println("delete() is running !");
	}
	void update(){
		System.out.println("update() is running !");
	}
}
class TableDAOFactory3{
	
	private static TableDAO3 tDao = new TableDAO3();
	
	private TableDAOFactory3(){}
	
	public static TableDAO3 getInstance(AuthInterceptor2 authInterceptor){
		
		Enhancer en = new Enhancer();
		//进行代理
		en.setSuperclass(TableDAO3.class);
		//进行回调
		en.setCallbacks(new Callback[]{authInterceptor,NoOp.INSTANCE});
		//设置过滤器
		en.setCallbackFilter(new AtuhInterceptorFilter());
		//生成代理实例
		tDao = (TableDAO3) en.create();
		
		return tDao;
	}
}
