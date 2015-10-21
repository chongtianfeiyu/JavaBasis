package com.startcaft.ssm.mapper;

import java.util.List;

import com.startcaft.ssm.po.extension.UserCustom;
import com.startcaft.ssm.po.extension.UserQueryVo;

public interface UserCustomMapper {
	
	/**检查是否有重名的情况**/
	public int checkUserName(String username) throws Exception;
	
	/**获取一个部门下的所有用户，分页**/
	public List<UserCustom> findUserLisyByDepart(UserQueryVo query) throws Exception;
	
	/**获取一个部门下的所有总数**/
	public int findUserLisyCount(UserQueryVo query) throws Exception;
}
