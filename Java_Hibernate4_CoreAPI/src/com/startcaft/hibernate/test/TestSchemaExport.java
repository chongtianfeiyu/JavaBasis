package com.startcaft.hibernate.test;


import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class TestSchemaExport {

	//使用SchemaExport来创建数据库表
	@Test
	public void testSchemaExport(){
		
		SchemaExport schema = new SchemaExport(new Configuration().configure());
		schema.create(true, true);
	}

}
