package com.startcaft.ssm.service;

import com.startcaft.ssm.po.Application;
import com.startcaft.ssm.po.extension.CommitMessage;

public interface ApplicationService {
	
	/**新增一条系统信息,不允许有相同的应用程序名**/
	public CommitMessage insertApplication(Application app) throws Exception;
	
	/**检查是否有重名的应用程序名称**/
	public boolean checkAppNameEquals(String appName) throws Exception;
}
