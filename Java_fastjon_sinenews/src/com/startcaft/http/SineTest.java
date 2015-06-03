package com.startcaft.http;

import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class SineTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String reuqestUrl = GlobalConstants.getUrl(2, "json");
		String jsonStr = HttpRequestUtil.httpURLConnRequest(reuqestUrl, "GET");
		//System.out.println(jsonStr);
		
		//处理页面的json数据
		int start = jsonStr.indexOf("(") +1;
		jsonStr = jsonStr.substring(start, jsonStr.lastIndexOf(")"));
		//System.out.println(jsonStr);
		
		String result = "";
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		result = jsonObject.getString("result").toString();
		
		JSONObject resObject = JSONObject.parseObject(result);
		
		String dataStr = resObject.getString("data").toString();
		
		JSONArray dataArray = JSONArray.parseArray(dataStr);
		String title = "",url = "",keywords = "" ,img = "",media_name = "";

        for (Iterator<Object> it = dataArray.iterator();it.hasNext();) {
			JSONObject object = (JSONObject) it.next();
			
			title = object.getString("title").toString();
			url = object.getString("url").toString();
			keywords = object.getString("keywords").toString();
			img = object.getString("img").toString();
			media_name = object.getString("media_name").toString();
			

			System.out.println("title:" + title + "\n");
			System.out.println("url:" + url + "\n");
			System.out.println("keywords:" + keywords + "\n");
			System.out.println("img:" + img + "\n");
			System.out.println("media_name:" + media_name + "\n");
		}
	}

}
