package com.cm.manage.dao.operate.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.IMatchForMappingDao;
import com.cm.manage.model.order.MatchForMapping;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
@Repository
public class MatchForMappingDaoImpl implements IMatchForMappingDao {

	@Autowired
	public IBaseDao<MatchForMapping> matchDao;
	@Override
	public EasyuiDataGridJson matchForMappingList(EasyuiDataGrid dg) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		String hql=" from MatchForMapping ";
		String totalHql = " select count(*) " + hql;
		j.setTotal(matchDao.count(totalHql));
		
		List<MatchForMapping> matchs = matchDao.find(hql, dg.getPage(), dg.getRows());
		j.setRows(matchs);
		return j;
	}
	/**
	 * 更改赛事
	 * @param matchName
	 * @param matchShortName
	 */
	public void updateMatchShortName(String matchName, String matchShortName){
		String sql="UPDATE TMS_MATCH_FOR_MAPPING SET MATCH_SHORT_NAME=? WHERE MATCH_NAME=?";
		List<Object> values = new ArrayList<Object>();
		values.add(matchShortName);
		values.add(matchName);
		matchDao.executeSql(sql,values);
	}
}
