package com.startcaft.ssm.controller.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class MyDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(source);	//日期格式字符串转换java.uitl.Date成功，直接返回。
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;						//参数绑定失败直接返回null。
	}

}
