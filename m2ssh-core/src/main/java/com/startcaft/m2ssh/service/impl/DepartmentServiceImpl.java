package com.startcaft.m2ssh.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.m2ssh.comm.CommitMessage;
import com.startcaft.m2ssh.dao.DepartmentDao;
import com.startcaft.m2ssh.entity.Department;
import com.startcaft.m2ssh.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departDao;

	public CommitMessage insertDepart(Department depart) throws Exception {
		
		CommitMessage msg = null;
		
		if(StringUtils.isEmpty(depart.getName())){
			msg = new CommitMessage("用户名不能为空", false);
		}
		else{
			long count = this.departDao.findCountByHQL("select count(*) from Department d where d.name=?", depart.getName());
			if(count > 0){
				msg = new CommitMessage("指定的部门[" + depart.getName() + "]已经存在", false);
			}
			else{
				this.departDao.save(depart);
				msg = new CommitMessage("添加部门[" + depart.getName() + "]成功", true);
			}
		}
		
		return msg;
	}

	public List<Department> getAllDepart() throws Exception {
		
		String hql = "select d from Department d";
		List<Department> lists = this.departDao.findByHQL(hql);
		return lists;
	}
}
