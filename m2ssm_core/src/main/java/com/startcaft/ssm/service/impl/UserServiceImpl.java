package com.startcaft.ssm.service.impl;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.ssm.mapper.UserCustomMapper;
import com.startcaft.ssm.mapper.UserMapper;
import com.startcaft.ssm.po.User;
import com.startcaft.ssm.po.extension.CommitMessage;
import com.startcaft.ssm.po.extension.UserCustom;
import com.startcaft.ssm.po.extension.UserQueryVo;
import com.startcaft.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private UserCustomMapper selectMapper;
	
	@Override
	public CommitMessage insertUser(Integer departId, UserCustom userCustom)
			throws Exception {
		
		CommitMessage msg = null;
		
		if(departId == null){
			msg = new CommitMessage(false, "必须指定一个部门ID");
		}
		else{
			if(StringUtils.isEmpty(userCustom.getLoginName())){
				msg = new CommitMessage(false, "用户登录名不能为空");
			}
			else{
				int flag = this.checkUserName(userCustom.getLoginName());
				if(flag > 0){
					msg = new CommitMessage(false, "指定的用户[" + userCustom.getLoginName() + "]已经存在");
				}
				else{
					userCustom.setDepartmentId(departId);
					User user = new User();
					BeanUtils.copyProperties(user, userCustom);
					int result = this.mapper.insert(user);
					msg = result > 0 ? new CommitMessage(true, "添加用户成功") : new CommitMessage(false, "添加用户失败");
				}
			}
		}
		
		return msg;
	}

	@Override
	public int findUserLisyCount(UserQueryVo query) throws Exception {
		
		return this.selectMapper.findUserLisyCount(query);
	}

	@Override
	public int checkUserName(String username) throws Exception {
		return this.selectMapper.checkUserName(username);
	}

	@Override
	public List<UserCustom> findUserLisyByDepart(UserQueryVo query)
			throws Exception {
		
		//根据分页的参数，计算具体开始和结束条目数
		query.setStart((query.getPage()-1)*query.getRows());
		query.setEnd(query.getPage() * query.getRows());
		
		return this.selectMapper.findUserLisyByDepart(query);
	}

	@Override
	public CommitMessage deleteUser(Integer id) throws Exception {
		
		CommitMessage msg = null;
		if(null == id){
			msg = new CommitMessage(false, "请指定要删除的用户");
		}
		else{
			int result = this.mapper.deleteByPrimaryKey(id);
			msg = result > 0 ? new CommitMessage(true, "删除用户成功") : new CommitMessage(false, "删除用户失败");
		}
		return msg;
	}
}
