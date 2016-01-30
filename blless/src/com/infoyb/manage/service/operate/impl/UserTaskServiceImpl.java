package com.cm.manage.service.operate.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.IUserTaskDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IUserTaskService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.UserTaskVO;

@Service("userTaskService")
public class UserTaskServiceImpl extends BaseServiceImpl implements
		IUserTaskService {

	private static Logger logger = Logger.getLogger(UserTaskServiceImpl.class);
	@Autowired
	private IUserTaskDao userTaskDao;
	@Override
	public EasyuiDataGridJson queryTaskList(EasyuiDataGrid dg,
			UserTaskVO userTaskVO) {
		return userTaskDao.queryTaskList(dg, userTaskVO);
	}
	@Override
	public Map countByFunddingType(UserTaskVO userTaskVO) {
		return userTaskDao.countByFunddingType(userTaskVO);
	}
	
}
