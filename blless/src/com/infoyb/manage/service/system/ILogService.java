package com.cm.manage.service.system;

import java.util.List;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.system.Log;

public interface ILogService extends IBaseService{
	public void insert(Log log);
	public void insertListLog(List<Log> logList);

}
