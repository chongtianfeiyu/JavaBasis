package com.startcaft.ssm.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.ssm.mapper.ApplicationCustomMapper;
import com.startcaft.ssm.mapper.ApplicationMapper;
import com.startcaft.ssm.po.Application;
import com.startcaft.ssm.po.extension.ApplicationCustom;
import com.startcaft.ssm.po.extension.ApplicationQueryVo;
import com.startcaft.ssm.po.extension.CommitMessage;
import com.startcaft.ssm.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationMapper mapper;

	@Autowired
	private ApplicationCustomMapper customMapper;

	public CommitMessage insertApplication(Application app) throws Exception {

		CommitMessage msg = null;

		if (StringUtils.isEmpty(app.getAppName())) {
			msg = new CommitMessage(false, "应用程序名称不能为空");
		} 
		else {
			if (this.checkAppNameEquals(app.getAppName())) {
				msg = new CommitMessage(false, "应用程序名[" + app.getAppName()
						+ "]已经存在");
			} else {
				msg = mapper.insert(app) > 0 ? new CommitMessage(true, "ok") : new CommitMessage(false, "添加失败");
			}
		}

		return msg;
	}

	public boolean checkAppNameEquals(String appName) throws Exception {

		ApplicationQueryVo query = new ApplicationQueryVo();
		ApplicationCustom custom = new ApplicationCustom();
		custom.setAppName(appName);
		query.setApplicationCustom(custom);

		int count = customMapper.findApplicationListCount(query);

		if (count > 0) {
			return true;
		}
		return false;
	}
}
