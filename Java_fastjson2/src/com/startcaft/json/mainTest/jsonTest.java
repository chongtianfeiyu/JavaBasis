package com.startcaft.json.mainTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.startcaft.json.entity.Classz;
import com.startcaft.json.entity.Student;


public class jsonTest {

	public static void main(String[] args) {
		
		Student student = new Student();
		student.setAge(33);
		student.setUserName("startcaft");
		student.setSex("男");
		
		Student student2 = new Student();
		student2.setAge(31);
		student2.setUserName("gungun");
		student2.setSex("女");
		
		Classz classz = new Classz();
		classz.getStudents().add(student);
		classz.getStudents().add(student2);
		
		
		//JSON.toJSONString(Object)
		String jsonString = JSON.toJSONString(classz);
		System.out.println("对象转化的JSON字符串为：" + jsonString);
		
		
		//根据json字符串转换成一个JSONObject对象
		JSONObject jsonObject = new JSONObject();
		Object object = jsonObject.parse(jsonString);
		System.out.println("JSON字符串转换成Object对象:" + object);
		
		
		//根据json字符串转换成对应的类型对象.JSON.parseObject(jsonstr,class)
		Classz clz = JSON.parseObject(jsonString,Classz.class);
		Student student3 = clz.getStudents().get(0);
		System.out.println(student3.getUserName());
	}
}
