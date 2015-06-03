package com.startcaft.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Http请求工具类
 * 
 * @author wow
 *
 */
public class HttpRequestUtil {
	
	/**
	 * 发送http请求
	 * @param requestUrl 请求的地址
	 * @param method 请求的方式，GET还是POST
	 * @return
	 */
	public static String httpURLConnRequest(String requestUrl,String method){
		StringBuffer buffer = new StringBuffer();
		
		try {
			//1.创建一个java.net.URL对象
			URL url = new URL(requestUrl);
			//2. 创建并配置HttpURLConnection对象
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod(method);
			httpUrlConn.setUseCaches(false);//是否使用缓存
			httpUrlConn.setInstanceFollowRedirects(true);//重定向
			//3.启动http连接
			httpUrlConn.connect();
			//将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader sReader  = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(sReader);
			
			String str = null;
			while((str = bufferedReader.readLine()) != null){
				buffer.append(str);
			}
			//释放资源
			bufferedReader.close();
			sReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return buffer.toString();
	}
}
