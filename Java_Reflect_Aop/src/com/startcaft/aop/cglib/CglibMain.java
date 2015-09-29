package com.startcaft.aop.cglib;

/**
 * CGlib是什么？
 * 它是一个强大的，高性能，高质量的Code生成类库，它可以在运行期扩展Java类与实现Java接口。
 * 这些实际的功能是asm所提供的。
 * asm又是什么？
 * 它是一个Java字节码操控框架。
 * cglib就是封装了sfm，简化了asm的操作，实现了在运行期间生成新的class。
 * 
 * 实际上CGlib为很多框架提供了底层的一种实现，如Spring AOP和Hibernate动态生成VO/PO对象。
 */
public class CglibMain {
	
	public static void main(String[] args) {
		
		TableDAO tableDao = TableDAOFactory.getInstance();
		doMethod(tableDao);
		
	}
	public static void doMethod(TableDAO dao){
		dao.create();
		dao.query();
		dao.update();
		dao.delete();
	}
}

/**模拟一个DAO类**/
class TableDAO{
	
	void create(){
		System.out.println("create() is running !");
	}
	void query(){
		System.out.println("query() is running !");
	}
	void delete(){
		System.out.println("delete() is running !");
	}
	void update(){
		System.out.println("update() is running !");
	}
}
/**模拟一个DAO工厂**/
class TableDAOFactory{
	
	private static TableDAO tDao = new TableDAO();
	
	private TableDAOFactory(){}
	
	public static TableDAO getInstance(){
		return tDao;
	}
	
}
