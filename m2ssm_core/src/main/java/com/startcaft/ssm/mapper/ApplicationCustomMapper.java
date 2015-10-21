package com.startcaft.ssm.mapper;

import com.startcaft.ssm.po.extension.ApplicationCustom;
import com.startcaft.ssm.po.extension.ApplicationQueryVo;

public interface ApplicationCustomMapper {
	
	/**查询应用程序列表**/
	public ApplicationCustom findApplicationList(ApplicationQueryVo query) throws Exception;
	
	/**查询应用程序列表总数**/
	public int findApplicationListCount(ApplicationQueryVo query) throws Exception;
}
