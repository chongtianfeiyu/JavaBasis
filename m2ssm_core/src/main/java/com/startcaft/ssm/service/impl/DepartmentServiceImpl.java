package com.startcaft.ssm.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.ssm.mapper.DepartmentCustomMapper;
import com.startcaft.ssm.mapper.DepartmentMapper;
import com.startcaft.ssm.po.Department;
import com.startcaft.ssm.po.extension.CommitMessage;
import com.startcaft.ssm.po.extension.DepartmentCustom;
import com.startcaft.ssm.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departMapper;
	
	@Autowired
	private DepartmentCustomMapper customMapper;
	
	public CommitMessage insertDepartment(Department depart) throws Exception {
		
		CommitMessage msg = null;
		
		if(depart.getApplicationId() == null){
			msg = new CommitMessage(false, "缺少应用程序主键");
		}
		else{
			if(depart.getParentId().intValue() == 0){//插入的是根节点,则检测是否允许插入根节点
				int topCount = customMapper.findTopDepartCount(depart.getApplicationId());
				if(topCount > 0){
					msg = new CommitMessage(false, "该应用下已经有一个顶级部门节点");
				}
				else{
					msg = departMapper.insert(depart) > 0 ? new CommitMessage(true, "ok") : new CommitMessage(false, "添加失败");
				}
			}
			else{
				msg = departMapper.insert(depart) > 0 ? new CommitMessage(true, "ok") : new CommitMessage(false, "添加失败");
			}
		}
		
		return msg;
	}

	@Override
	public CommitMessage update(Department depart) throws Exception {
		
		int result = departMapper.updateByPrimaryKey(depart);
		
		CommitMessage msg = result > 0 ? new CommitMessage(true, "ok") : new CommitMessage(false, "更新失败");
		
		return msg;
	}

	@Override
	public Department getDepartment(Integer id) throws Exception {
		
		Department depart = null;
		
		depart = departMapper.selectByPrimaryKey(id);
		
		return depart;
	}

	@Override
	public List<DepartmentCustom> getAllDepartment(int appId) throws Exception {
		
		return customMapper.findDepartListByApp(appId);
	}

	@Override
	public CommitMessage deleteDepartment(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
