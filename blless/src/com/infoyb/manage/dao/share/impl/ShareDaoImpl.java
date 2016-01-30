package com.cm.manage.dao.share.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.share.IShareDao;
import com.cm.manage.model.share.JoinReward;
import com.cm.manage.model.share.ShareModule;
import com.cm.manage.model.share.ShareReward;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.share.ShareInfoVO;
@Repository
public class ShareDaoImpl implements IShareDao {
	
	@Autowired
    private IBaseDao<Object> shareDao;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	 
	@Override	
	public EasyuiDataGridJson shareList(EasyuiDataGrid dg, ShareInfoVO shareVo) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder str=new StringBuilder("select s.SHARE_ID,s.SHARE_NAME,s.SHARE_TYPE,s.OUT_USER_CODE,s.SHARE_URL,s.PRIVATE_KEY,s.REWARD_TIMES,s.STATUS,");
		str.append(" s.START_TIME,s.END_TIME,u.USER_NAME,sr.AWARD_TYPE,sr.AMOUNT,s.SHARE_TITLE,s.SHARE_CONTENT  from SHARE_INFO s left join USER_MEMBER u on s.OUT_USER_CODE=u.USER_CODE and u.MEMBER_TYPE=1 ");
		str.append(" left join SHARE_REWARD sr on s.SHARE_ID=sr.SHARE_ID and sr.BASE_TYPE=0 where 1=1 ");
		List<Object> values = new ArrayList<Object>();
        if (shareVo != null) {
            // 是否模糊查询
            boolean flag = shareVo.isFlag();
            // 活动编号
            if (CommonUtil.isNotEmpty(shareVo.getShareId())) {
                if (flag) {
                    str.append(" and s.SHARE_ID like ? ");
                    values.add("%" + shareVo.getShareId().trim() + "%");
                } else {
                    str.append(" and s.SHARE_ID = ? ");
                    values.add(shareVo.getShareId().trim());
                }
            }
            //活动名称
            if (CommonUtil.isNotEmpty(shareVo.getShareName())) {
                if (flag) {
                    str.append(" and s.SHARE_NAME like ? ");
                    values.add("%" + shareVo.getShareName().trim() + "%");
                } else {
                    str.append(" and s.SHARE_NAME = ? ");
                    values.add(shareVo.getShareName().trim());
                }
            }
            //奖励类型
            if(CommonUtil.isNotEmpty(shareVo.getAwardType())){
            	str.append(" and sr.AWARD_TYPE=?");
            	values.add(shareVo.getAwardType());
            }
            //分享类型
            if(CommonUtil.isNotEmpty(shareVo.getShareType())){
            	str.append(" and s.SHARE_TYPE=?");
            	values.add(shareVo.getShareType());
            }
            //活动状态
            if (CommonUtil.isNotEmpty(shareVo.getStatus())) {
            	str.append(" and s.STATUS = ? ");
            	values.add(shareVo.getStatus());
            }
           
            //开始时间start
            if (CommonUtil.isNotEmpty(shareVo.getStartTimeStart())) {
                str.append(" and s.START_TIME >= ? ");
                values.add(DateUtil.format(shareVo.getStartTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //开始时间end
            if (CommonUtil.isNotEmpty(shareVo.getStartTimeEnd())) {
                str.append(" and s.START_TIME <= ? ");
                values.add(DateUtil.format(shareVo.getStartTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }

            //结束时间start
            if (CommonUtil.isNotEmpty(shareVo.getEndTimeStart())) {
                str.append(" and s.END_TIME >= ? ");
                values.add(DateUtil.format(shareVo.getEndTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //结束时间end
            if (CommonUtil.isNotEmpty(shareVo.getEndTimeEnd())) {
                str.append(" and s.END_TIME <= ? ");
                values.add(DateUtil.format(shareVo.getEndTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }
        }
	    
	    String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(shareDao.countBySql(totalHql, values).longValue());// 设置总记录数
        
	    str.append(" order by s.STATUS ");
	    List<Map> mapList = shareDao.findBySql(str.toString(),values,dg.getPage(), dg.getRows());
	    List<ShareInfoVO> shareList=new ArrayList<ShareInfoVO>();
	    if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
            	ShareInfoVO share=new ShareInfoVO();
            	String shareId=(String) map.get("SHARE_ID");
            	share.setShareId(shareId);
            	String shareName=(String) map.get("SHARE_NAME");
            	share.setShareName(shareName);
            	
            	String outUserCode=(String) map.get("OUT_USER_CODE");
            	share.setOutUserCode(outUserCode);
            	String outUserName=(String) map.get("USER_NAME");
            	share.setOutUserName(outUserName);
            	
            	String shareType=(String) map.get("SHARE_TYPE") ;
            	share.setShareType(shareType);
            	String privateKey=(String) map.get("PRIVATE_KEY") ;
            	share.setPrivateKey(privateKey);
            	String shareUrl=(String) map.get("SHARE_URL") ;
            	share.setShareUrl(shareUrl);
            	
            	Date startTime = (Date) map.get("START_TIME");
            	share.setStartTime(DateUtil.format(startTime,"yy-MM-dd HH:mm:ss"));
                Date endTime = (Date) map.get("END_TIME");
                share.setEndTime(DateUtil.format(endTime,"yy-MM-dd HH:mm:ss"));
                BigDecimal status = (BigDecimal) map.get("STATUS");
                share.setStatus(status.intValue());
                
                BigDecimal awardType=(BigDecimal) map.get("AWARD_TYPE");
                share.setAwardType(awardType==null?null:awardType.intValue());
                
                BigDecimal amount=(BigDecimal) map.get("AMOUNT");
                share.setAmount(amount==null?null:amount.doubleValue());
                share.setShareTitle((String)map.get("SHARE_TITLE"));
                share.setShareContent((String)map.get("SHARE_CONTENT"));
                shareList.add(share);
            }
            
	    }
	    j.setRows(shareList);
        return j;
	}
	
	/**
	 * 分享活动保存
	 * @param share
	 * @param options
	 */
	public void saveActivity(ShareInfoVO share,String options){
		
        String shareId = share.getShareId();
        if (CommonUtil.isNotEmpty(shareId)) {
            StringBuffer str = new StringBuffer("UPDATE SHARE_INFO SET SHARE_NAME=?,SHARE_TYPE=?,SHARE_URL=?,PRIVATE_KEY=?,OUT_USER_CODE=?,START_TIME=?,END_TIME=?,");
            str.append(" SHARE_TITLE=?,SHARE_CONTENT=?,SHARE_IMG_URL=?,UPDATE_TIME=sysdate where SHARE_ID=?");
            List<Object> values = new ArrayList<Object>();
            values.add(share.getShareName());
            values.add(share.getShareType());
            values.add(share.getShareUrl());
            if("bonus".equals(share.getShareType())){
            	values.add(shareId);
            }else{
            	values.add(share.getPrivateKey());
            }
            values.add(share.getOutUserCode());
            values.add(DateUtil.format(share.getStartTime(), "yy-MM-dd HH:mm:ss"));
            if(CommonUtil.isNotEmpty(share.getEndTime())){
            	values.add(DateUtil.format(share.getEndTime(), "yy-MM-dd HH:mm:ss"));
            }else{
            	values.add("");
            }
            values.add(share.getShareTitle());
            values.add(share.getShareContent());
            values.add(share.getShareImgUrl());
            
            values.add(shareId);
            shareDao.executeSql(str.toString(), values);

        } else {
        	shareId = formatter.format(new Date()) + CommonUtil.random(6);
            StringBuffer str = new StringBuffer("insert into SHARE_INFO(ID,SHARE_ID,SHARE_NAME,SHARE_TYPE,SHARE_URL,PRIVATE_KEY,OUT_USER_CODE,START_TIME,END_TIME,");
            str.append(" SHARE_TITLE,SHARE_CONTENT,SHARE_IMG_URL,STATUS,CREATE_TIME,UPDATE_TIME) values (SEQ_SHARE_INFO.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,0,sysdate,sysdate)");
            List<Object> values = new ArrayList<Object>();
            values.add(shareId);
            values.add(share.getShareName());
            values.add(share.getShareType());
            values.add(share.getShareUrl());
            if("bonus".equals(share.getShareType())){
            	values.add(shareId);
            }else{
            	values.add(share.getPrivateKey());
            }
            values.add(share.getOutUserCode());
            values.add(DateUtil.format(share.getStartTime(), "yy-MM-dd HH:mm:ss"));
            if(CommonUtil.isNotEmpty(share.getEndTime())){
            	values.add(DateUtil.format(share.getEndTime(), "yy-MM-dd HH:mm:ss"));
            }else{
            	values.add("");
            }
            values.add(share.getShareTitle());
            values.add(share.getShareContent());
            values.add(share.getShareImgUrl());
            shareDao.executeSql(str.toString(), values);
            share.setShareId(shareId);
        }
        updateShareReward(share, options);
        updateJoinReward(share);
	}
	public void updateShareReward(ShareInfoVO share,String options){
		String shareId = share.getShareId();
		String isBase=share.getIsBase();
		List<ShareReward> baseReward=shareRewardInfo(shareId, 0);
	    if("true".equals(isBase)){
	     	if(baseReward.size()>0){
	     		StringBuilder str=new StringBuilder("UPDATE SHARE_REWARD SET OUT_USER_CODE=?,AWARD_TYPE=?,AWARD_MAX=?,LOTTERY_CODE=?,AMOUNT=? WHERE SHARE_ID=? AND BASE_TYPE=0 ");
	     		List<Object> values = new ArrayList<Object>();
	     		values.add(share.getOutUserCode());
	     		Integer awardType=share.getShareAwardType();
	     		values.add(awardType);
	     		values.add(share.getShareAwardMax()==null?0:share.getShareAwardMax());
	     		if(awardType==2){
	     			values.add("001");
	     		}else{
	     			values.add("");
	     		}
	     		values.add(share.getShareAmount()==null?new Double(0):share.getShareAmount());
	     		values.add(shareId);
	     		shareDao.executeSql(str.toString(), values);
	     	}else{
	     		String shareRewardId = formatter.format(new Date()) + CommonUtil.random(6);
	     		StringBuilder str=new StringBuilder("INSERT INTO SHARE_REWARD (ID,SHARE_REWARD_ID,SHARE_ID,OUT_USER_CODE,BASE_TYPE,");
	     		str.append(" AWARD_TYPE,AWARD_MAX,LOTTERY_CODE,AMOUNT,CREATE_TIME) values (SEQ_SHARE_REWARD.NEXTVAL,?,?,?,0,?,?,?,?,sysdate)");
	     		List<Object> values = new ArrayList<Object>();
	     		values.add(shareRewardId);
	     		values.add(shareId);
	     		values.add(share.getOutUserCode());
	     		Integer awardType=share.getShareAwardType();
	     		values.add(awardType);
	     		values.add(share.getShareAwardMax()==null?0:share.getShareAwardMax());
	     		if(awardType==2){
	     			values.add("001");
	     		}else{
	     			values.add("");
	     		}
	     		values.add(share.getShareAmount()==null?new Double(0):share.getShareAmount());
	     		shareDao.executeSql(str.toString(), values);
	     	}
	    }else{
	    	if(baseReward.size()>0){
	    		StringBuilder str=new StringBuilder("delete from SHARE_REWARD where SHARE_ID=? AND BASE_TYPE=0 ");
	    		List<Object> values = new ArrayList<Object>();
	    		values.add(shareId);
	    		shareDao.executeSql(str.toString(), values);
	    	}
	    }
	    String isExtra=share.getIsExtra();
	    if("true".equals(isExtra)){
    	   JSONArray jsonArr = (JSONArray) JSONSerializer.toJSON(options);
           for (int i = 0; i < jsonArr.size(); i++) {
               JSONObject jsonObj = jsonArr.getJSONObject(i);
               ShareReward shareReward = (ShareReward) JSONObject.toBean(jsonObj, ShareReward.class);
               String shareRewardId = shareReward.getShareRewardId();
               if (CommonUtil.isNotEmpty(shareRewardId)) {
            	StringBuilder str=new StringBuilder("UPDATE SHARE_REWARD SET OUT_USER_CODE=?,AWARD_TYPE=?,NUM=?,LOTTERY_CODE=?,AMOUNT=? WHERE SHARE_ID=? AND SHARE_REWARD_ID=? AND BASE_TYPE=1 ");
   	     		List<Object> values = new ArrayList<Object>();
   	     		values.add(share.getOutUserCode());
   	     		Integer awardType=shareReward.getAwardType();
   	     		values.add(awardType);
   	     		values.add(shareReward.getNum());
   	     		if(awardType==2){
   	     			values.add("001");
   	     		}else{
   	     			values.add("");
   	     		}
   	     		values.add(shareReward.getAmount()==null?new Double(0):shareReward.getAmount());
   	     		values.add(shareId);
   	     		values.add(shareRewardId);
   	     	    shareDao.executeSql(str.toString(), values);
               } else {
            	    shareRewardId = formatter.format(new Date()) + CommonUtil.random(6);
            	    StringBuilder str=new StringBuilder("INSERT INTO SHARE_REWARD (ID,SHARE_REWARD_ID,SHARE_ID,OUT_USER_CODE,BASE_TYPE,");
	   	     		str.append(" AWARD_TYPE,NUM,LOTTERY_CODE,AMOUNT,CREATE_TIME) values (SEQ_SHARE_REWARD.NEXTVAL,?,?,?,1,?,?,?,?,sysdate)");
	   	     		List<Object> values = new ArrayList<Object>();
	   	     		values.add(shareRewardId);
	   	     		values.add(shareId);
	   	     		values.add(share.getOutUserCode());
	   	     		Integer awardType=shareReward.getAwardType();
	   	     		values.add(awardType);
	   	     		values.add(shareReward.getNum());
	   	     		if(awardType==2){
	   	     			values.add("001");
	   	     		}else{
	   	     			values.add("");
	   	     		}
	   	     		values.add(shareReward.getAmount()==null?new Double(0):shareReward.getAmount());
	   	     	    shareDao.executeSql(str.toString(), values);
               }

	           }
	     	
	    }else{
	    	StringBuilder str=new StringBuilder("delete from SHARE_REWARD where SHARE_ID=? AND BASE_TYPE=1 ");
    		List<Object> values = new ArrayList<Object>();
    		values.add(shareId);
    		shareDao.executeSql(str.toString(), values);
	    }
	}
	public void updateJoinReward(ShareInfoVO share){

		    String shareId = share.getShareId();
		    JoinReward joinReward=joinRewardInfo(shareId);
	     	if(CommonUtil.isNotEmpty(joinReward)){
	     		StringBuilder str=new StringBuilder("UPDATE SHARE_JOIN_REWARD SET OUT_USER_CODE=?,AWARD_TYPE=?,LOTTERY_CODE=?,AMOUNT=? WHERE SHARE_ID=?  ");
	     		List<Object> values = new ArrayList<Object>();
	     		values.add(share.getOutUserCode());
	     		Integer awardType=share.getJoinAwardType();
	     		values.add(awardType);
	     		
	     		if(awardType==2){
	     			values.add("001");
	     		}else{
	     			values.add("");
	     		}
	     		values.add(share.getJoinAmount()==null?new Double(0):share.getJoinAmount());
	     		values.add(shareId);
	     		shareDao.executeSql(str.toString(), values);
	     	}else{
	     		StringBuilder str=new StringBuilder("INSERT INTO SHARE_JOIN_REWARD (ID,SHARE_ID,OUT_USER_CODE,");
	     		str.append(" AWARD_TYPE,LOTTERY_CODE,AMOUNT,CREATE_TIME) values (SEQ_SHARE_JOIN_REWARD.NEXTVAL,?,?,?,?,?,sysdate)");
	     		List<Object> values = new ArrayList<Object>();
	     		values.add(shareId);
	     		values.add(share.getOutUserCode());
	     		Integer awardType=share.getJoinAwardType();
	     		values.add(awardType);
	     		
	     		if(awardType==2){
	     			values.add("001");
	     		}else{
	     			values.add("");
	     		}
	     		values.add(share.getJoinAmount()==null?new Double(0):share.getJoinAmount());
	     		shareDao.executeSql(str.toString(), values);
	     	}
	}
	
	/**
	 * 分享活动详情
	 * @param shareId
	 * @return
	 */
	public ShareInfoVO shareInfo(String shareId){

		List<Map> list=new ArrayList<Map>();
		StringBuilder str=new StringBuilder("select s.SHARE_ID,s.SHARE_NAME,s.SHARE_TYPE,s.OUT_USER_CODE,s.SHARE_URL,s.PRIVATE_KEY,s.REWARD_TIMES,s.STATUS,");
		str.append(" s.START_TIME,s.END_TIME,s.SHARE_TITLE,s.SHARE_CONTENT,s.SHARE_IMG_URL,u.USER_NAME  from SHARE_INFO s left join USER_MEMBER u on s.OUT_USER_CODE=u.USER_CODE and u.MEMBER_TYPE=1 ");
		str.append(" where  SHARE_ID=?  ");
		List<Object> values = new ArrayList<Object>();
		values.add(shareId);
		list=shareDao.findBySql(str.toString(), values);
		if(CommonUtil.isNotEmpty(list)){
        	Map map=list.get(0);
        	ShareInfoVO share=new ShareInfoVO();
        	share.setShareId(shareId);
        	String shareName=(String) map.get("SHARE_NAME");
        	share.setShareName(shareName);
        	
        	String outUserCode=(String) map.get("OUT_USER_CODE");
        	share.setOutUserCode(outUserCode);
        	String outUserName=(String) map.get("USER_NAME");
        	share.setOutUserName(outUserName);
        	
        	String shareType=(String) map.get("SHARE_TYPE") ;
        	share.setShareType(shareType);
        	String privateKey=(String) map.get("PRIVATE_KEY") ;
        	share.setPrivateKey(privateKey);
        	String shareUrl=(String) map.get("SHARE_URL") ;
        	share.setShareUrl(shareUrl);
        	
        	Date startTime = (Date) map.get("START_TIME");
        	share.setStartTime(DateUtil.format(startTime,"yy-MM-dd HH:mm:ss"));
            Date endTime = (Date) map.get("END_TIME");
            share.setEndTime(DateUtil.format(endTime,"yy-MM-dd HH:mm:ss"));
            BigDecimal status = (BigDecimal) map.get("STATUS");
            share.setStatus(status.intValue());
            
            share.setShareTitle((String)map.get("SHARE_TITLE"));
            share.setShareContent((String)map.get("SHARE_CONTENT"));
            share.setShareImgUrl((String)map.get("SHARE_IMG_URL"));
           return share;
		}
		return null;
	
	}
	/**
	 * 分享人奖励
	 * @param shareId
	 * @param baseType
	 * @return
	 */
	public List<ShareReward> shareRewardInfo(String shareId,Integer baseType){
		List<ShareReward> rewardList=new ArrayList<ShareReward>();
		StringBuffer str=new StringBuffer("select SHARE_REWARD_ID ,SHARE_ID,OUT_USER_CODE,BASE_TYPE,AWARD_TYPE,NUM,");
		str.append(" AWARD_MAX,LOTTERY_CODE,AMOUNT from SHARE_REWARD where SHARE_ID=? ");
		List<Object> values = new ArrayList<Object>();
		values.add(shareId);
		if(CommonUtil.isNotEmpty(baseType)){
         	str.append(" and BASE_TYPE=?");
         	values.add(baseType);
         	if(baseType==1){
         		str.append(" order by NUM ");
         	}
         }
		List<Map> list=shareDao.findBySql(str.toString(), values);
		if(CommonUtil.isNotEmpty(list)){
			for(Map map: list){
				ShareReward reward=new ShareReward();
				reward.setShareId(shareId);
				reward.setShareRewardId((String)map.get("SHARE_REWARD_ID"));
				
				String outUserCode=(String) map.get("OUT_USER_CODE");
				reward.setOutUserCode(outUserCode);
				BigDecimal basetype=(BigDecimal) map.get("BASE_TYPE");
				reward.setBaseType(basetype.intValue());
				BigDecimal awardType=(BigDecimal) map.get("AWARD_TYPE");
				reward.setAwardType(awardType.intValue());
				
				BigDecimal num=(BigDecimal) map.get("NUM");
				reward.setNum(num==null?null:num.intValue());
				BigDecimal awardMax=(BigDecimal) map.get("AWARD_MAX");
				reward.setAwardMax(awardMax==null?null:awardMax.intValue());
				
				BigDecimal amount=(BigDecimal) map.get("AMOUNT");
				reward.setAmount(amount==null?null:amount.doubleValue());
				
				reward.setLotteryCode((String)map.get("LOTTERY_CODE"));
				rewardList.add(reward);
			}
		}
		return rewardList;
	}
	
	/**
	 * 参与人奖励
	 * @param shareId
	 * @return
	 */
	public JoinReward joinRewardInfo(String shareId){
		StringBuffer str=new StringBuffer("select SHARE_ID,OUT_USER_CODE,AWARD_TYPE,");
		str.append(" LOTTERY_CODE,AMOUNT from SHARE_JOIN_REWARD where SHARE_ID=? ");
		List<Object> values = new ArrayList<Object>();
		values.add(shareId);
		List<Map> list=shareDao.findBySql(str.toString(), values);
		if(CommonUtil.isNotEmpty(list)){
        	Map map=list.get(0);
        	JoinReward reward=new JoinReward();
        	reward.setShareId(shareId);
			
			String outUserCode=(String) map.get("OUT_USER_CODE");
			reward.setOutUserCode(outUserCode);
			BigDecimal awardType=(BigDecimal) map.get("AWARD_TYPE");
			reward.setAwardType(awardType.intValue());
			
			BigDecimal amount=(BigDecimal) map.get("AMOUNT");
			reward.setAmount(amount==null?null:amount.doubleValue());
			return reward;
		}
		return null;
	}
	
	/**
	 * 更改分享活动状态
	 * @param shareId
	 * @param status
	 * @return
	 */
	public boolean updateActivityStatus(String shareId,Integer status){
		StringBuffer str = new StringBuffer("UPDATE SHARE_INFO set STATUS=?,UPDATE_TIME=sysdate where SHARE_ID=? ");
        List<Object> values = new ArrayList<Object>();
        values.add(status);
        values.add(shareId);
        int result = shareDao.executeSql(str.toString(), values);
        if (result > 0) {
            return true;
        }
        return false;
	}
	
	/**
	 * 分享模板列表
	 * @return
	 */
	public List<ShareModule> shareModuleList(){
		StringBuilder str=new StringBuilder("select SHARE_NAME,SHARE_TYPE,STATUS from SHARE_MODULE ");
		List<Map> list=shareDao.findBySql(str.toString());
		List<ShareModule> moduleList=new ArrayList<ShareModule>();
		if(CommonUtil.isNotEmpty(list)){
			for(Map map: list){
			ShareModule module=new ShareModule();
			String shareType=(String) map.get("SHARE_TYPE");
			module.setShareType(shareType);
			String shareName=(String) map.get("SHARE_NAME");
			module.setShareName(shareName);
			BigDecimal status=(BigDecimal) map.get("STATUS");
			module.setStatus(status.intValue());
			moduleList.add(module);
			}
		}
		return moduleList;
	}
	/**
	 * 分享模块分享设置
	 * @param shareType
	 * @param shareName
	 * @param status
	 * @return
	 */
	public boolean moduleShare(String shareType,String shareName,Integer status){
		StringBuffer str=new StringBuffer(" UPDATE SHARE_MODULE SET STATUS=? WHERE SHARE_NAME=? and SHARE_TYPE=? ");
		 List<Object> values = new ArrayList<Object>();
		 values.add(status);
	     values.add(shareName);
	     values.add(shareType);
	     int result = shareDao.executeSql(str.toString(), values);
	     if (result > 0) {
	        return true;
	    }
	    return false;
	 
	}
	/**
	 * 额外奖励删除
	 * @param shareRewardId
	 * @return
	 */
	public boolean deleteExtraReward(String shareRewardId){
		StringBuffer str = new StringBuffer("DELETE FROM SHARE_REWARD WHERE SHARE_REWARD_ID=? and BASE_TYPE=1 ");
        List<Object> values = new ArrayList<Object>();
        values.add(shareRewardId);
        int result = shareDao.executeSql(str.toString(), values);
        if (result > 0) {
            return true;
        }
        return false;
	}
	
	/**
	 * 分享活动删除
	 * @param shareId
	 * @return
	 */
	public boolean deleteShareActivity(String shareId){
		StringBuffer str = new StringBuffer("DELETE FROM SHARE_INFO WHERE SHARE_ID=?  ");
        List<Object> values = new ArrayList<Object>();
        values.add(shareId);
        int result = shareDao.executeSql(str.toString(), values);
        if (result > 0) {
        	str = new StringBuffer("DELETE FROM SHARE_REWARD WHERE SHARE_ID=?  ");
        	shareDao.executeSql(str.toString(), values);
        	str = new StringBuffer("DELETE FROM SHARE_JOIN_REWARD WHERE SHARE_ID=?  ");
        	shareDao.executeSql(str.toString(), values);
            return true;
        }
        return false;
	}
}
