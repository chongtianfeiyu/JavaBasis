package com.startcaft.json.mainTest;

import java.util.Iterator;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class jsonTest {

	public static void main(String[] args) {
		//[{age:22,sex:'��',userName:'xiaoliang'},{age:22,sex:'��',userName:'xiaoliang'}]
		
		StringBuffer buffer = new StringBuffer();
		
		//ʹ��StringBuffer�����������json�ַ���,ע��json�ַ����ĸ�ʽ
		buffer.append("[");
			buffer.append("{");
				buffer.append("age").append(":").append(22).append(',');
				buffer.append("sex").append(":").append("'��'").append(',');
				buffer.append("userName").append(":").append("'�ܲ�ͨ'");
			buffer.append("}");	
			buffer.append(",");	//��һ���������
			buffer.append("{");
				buffer.append("age").append(":").append(23).append(',');
				buffer.append("sex").append(":").append("'��'").append(',');
				buffer.append("userName").append(":").append("'�����'");
			buffer.append("}");	
		buffer.append("]");		
		
		String jsonString = buffer.toString();
		
		//ʹ��JSON.parseArray(jsonStr)
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
