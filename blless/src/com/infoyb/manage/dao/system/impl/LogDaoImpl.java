package com.cm.manage.dao.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.ILogDao;
import com.cm.manage.vo.system.Log;
@Repository("logDao")
public class LogDaoImpl implements ILogDao {

	@Autowired
	private IBaseDao<Log> logDao;
	@Override
	public void save(Log log) {
		String sql = "insert into sys_log(id,user_id,content,operation,create_time,login_ip) values(SEQ_SYS_LOG.nextval,?,?,?,sysdate,?)";
		List<Object> param = new ArrayList<Object>();
		param.add(log.getUserId());
		param.add(log.getContent());
		param.add(log.getOperation());
		param.add(log.getLoginIP());
		logDao.executeSql(sql, param);
	}

}
