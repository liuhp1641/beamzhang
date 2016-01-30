package com.cm.manage.dao.base.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.base.IManageLogDao;
import com.cm.manage.model.system.ManagesLog;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.util.lottery.LotteryList;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.order.OrderVO;
import com.cm.manage.vo.system.ManagesLogVO;

@Repository
public class ManageLogDaoImpl implements IManageLogDao {

	@Autowired
	private IBaseDao<ManagesLog> manageLogDao;
	
	@Autowired
	private IBaseDao<Object> baseDao;
	
 	@Override
	public void manageLogSave(ManagesLog log) {
		manageLogDao.save(log);
	}

	
	/**
	 * 操作日志列表
	 * @param dg
	 * @param log
	 * @return
	 */
	public EasyuiDataGridJson manageLogList(EasyuiDataGrid dg, ManagesLogVO log){
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuffer str=new StringBuffer("select ADMIN_NAME,TYPE,DESCRIPTION,IP,CREATE_TIME,OPERATING_TYPE from SYS_MANAGES_LOG where 1=1 ");
		List<Object> values = new ArrayList<Object>();
        if (log != null) {// 添加查询条件
        	boolean flag=log.isFlag();
            //客服名称
            if (CommonUtil.isNotEmpty(log.getAdminName())) {
            	if (flag) {
					str.append(" and ADMIN_NAME like ? ");
					values.add("%" + log.getAdminName().toString() + "%");
				} else {
					str.append(" and ADMIN_NAME = ? ");
					values.add(log.getAdminName().toString());
				}
            }
            //操作类型
            if (CommonUtil.isNotEmpty(log.getOperatingType())) {
				    str.append(" and OPERATING_TYPE = ? ");
					values.add(log.getOperatingType());
            }
           // 操作开始时间
 			if (CommonUtil.isNotEmpty(log.getOperateStart())) {
 				str.append(" and  CREATE_TIME >= ?");
 				 Calendar cal = Calendar.getInstance();
                 cal.setTime(DateUtil.format(log.getOperateStart(), "yy-MM-dd"));
                 cal.set(Calendar.HOUR_OF_DAY, 0);
                 cal.set(Calendar.MINUTE, 0);
                 cal.set(Calendar.SECOND, 0); 
                 values.add(cal.getTime());
 			}
 			if (CommonUtil.isNotEmpty(log.getOperateEnd())) {
 				str.append( " and CREATE_TIME <= ? ");
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(log.getOperateEnd(),"yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				values.add(cal.getTime());
 			}
        }
        String totalHql = " select count(*)  from (" + str.toString() + ")";
		j.setTotal(baseDao.countBySql(totalHql, values).longValue());// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			String sort = "";
			if (dg.getSort().toString().equalsIgnoreCase("createTime")) {
				sort = "CREATE_TIME";
			}
			
			if (!sort.equals("")) {
				str.append(" order by " + sort + " " + dg.getOrder());
			}
		}else{
			str.append(" order by  CREATE_TIME desc");
		}

		List<Map> operateMapList = baseDao.findBySql(str.toString(), values,
				dg.getPage(), dg.getRows());// 查询分页
		List<ManagesLogVO> operatesList = new ArrayList<ManagesLogVO>();
		if (operateMapList != null && operateMapList.size() > 0) {// 转换模型
			for (Map map : operateMapList) {
				ManagesLogVO logVo = new ManagesLogVO();
				logVo.setAdminName((String)map.get("ADMIN_NAME"));
				Date createTime = (Date) map.get("CREATE_TIME");
				logVo.setCreateTime(DateUtil.format(createTime));
				logVo.setDescription((String)map.get("DESCRIPTION"));
				logVo.setIp((String)map.get("IP"));
				logVo.setOperatingType(((BigDecimal) map.get("OPERATING_TYPE")).intValue());
				operatesList.add(logVo);
			}
		}
 		j.setRows(operatesList);	
		return j;
	}

}
