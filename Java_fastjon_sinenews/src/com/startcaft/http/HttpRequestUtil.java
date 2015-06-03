package com.startcaft.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Http���󹤾���
 * 
 * @author wow
 *
 */
public class HttpRequestUtil {
	
	/**
	 * ����http����
	 * @param requestUrl ����ĵ�ַ
	 * @param method ����ķ�ʽ��GET����POST
	 * @return
	 */
	public static String httpURLConnRequest(String requestUrl,String method){
		StringBuffer buffer = new StringBuffer();
		
		try {
			//1.����һ��java.net.URL����
			URL url = new URL(requestUrl);
			//2. ����������HttpURLConnection����
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod(method);
			httpUrlConn.setUseCaches(false);//�Ƿ�ʹ�û���
			httpUrlConn.setInstanceFollowRedirects(true);//�ض���
			//3.����http����
			httpUrlConn.connect();
			//�����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader sReader  = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(sReader);
			
			String str = null;
			while((str = bufferedReader.readLine()) != null){
				buffer.append(str);
			}
			//�ͷ���Դ
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
