package com.startcaft.ssm.service;

import java.util.List;

import com.startcaft.ssm.po.extension.CommitMessage;
import com.startcaft.ssm.po.extension.UserCustom;
import com.startcaft.ssm.po.extension.UserQueryVo;

public interface UserService {
	
	/**往一个部门中添加一个用户**/
	public CommitMessage insertUser(Integer departId,UserCustom userCustom) throws Exception;
	
	/**获取一个部门下的所有用户，分页**/
	public List<UserCustom> findUserLisyByDepart(UserQueryVo query) throws Exception;
	
	/**获取一个部门下的所有总数**/
	public int findUserLisyCount(UserQueryVo query) throws Exception;
	
	/**检查是否有重名的情况**/
	public int checkUserName(String username) throws Exception;
	
	/**删除一个用户**/
	public CommitMessage deleteUser(Integer id) throws Exception;
	
}
