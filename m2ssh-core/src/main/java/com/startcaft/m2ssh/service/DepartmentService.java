package com.startcaft.m2ssh.service;

import java.util.List;

import com.startcaft.m2ssh.comm.CommitMessage;
import com.startcaft.m2ssh.entity.Department;

public interface DepartmentService {
	
	/**新增一个部门**/
	public CommitMessage insertDepart(Department depart) throws Exception;
	
	/**获取所有的部门信息，全部的数据交给zTree来处理**/
	public List<Department> getAllDepart() throws Exception;
	
}
