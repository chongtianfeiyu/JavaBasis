package com.startcaft.hibernate.test;


import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class TestSchemaExport {

	//ʹ��SchemaExport���������ݿ��
	@Test
	public void testSchemaExport(){
		
		SchemaExport schema = new SchemaExport(new Configuration().configure());
		schema.create(true, true);
	}

}
