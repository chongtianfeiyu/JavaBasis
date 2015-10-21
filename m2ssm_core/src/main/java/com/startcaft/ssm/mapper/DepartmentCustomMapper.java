package com.startcaft.ssm.mapper;

import java.util.List;

import com.startcaft.ssm.po.extension.DepartmentCustom;


public interface DepartmentCustomMapper {
	
	/**根据应用程序查询其下的部门中的根节点个数**/
	public int findTopDepartCount(int applicationId) throws Exception;
	
	/**根据应用程序获取下面的所有部门**/
	public List<DepartmentCustom> findDepartListByApp(int applicationId) throws Exception;
}
