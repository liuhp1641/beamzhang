package com.cm.manage.service.operate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.operate.IMatchForMappingDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.operate.IMatchForMappingService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
@Service("matchService")
public class MatchForMappingServiceImpl extends BaseServiceImpl implements
		IMatchForMappingService {

	@Autowired
	private IMatchForMappingDao matchDao;
	/**
	 * 赛事列表
	 * @return
	 */
	public EasyuiDataGridJson matchForMappingList(EasyuiDataGrid dg){
		return matchDao.matchForMappingList(dg);
	}

	/**
	 * 更改赛事
	 * @param matchName
	 * @param matchShortName
	 */
	public void updateMatchShortName(String matchName, String matchShortName){
		matchDao.updateMatchShortName(matchName, matchShortName);
	}
}
