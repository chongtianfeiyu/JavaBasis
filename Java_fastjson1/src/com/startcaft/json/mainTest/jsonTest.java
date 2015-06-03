package com.startcaft.json.mainTest;

import java.util.Iterator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class jsonTest {

	public static void main(String[] args) {
		//[{age:22,sex:'男',userName:'xiaoliang'},{age:22,sex:'男',userName:'xiaoliang'}]
		
		StringBuffer buffer = new StringBuffer();
		
		//使用StringBuffer来构造上面的json字符串,注意json字符串的格式
		buffer.append("[");
			buffer.append("{");
				buffer.append("age").append(":").append(22).append(',');
				buffer.append("sex").append(":").append("'男'").append(',');
				buffer.append("userName").append(":").append("'周伯通'");
			buffer.append("}");	
			buffer.append(",");	//第一个对象结束
			buffer.append("{");
				buffer.append("age").append(":").append(23).append(',');
				buffer.append("sex").append(":").append("'男'").append(',');
				buffer.append("userName").append(":").append("'令狐冲'");
			buffer.append("}");	
		buffer.append("]");		
		
		String jsonString = buffer.toString();
		
		//使用JSON.parseArray(jsonStr)
		JSONArray jarry = JSONArray.parseArray(jsonString);
		
		for(Iterator<Object> iterator = jarry.iterator();iterator.hasNext();){
			JSONObject jObject = (JSONObject)iterator.next();
			int age = jObject.getIntValue("age");
			String name = jObject.getString("userName");
			String sex = jObject.getString("sex");
			
			System.out.println("name:" + name + "---" + "age:" + age + "---" + "male:" + sex);
		}
	}

}
