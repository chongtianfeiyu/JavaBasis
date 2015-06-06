package com.startcaft.junit.test;

//import static org.junit.Assert.*;静态导入包，直接使用静态成员，无需类名.


import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.startcaft.junit.HelloTest;

public class HelloTestTest {

	@Test
	public void testAdd() {
		
		/* 这是JUnit4之前，老的断言方式
		int x = new HelloTest().add(5, 3);
		Assert.assertEquals(8, x);
		//Assert.assertFalse("x太小",x > 3);
		Assert.assertTrue(x > 3);
		*/
		
		//JUnit4之后，使用新的断言方式，使用hamcrest表达式进行断言
		//需要引入hamcrest-core-1.x.jar包和hamcrest-library-1.x.jar包
		//使用Matchers静态中的表达式方法进行断言即可。
		int x = new HelloTest().add(5, 3);
		Assert.assertThat(x, Matchers.is(8));
		
		//断言结果大于3 小于10
		Assert.assertThat(x, Matchers.allOf(Matchers.greaterThan(3),Matchers.lessThan(10)));
		//任意值都OK
		Assert.assertThat(x, Matchers.anything());
	}
	
	//断言一个异常
	@Test(expected=java.lang.ArithmeticException.class)
	public void testDivid(){
		int z = new HelloTest().divid(4, 0);
	}
	
	//断言一个方法在多长时间内完成
	@Test(timeout=100)
	public void testForOPer(){
		
		int x = new HelloTest().forOpr();
	}
	
	//忽略一个测试用例
	@Ignore
	@Test
	public void testIgore(){
		
	}
	
	//每个测试方法之前运行
	@Before
	public void testBefore(){
		System.out.println("before");
	}
	//每个测试方法之后运行
	@After
	public void testAfter(){
		System.out.println("after");
	}
	
	//测试类对象初始化之前运行，所以必须是静态的,一般做一些初始化工作，如加载spring配置文件，连接数据库等等
	@BeforeClass
	public static void testClassBefore(){
		System.out.println("class before");
	}
	@AfterClass
	public static void testClassAfter(){
		System.out.println("class after");
	}
}
