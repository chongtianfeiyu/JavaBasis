package com.startcaft.ssm.service;

import java.util.List;

import com.startcaft.ssm.po.Department;
import com.startcaft.ssm.po.extension.CommitMessage;
import com.startcaft.ssm.po.extension.DepartmentCustom;

public interface DepartmentService {
	
	/**添加一个部门,一个应用程序下只能有一个顶级部门**/
	public CommitMessage insertDepartment(Department depart) throws Exception;
	
	/**根据应用程序获取下面的所有部门**/
	public List<DepartmentCustom> getAllDepartment(int appId) throws Exception;
	
	/**更新一个部门信息，适合于先查询到该部门再更新**/
	public CommitMessage update(Department depart) throws Exception;
	
	/**根据主键获取部门信息**/
	public Department getDepartment(Integer id) throws Exception;
	
	/**删除一个部门节点，有子节点，有用户信息的不能删除**/
	public CommitMessage deleteDepartment(Integer id) throws Exception;
}
