package com.cm.manage.dao.operate.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.operate.IRebateDao;
import com.cm.manage.dao.order.ILotteryControlDao;
import com.cm.manage.model.order.LotteryControl;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.operate.TaskRebateUserVO;
import com.cm.manage.vo.operate.TaskRebateVO;
import com.cm.manage.vo.score.ScoreQutzUserVO;

@Repository
public class RebateDaoImpl implements IRebateDao {
	
	@Autowired
	private IBaseDao<Object> rebateDao;
	
	@Autowired
	private ILotteryControlDao lotteryControlDao;

	/**
	 * 返利方案列表
	 * @param dg
	 * @return
	 */
	@Override
	public EasyuiDataGridJson rebateList(EasyuiDataGrid dg,TaskRebateVO rebateVo) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder str=new StringBuilder("select r.REBATE_ID,r.REBATE_NAME,r.LOTTERY_CODES,r.OUT_USER_CODE,r.VIP_LOW,r.VIP_HIGH,r.SCORE_RATE,r.PRE_RATE,");
		str.append(" r.START_TIME,r.END_TIME,r.STATUS,u.USER_NAME  from TASK_REBATE r left join USER_MEMBER u on r.OUT_USER_CODE=u.USER_CODE and u.MEMBER_TYPE=1 where 1=1");
		List<Object> values = new ArrayList<Object>();
        if (rebateVo != null) {
            // 是否模糊查询
            boolean flag = rebateVo.isFlag();
            // 活动编号
            if (CommonUtil.isNotEmpty(rebateVo.getRebateId())) {
                if (flag) {
                    str.append(" and r.REBATE_ID like ? ");
                    values.add("%" + rebateVo.getRebateId().trim() + "%");
                } else {
                    str.append(" and r.REBATE_ID = ? ");
                    values.add(rebateVo.getRebateId().trim());
                }
            }
            //活动名称
            if (CommonUtil.isNotEmpty(rebateVo.getRebateName())) {
                if (flag) {
                    str.append(" and r.REBATE_NAME like ? ");
                    values.add("%" + rebateVo.getRebateName().trim() + "%");
                } else {
                    str.append(" and r.REBATE_NAME = ? ");
                    values.add(rebateVo.getRebateName().trim());
                }
            }
           
            //活动状态
            if (CommonUtil.isNotEmpty(rebateVo.getStatus())) {
            	if(rebateVo.getStatus()==0){
            		str.append(" and r.STATUS = 0 ");
            	}
            	if(rebateVo.getStatus()==1){
            		str.append(" and r.STATUS = 1 and r.START_TIME > sysdate ");
            	}
            	if(rebateVo.getStatus()==2){
            		str.append(" and r.STATUS = 1 and r.START_TIME <= sysdate ");
            	}
            	if(rebateVo.getStatus()==3){
            		str.append(" and r.STATUS = 2 ");
            	}
            }
           
            //开始时间start
            if (CommonUtil.isNotEmpty(rebateVo.getStartTimeStart())) {
                str.append(" and r.START_TIME >= ? ");
                values.add(DateUtil.format(rebateVo.getStartTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //开始时间end
            if (CommonUtil.isNotEmpty(rebateVo.getStartTimeEnd())) {
                str.append(" and r.START_TIME <= ? ");
                values.add(DateUtil.format(rebateVo.getStartTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }

            //结束时间start
            if (CommonUtil.isNotEmpty(rebateVo.getEndTimeStart())) {
                str.append(" and r.END_TIME >= ? ");
                values.add(DateUtil.format(rebateVo.getEndTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //结束时间end
            if (CommonUtil.isNotEmpty(rebateVo.getEndTimeEnd())) {
                str.append(" and r.END_TIME <= ? ");
                values.add(DateUtil.format(rebateVo.getEndTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }
        }
	    
	    String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(rebateDao.countBySql(totalHql, values).longValue());// 设置总记录数
        
	    str.append(" order by r.START_TIME desc");
	    List<Map> mapList = rebateDao.findBySql(str.toString(),values,dg.getPage(), dg.getRows());
	    List<TaskRebateVO> rebateList=new ArrayList<TaskRebateVO>();
	    if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
            	TaskRebateVO rebate=new TaskRebateVO();
            	String rebateId=(String) map.get("REBATE_ID");
            	rebate.setRebateId(rebateId);
            	String rebateName=(String) map.get("REBATE_NAME");
            	rebate.setRebateName(rebateName);
            	
            	String outUserCode=(String) map.get("OUT_USER_CODE");
            	rebate.setOutUserCode(outUserCode);
            	String outUserName=(String) map.get("USER_NAME");
            	rebate.setOutUserName(outUserName);
            	
            	String lotteryCodes=(String) map.get("LOTTERY_CODES");
            	if(CommonUtil.isNotEmpty(lotteryCodes)){
            		String lotteryNames="";
            		if("0000".equals(lotteryCodes)){
            			lotteryNames+="全部"+",";
            		}else{
            			String[] lotterys=lotteryCodes.split(",");
            			for(int i=0;i<lotterys.length;i++){
            				LotteryControl lotteryControl=lotteryControlDao.getLotteryControl(lotterys[i]);
            				if(CommonUtil.isNotEmpty(lotteryControl)){
            					lotteryNames+=lotteryControl.getLotteryName()+",";
            					
            				}
            			}
            			
            		}
            		
            		rebate.setLotteryNames(lotteryNames.substring(0, lotteryNames.length()-1));
            		rebate.setLotteryCodes(lotteryCodes);
            	}
            	BigDecimal vipLow=(BigDecimal) map.get("VIP_LOW");
            	rebate.setVipLow(vipLow.intValue());
            	BigDecimal vipHigh=(BigDecimal) map.get("VIP_HIGH");
            	rebate.setVipHigh(vipHigh.intValue());
            	BigDecimal scoreRate=(BigDecimal) map.get("SCORE_RATE");
            	rebate.setScoreRate(scoreRate.doubleValue());
            	BigDecimal preRate=(BigDecimal) map.get("PRE_RATE");
            	rebate.setPreRate(preRate.doubleValue());
            	Date startTime = (Date) map.get("START_TIME");
            	rebate.setStartTime(DateUtil.format(startTime,"yy-MM-dd HH:mm:ss"));
                Date endTime = (Date) map.get("END_TIME");
                rebate.setEndTime(DateUtil.format(endTime,"yy-MM-dd HH:mm:ss"));
                BigDecimal status = (BigDecimal) map.get("STATUS");
                rebate.setStatus(status.intValue());
                rebateList.add(rebate);
            }
            
	    }
	    j.setRows(rebateList);
        return j;
	}
	/**
	 * 返利方案保存
	 * @param rebate
	 * @return
	 */
	public boolean saveRebate(TaskRebateVO rebate){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	     String rebateId = rebate.getRebateId();
	     int result=0;
	        if (CommonUtil.isNotEmpty(rebateId)) {
	            StringBuffer str = new StringBuffer("UPDATE TASK_REBATE SET REBATE_NAME=?,LOTTERY_CODES=?,SCORE_RATE=?,PRE_RATE=?,OUT_USER_CODE=?,START_TIME=?,END_TIME=?,");
	            str.append(" VIP_LOW=?,VIP_HIGH=? ,UPDATE_TIME=sysdate where REBATE_ID=?");
	            List<Object> values = new ArrayList<Object>();
	            values.add(rebate.getRebateName());
	            values.add(rebate.getLotteryCodes());
	            values.add(rebate.getScoreRate());
	            values.add(rebate.getPreRate());
	            values.add(rebate.getOutUserCode());
	            values.add(DateUtil.format(rebate.getStartTime(), "yy-MM-dd HH:mm:ss"));
	            if(CommonUtil.isNotEmpty(rebate.getEndTime())){
	            	values.add(DateUtil.format(rebate.getEndTime(), "yy-MM-dd HH:mm:ss"));
	            }else{
	            	values.add("");
	            }
	            values.add(rebate.getVipLow() == null ? 0 : rebate.getVipLow());
	            values.add(rebate.getVipHigh() == null ? 99 : rebate.getVipHigh());
	            values.add(rebateId);
	            result=rebateDao.executeSql(str.toString(), values);

	        } else {
	        	rebateId = formatter.format(new Date()) + CommonUtil.random(6);
	            StringBuffer str = new StringBuffer("insert into TASK_REBATE(ID,REBATE_ID,REBATE_NAME,LOTTERY_CODES,SCORE_RATE,PRE_RATE,OUT_USER_CODE,START_TIME,END_TIME,");
	            str.append(" VIP_LOW,VIP_HIGH,STATUS,TYPE,CREATE_TIME,UPDATE_TIME) values (SEQ_TASK_REBATE.NEXTVAL,?,?,?,?,?,?,?,?,?,?,0,0,sysdate,sysdate)");
	            List<Object> values = new ArrayList<Object>();
	            values.add(rebateId);
	            values.add(rebate.getRebateName());
	            values.add(rebate.getLotteryCodes());
	            values.add(rebate.getScoreRate());
	            values.add(rebate.getPreRate());
	            values.add(rebate.getOutUserCode());
	            values.add(DateUtil.format(rebate.getStartTime(), "yy-MM-dd HH:mm:ss"));
	            if(CommonUtil.isNotEmpty(rebate.getEndTime())){
	            	values.add(DateUtil.format(rebate.getEndTime(), "yy-MM-dd HH:mm:ss"));
	            }else{
	            	values.add("");
	            }
	            values.add(rebate.getVipLow() == null ? 0 : rebate.getVipLow());
	            values.add(rebate.getVipHigh() == null ? 99 : rebate.getVipHigh());
	            result=rebateDao.executeSql(str.toString(), values);
	        }
	        if(result>0){
	        	return true;
	        }
		return false;
	}

	/**
	 * 判断返利方案是否有重复
	 * @param rebate
	 * @return
	 */
	public TaskRebateVO isExistRebate(String lotteryCodes,Integer vipLow,Integer vipHigh,String startTime,String endTime) {
		StringBuilder str=new StringBuilder("select REBATE_ID,REBATE_NAME,LOTTERY_CODES,VIP_LOW,VIP_HIGH,SCORE_RATE,PRE_RATE,");
		str.append(" START_TIME,END_TIME,STATUS from TASK_REBATE where STATUS=1");
		List<Object> values = new ArrayList<Object>();
        if (CommonUtil.isNotEmpty(lotteryCodes)) {
            str.append(" and LOTTERY_CODES=? ");
            values.add(lotteryCodes);
        }
        if (CommonUtil.isNotEmpty(vipLow)) {
            str.append(" and VIP_LOW=? ");
            values.add(vipLow);
        }
        if (CommonUtil.isNotEmpty(vipHigh)) {
            str.append(" and VIP_HIGH=? ");
            values.add(vipHigh);
        }
       /* if (CommonUtil.isNotEmpty(startTime)) {
            str.append(" and START_TIME=? ");
            values.add(DateUtil.format(startTime, "yy-MM-dd HH:mm:ss"));
        }
        if (CommonUtil.isNotEmpty(endTime)) {
            str.append(" and END_TIME=? ");
            values.add(DateUtil.format(endTime, "yy-MM-dd HH:mm:ss"));
        }*/
        List<Map> list=rebateDao.findBySql(str.toString(), values);
        TaskRebateVO vo=null;
        if(CommonUtil.isNotEmpty(list)){
        	for(Map map :list){
        		TaskRebateVO rebate=new TaskRebateVO();
        		String rebateId=(String) map.get("REBATE_ID");
        		rebate.setRebateId(rebateId);
        		String rebateName=(String) map.get("REBATE_NAME");
        		rebate.setRebateName(rebateName);
        		
        		BigDecimal scoreRate=(BigDecimal) map.get("SCORE_RATE");
        		rebate.setScoreRate(scoreRate.doubleValue());
        		BigDecimal preRate=(BigDecimal) map.get("PRE_RATE");
        		rebate.setPreRate(preRate.doubleValue());
        		
        		BigDecimal status = (BigDecimal) map.get("STATUS");
        		rebate.setStatus(status.intValue());
        		
        		Date starttime = (Date) map.get("START_TIME");
            	rebate.setStartTime(DateUtil.format(starttime,"yy-MM-dd HH:mm:ss"));
                Date endtime = (Date) map.get("END_TIME");
                rebate.setEndTime(DateUtil.format(endtime,"yy-MM-dd HH:mm:ss"));
                if(startTime.compareTo(DateUtil.format(endtime,"yy-MM-dd HH:mm:ss"))<=0){
                	vo=rebate;
                	break;
                }
        		
        	}
        }
       return vo;
	}
	
	/**
	 * 更改返利方案的状态
	 * @param rebateId
	 * @param status
	 * @return
	 */
	public boolean updateActivityStatus(String rebateId,Integer status){
		StringBuffer str = new StringBuffer("UPDATE TASK_REBATE set STATUS=?,UPDATE_TIME=sysdate where REBATE_ID=? ");
        List<Object> values = new ArrayList<Object>();
        values.add(status);
        values.add(rebateId);
        int result = rebateDao.executeSql(str.toString(), values);
        if (result > 0) {
            return true;
        }
        return false;
	}
	/**
	 * 返利方案开始
	 * @param rebateId
	 * @return
	 */
	public boolean activityStart(String rebateId){
		StringBuffer str = new StringBuffer("UPDATE TASK_REBATE set START_TIME=sysdate, UPDATE_TIME=sysdate where REBATE_ID=? ");
        List<Object> values = new ArrayList<Object>();
        values.add(rebateId);
        int result = rebateDao.executeSql(str.toString(), values);
        if (result > 0) {
            return true;
        }
        return false;
	}
	
	/**
	 * 返利方案详情
	 * @param rebateId
	 * @return
	 */
	public TaskRebateVO rebateInfo(String rebateId){
		StringBuilder str=new StringBuilder("select r.REBATE_ID,r.REBATE_NAME,r.LOTTERY_CODES,r.OUT_USER_CODE,r.VIP_LOW,r.VIP_HIGH,r.SCORE_RATE,r.PRE_RATE,");
		str.append(" r.START_TIME,r.END_TIME,r.STATUS,u.USER_NAME  from TASK_REBATE r left join USER_MEMBER u on r.OUT_USER_CODE=u.USER_CODE and u.MEMBER_TYPE=1 where 1=1");
		List<Object> values = new ArrayList<Object>();
        str.append(" and r.REBATE_ID=? ");
        values.add(rebateId);
        List<Map> list=rebateDao.findBySql(str.toString(), values);
        if(CommonUtil.isNotEmpty(list)){
        	Map map=list.get(0);
        	TaskRebateVO rebate=new TaskRebateVO();
        	rebate.setRebateId(rebateId);
        	String rebateName=(String) map.get("REBATE_NAME");
        	rebate.setRebateName(rebateName);
        	String outUserCode=(String) map.get("OUT_USER_CODE");
        	rebate.setOutUserCode(outUserCode);
        	String outUserName=(String) map.get("USER_NAME");
        	rebate.setOutUserName(outUserName);
        	BigDecimal vipLow=(BigDecimal) map.get("VIP_LOW");
        	rebate.setVipLow(vipLow.intValue());
        	BigDecimal vipHigh=(BigDecimal) map.get("VIP_HIGH");
        	rebate.setVipHigh(vipHigh.intValue());
        	BigDecimal scoreRate=(BigDecimal) map.get("SCORE_RATE");
        	rebate.setScoreRate(scoreRate.doubleValue());
        	BigDecimal preRate=(BigDecimal) map.get("PRE_RATE");
        	rebate.setPreRate(preRate.doubleValue());
        	Date startTime = (Date) map.get("START_TIME");
        	rebate.setStartTime(DateUtil.format(startTime,"yy-MM-dd HH:mm:ss"));
            Date endTime = (Date) map.get("END_TIME");
            rebate.setEndTime(DateUtil.format(endTime,"yy-MM-dd HH:mm:ss"));
            BigDecimal status = (BigDecimal) map.get("STATUS");
            rebate.setStatus(status.intValue());
            String lotteryCodes=(String) map.get("LOTTERY_CODES");
            rebate.setLotteryCodes(lotteryCodes);
        	if(CommonUtil.isNotEmpty(lotteryCodes)){
        		String lotteryNames="";
        		if("0000".equals(lotteryCodes)){
        			lotteryNames+="全部"+",";
        		}else{
        			String[] lotterys=lotteryCodes.split(",");
        			for(int i=0;i<lotterys.length;i++){
        				LotteryControl lotteryControl=lotteryControlDao.getLotteryControl(lotterys[i]);
        				if(CommonUtil.isNotEmpty(lotteryControl)){
        					lotteryNames+=lotteryControl.getLotteryName()+",";
        					
        				}
        			}
        			
        		}
        		rebate.setLotteryNames(lotteryNames.substring(0, lotteryNames.length()-1));
        		rebate.setLotteryCodes(lotteryCodes);
        	}
            return rebate;
        }
       return null;
	
	}
	/**
	 * 返利方案日志列表
	 * @param dg
	 * @param rebateUser
	 * @return
	 */
	public EasyuiDataGridJson rebateLog(EasyuiDataGrid dg,TaskRebateUserVO rebateUser) {

        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuffer str = new StringBuffer(" select r.REBATE_USER_ID,r.REBATE_ID,r.REBATE_NAME,u1.USER_NAME,r.USER_CODE,u2.USER_NAME outUserName,r.OUT_USER_CODE ,");
        str.append(" r.ORDER_ID,r.STATUS,r.CREATE_TIME,r.UPDATE_TIME,r.RETURN_SCORE,r.RETURN_PRE  ");
        str.append(" from TASK_REBATE_USER r  left join USER_MEMBER u1 on r.USER_CODE=u1.USER_CODE ");
        str.append(" left join USER_MEMBER u2 on r.OUT_USER_CODE =u2.USER_CODE and u2.MEMBER_TYPE=1  where 1=1 ");
        List<Object> values = new ArrayList<Object>();
        if (rebateUser != null) {
            // 是否模糊查询
            boolean flag = rebateUser.isFlag();
            // 流水编号
            if (CommonUtil.isNotEmpty(rebateUser.getRebateUserId())) {
                if (flag) {
                    str.append(" and r.REBATE_USER_ID like ? ");
                    values.add("%" + rebateUser.getRebateUserId().trim() + "%");
                } else {
                    str.append(" and r.REBATE_USER_ID = ? ");
                    values.add(rebateUser.getRebateUserId().trim());
                }
            }
            // 用户名称
            if (CommonUtil.isNotEmpty(rebateUser.getUserName())) {
                if (flag) {
                    str.append(" and u1.user_name like ? ");
                    values.add("%" + rebateUser.getUserName().trim() + "%");
                } else {
                    str.append(" and u1.user_name = ? ");
                    values.add(rebateUser.getUserName().trim());
                }
            }
            // 返利说明
            if (CommonUtil.isNotEmpty(rebateUser.getRebateName())) {
                if (flag) {
                    str.append(" and r.REBATE_NAME like ? ");
                    values.add("%" + rebateUser.getRebateName().trim() + "%");
                } else {
                    str.append(" and r.REBATE_NAME = ? ");
                    values.add(rebateUser.getRebateName().trim());
                }
            }
           
            //返利状态
            if (CommonUtil.isNotEmpty(rebateUser.getStatus())) {
                str.append(" and r.STATUS = ? ");
                values.add(rebateUser.getStatus());
            }
            //创建时间start
            if (CommonUtil.isNotEmpty(rebateUser.getCreateTimeStart())) {
                str.append(" and r.CREATE_TIME >= ? ");
                values.add(DateUtil.format(rebateUser.getCreateTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //创建时间end
            if (CommonUtil.isNotEmpty(rebateUser.getCreateTimeEnd())) {
                str.append(" and r.CREATE_TIME <= ? ");
                values.add(DateUtil.format(rebateUser.getCreateTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }

            //返利时间start
            if (CommonUtil.isNotEmpty(rebateUser.getUpdateTimeStart())) {
                str.append(" and r.UPDATE_TIME >= ? ");
                values.add(DateUtil.format(rebateUser.getUpdateTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //返利时间end
            if (CommonUtil.isNotEmpty(rebateUser.getUpdateTimeEnd())) {
                str.append(" and r.UPDATE_TIME <= ? ");
                values.add(DateUtil.format(rebateUser.getUpdateTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }
        }
        String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(rebateDao.countBySql(totalHql, values).longValue());// 设置总记录数

        str.append(" order by  r.CREATE_TIME desc");
        List<Map> mapList = rebateDao.findBySql(str.toString(), values,
                dg.getPage(), dg.getRows());// 查询分页
        List<TaskRebateUserVO> rebateUserLogs = new ArrayList<TaskRebateUserVO>();
        if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
            	TaskRebateUserVO vo = new TaskRebateUserVO();
            	vo.setRebateUserId((String) map.get("REBATE_USER_ID"));
            	String rebateId=(String) map.get("REBATE_ID");
        		vo.setRebateId(rebateId);
            	vo.setRebateName((String) map.get("REBATE_NAME"));
                vo.setUserCode((String) map.get("USER_CODE"));
                vo.setUserName((String) map.get("USER_NAME"));
                vo.setOutUserCode((String) map.get("OUT_USER_CODE"));
                vo.setOutUserName((String) map.get("OUTUSERNAME"));
                Date createTime = (Date) map.get("CREATE_TIME");
                vo.setCreateTime(DateUtil.format(createTime));
                Date updateTime = (Date) map.get("UPDATE_TIME");
                vo.setUpdateTime(DateUtil.format(updateTime));
                BigDecimal status = (BigDecimal) map.get("STATUS");
                vo.setStatus(status.intValue());
                BigDecimal returnScore=(BigDecimal) map.get("RETURN_SCORE");
                vo.setReturnScore(returnScore.doubleValue());
                
                BigDecimal returnPre=(BigDecimal) map.get("RETURN_PRE");
                vo.setReturnPre(returnPre.doubleValue());
                vo.setOrderId((String)map.get("ORDER_ID"));
                
                rebateUserLogs.add(vo);
            }
        }
        j.setRows(rebateUserLogs);// 设置返回的行
        return j;
    
	}
	
	/**
	 * 返利日志汇总
	 * @param rebateUser
	 * @return
	 */
	public Map rebateCount(TaskRebateUserVO rebateUser){
		    StringBuilder str=new StringBuilder("select sum(r.RETURN_SCORE) RETURN_SCORE, sum(r.RETURN_PRE) RETURN_PRE ");
		    str.append(" from TASK_REBATE_USER r  left join USER_MEMBER u1 on r.USER_CODE=u1.USER_CODE ");
	        str.append(" left join USER_MEMBER u2 on r.OUT_USER_CODE =u2.USER_CODE and u2.MEMBER_TYPE=1  where 1=1 ");
	        List<Object> values = new ArrayList<Object>();
	        if (rebateUser != null) {
	            // 是否模糊查询
	            boolean flag = rebateUser.isFlag();
	            // 流水编号
	            if (CommonUtil.isNotEmpty(rebateUser.getRebateUserId())) {
	                if (flag) {
	                    str.append(" and r.REBATE_USER_ID like ? ");
	                    values.add("%" + rebateUser.getRebateUserId().trim() + "%");
	                } else {
	                    str.append(" and r.REBATE_USER_ID = ? ");
	                    values.add(rebateUser.getRebateUserId().trim());
	                }
	            }
	            // 用户名称
	            if (CommonUtil.isNotEmpty(rebateUser.getUserName())) {
	                if (flag) {
	                    str.append(" and u1.user_name like ? ");
	                    values.add("%" + rebateUser.getUserName().trim() + "%");
	                } else {
	                    str.append(" and u1.user_name = ? ");
	                    values.add(rebateUser.getUserName().trim());
	                }
	            }
	            // 返利说明
	            if (CommonUtil.isNotEmpty(rebateUser.getRebateName())) {
	                if (flag) {
	                    str.append(" and r.REBATE_NAME like ? ");
	                    values.add("%" + rebateUser.getRebateName().trim() + "%");
	                } else {
	                    str.append(" and r.REBATE_NAME = ? ");
	                    values.add(rebateUser.getRebateName().trim());
	                }
	            }
	           
	            //返利状态
	            if (CommonUtil.isNotEmpty(rebateUser.getStatus())) {
	                str.append(" and r.STATUS = ? ");
	                values.add(rebateUser.getStatus());
	            }
	            //创建时间start
	            if (CommonUtil.isNotEmpty(rebateUser.getCreateTimeStart())) {
	                str.append(" and r.CREATE_TIME >= ? ");
	                values.add(DateUtil.format(rebateUser.getCreateTimeStart(), "yy-MM-dd HH:mm:ss"));
	            }
	            //创建时间end
	            if (CommonUtil.isNotEmpty(rebateUser.getCreateTimeEnd())) {
	                str.append(" and r.CREATE_TIME <= ? ");
	                values.add(DateUtil.format(rebateUser.getCreateTimeEnd(), "yy-MM-dd HH:mm:ss"));
	            }

	            //返利时间start
	            if (CommonUtil.isNotEmpty(rebateUser.getUpdateTimeStart())) {
	                str.append(" and r.UPDATE_TIME >= ? ");
	                values.add(DateUtil.format(rebateUser.getUpdateTimeStart(), "yy-MM-dd HH:mm:ss"));
	            }
	            //返利时间end
	            if (CommonUtil.isNotEmpty(rebateUser.getUpdateTimeEnd())) {
	                str.append(" and r.UPDATE_TIME <= ? ");
	                values.add(DateUtil.format(rebateUser.getUpdateTimeEnd(), "yy-MM-dd HH:mm:ss"));
	            }
	        }
	        List<Map> mapList = rebateDao.findBySql(str.toString(), values);
	        if (CommonUtil.isNotEmpty(mapList)) {
	            return mapList.get(0);
	        }
	        return null;
	}
}
