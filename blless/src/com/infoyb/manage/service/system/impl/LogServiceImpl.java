package com.cm.manage.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.system.ILogDao;
import com.cm.manage.service.system.ILogService;
import com.cm.manage.vo.system.Log;
@Service("logService")
public class LogServiceImpl implements ILogService {

	@Autowired
	private ILogDao logDao;
	@Override
	public void insert(Log log) {
		logDao.save(log);
	}
	@Override
	@Transactional
	public void insertListLog(List<Log> logList) {
		for(Log log : logList){
			logDao.save(log);
		}
		
	}

}
