package com.startcaft.http;


public class GlobalConstants {
	
	/**
	 * ��ȡurl����
	 * @param page �ڼ�ҳ
	 * @param format xml/json
	 * @return
	 */
	public static String getUrl(Integer page,String format){
		StringBuffer buffer = new StringBuffer("http://api.roll.news.sina.com.cn/zt_list?channel=news");
		String url = "";
		buffer.append("&cat_1=shxw");//��ʾ����
        buffer.append("&cat_2==zqsk||=qwys||=shwx||=fz-shyf");
        buffer.append("&level==1||=2");//����
        buffer.append("&show_ext=1");
        buffer.append("&show_all=1");//��ʾ����
        buffer.append("&show_num=22");//��ʾ������
        buffer.append("&tag=1");
        buffer.append("&format=+format");
        buffer.append("&page=+page");
        buffer.append("&callback=newsloader");
        url=buffer.toString();
        return url;
	}
}
