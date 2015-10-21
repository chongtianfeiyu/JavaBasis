package com.startcaft.ssm.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.startcaft.ssm.po.extension.CommitMessage;
import com.startcaft.ssm.po.extension.DepartmentCustom;
import com.startcaft.ssm.po.extension.UserCustom;
import com.startcaft.ssm.po.extension.UserQueryVo;
import com.startcaft.ssm.service.DepartmentService;
import com.startcaft.ssm.service.UserService;

@Controller
@RequestMapping("/userDepart")
public class UserDepartController {

	@Autowired
	private DepartmentService departService;

	@Autowired
	private UserService userService;

	@RequestMapping("/index")
	public String Index() {
		// System.out.println("部门与用户管理");
		return "users/DepartUsers";
	}

	@ResponseBody
	@RequestMapping("/getAllDepart/{appId}")
	public Collection<DepartmentCustom> getAllDepart(
			@PathVariable("appId") Integer appId) throws Exception {

		return this.departService.getAllDepartment(appId);
	}
	
	@ResponseBody
	@RequestMapping("/getUserPage/{departId}")
	public Map<String, Object> getUserPage(
			@PathVariable(value="departId") Integer departId,
			@RequestParam(required=true,defaultValue="1") Integer page,
			@RequestParam(required=true,defaultValue="20") Integer rows,
			@RequestParam(required=false) String loginName) throws Exception {
		
		
		UserQueryVo query = new UserQueryVo();
		query.setPage(page);
		query.setRows(rows);
		
		UserCustom user = new UserCustom();
		user.setDepartmentId(departId);
		if(!StringUtils.isEmpty(loginName)){
			user.setLoginName(loginName);
		}
		query.setUserCustom(user);
		
		int total = this.userService.findUserLisyCount(query);
		List<UserCustom> userList = this.userService.findUserLisyByDepart(query);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total", total);
		result.put("rows", userList);

		return result;
	}
	
	@RequestMapping("/deleteUser/{userId}")
	public CommitMessage deleteUser(@PathVariable("userId") Integer id) throws Exception{
		
		CommitMessage msg = this.userService.deleteUser(id);
		
		return msg;
	}
	
	@RequestMapping(value="/user/add",method=RequestMethod.GET)
	public String addUser(){
		
		return "users/addUser";
	}
}
