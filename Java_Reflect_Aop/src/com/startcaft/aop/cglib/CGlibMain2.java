package com.startcaft.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGlibMain类的main方法中依次调用的TableDAO类的CRUD方法，
 * 现在新的需求来了，只有"张三"才能有权调用这些方法。怎么办？难道要在每个方法上进行判断吗？
 * no，可以使用Proxy，JDK的代理就可以解决，但是JDK的代理需要被代理的对象实现接口，
 * 那岂不是要修改DAO类。
 * 
 * 既然不想改动DAO类又要使用代理，CGlib就可以出来了。
 * 我们只需要增加一个权限验证的方法拦截器即可。
 */
public class CGlibMain2 {

	public static void main(String[] args) {
		
		TableDAO2 dao = TableDAOFactory2.getInstance(new AuthInterceptor("张三"));
		doMethod(dao);
		
		System.out.println("--------------------------");
		dao = TableDAOFactory2.getInstance(new AuthInterceptor("李四"));
		doMethod(dao);
	}
	public static void doMethod(TableDAO2 dao){
		dao.create();
		dao.query();
		dao.update();
		dao.delete();
	}
}
/**拦截器，用于验证用户名**/
class AuthInterceptor implements MethodInterceptor{
	
	private String name;//传入的用户名
	
	public AuthInterceptor(String name) {
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
class TableDAO2{
	
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
class TableDAOFactory2{
	
	private static TableDAO2 tDao = new TableDAO2();
	
	private TableDAOFactory2(){}
	
	public static TableDAO2 getInstance(AuthInterceptor authInterceptor){
		
		Enhancer en = new Enhancer();
		//进行代理
		en.setSuperclass(TableDAO2.class);
		//进行回调
		en.setCallback(authInterceptor);
		//生成代理实例
		tDao = (TableDAO2) en.create();
		
		return tDao;
	}
}
