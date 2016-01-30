package com.cm.manage.dao.share.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.share.IShareUserDao;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.share.JoinUserRecordVO;
import com.cm.manage.vo.share.ShareUserInfoVO;
import com.cm.manage.vo.share.ShareUserRecordBaseVO;
import com.cm.manage.vo.share.ShareUserRecordExtraVO;

@Repository
public class ShareUserDaoImpl implements IShareUserDao {

	@Autowired
	private IBaseDao<Object> shareUserDao;
	@Override
	public EasyuiDataGridJson shareUserList(EasyuiDataGrid dg,
			ShareUserInfoVO shareUser) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder str=new StringBuilder("select s.SHARE_ID,s.SHARE_NAME,s.SHARE_TYPE,s.SHARE_USER_ID,s.SHARE_URL,s.PRIVATE_KEY,s.USER_CODE,s.ACCOUNT_TYPE,");
		str.append(" s.AMOUNT,s.EXTRA_LOT,s.EXTRA_AMOUNT,s.EXTRA_SCORE,s.BONUS_TIME,s.CREATE_TIME,u.USER_NAME  from SHARE_USER_INFO s left join USER_MEMBER u on s.USER_CODE=u.USER_CODE ");
		str.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
        if (shareUser != null) {
            // 是否模糊查询
            boolean flag = shareUser.isFlag();
            // 流水编号
            if (CommonUtil.isNotEmpty(shareUser.getShareUserId())) {
                if (flag) {
                    str.append(" and s.SHARE_USER_ID like ? ");
                    values.add("%" + shareUser.getShareUserId().trim() + "%");
                } else {
                    str.append(" and s.SHARE_USER_ID = ? ");
                    values.add(shareUser.getShareUserId().trim());
                }
            }
            //分享人
            if (CommonUtil.isNotEmpty(shareUser.getUserName())) {
                if (flag) {
                    str.append(" and u.USER_NAME like ? ");
                    values.add("%" + shareUser.getUserName().trim() + "%");
                } else {
                    str.append(" and u.USER_NAME = ? ");
                    values.add(shareUser.getUserName().trim());
                }
            }
            // 活动编号
            if (CommonUtil.isNotEmpty(shareUser.getShareId())) {
                if (flag) {
                    str.append(" and s.SHARE_ID like ? ");
                    values.add("%" + shareUser.getShareId().trim() + "%");
                } else {
                    str.append(" and s.SHARE_ID = ? ");
                    values.add(shareUser.getShareId().trim());
                }
            }
            //关联ID
            if (CommonUtil.isNotEmpty(shareUser.getPrivateKey())) {
                if (flag) {
                    str.append(" and s.PRIVATE_KEY like ? ");
                    values.add("%" + shareUser.getPrivateKey().trim() + "%");
                } else {
                    str.append(" and s.PRIVATE_KEY = ? ");
                    values.add(shareUser.getPrivateKey().trim());
                }
            }
            //奖励类型
            if(CommonUtil.isNotEmpty(shareUser.getAccountType())){
            	str.append(" and s.ACCOUNT_TYPE=?");
            	values.add(shareUser.getAccountType());
            }
            //分享类型
            if(CommonUtil.isNotEmpty(shareUser.getShareType())){
            	str.append("and s.SHARE_TYPE=?");
            	values.add(shareUser.getShareType());
            }
           
           
            //开始时间start
            if (CommonUtil.isNotEmpty(shareUser.getCreateTimeStart())) {
                str.append(" and s.CREATE_TIME >= ? ");
                values.add(DateUtil.format(shareUser.getCreateTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //开始时间end
            if (CommonUtil.isNotEmpty(shareUser.getCreateTimeEnd())) {
                str.append(" and s.CREATE_TIME <= ? ");
                values.add(DateUtil.format(shareUser.getCreateTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }

            //结束时间start
            if (CommonUtil.isNotEmpty(shareUser.getBonusTimeStart())) {
                str.append(" and s.BONUS_TIME >= ? ");
                values.add(DateUtil.format(shareUser.getBonusTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //结束时间end
            if (CommonUtil.isNotEmpty(shareUser.getBonusTimeEnd())) {
                str.append(" and s.BONUS_TIME <= ? ");
                values.add(DateUtil.format(shareUser.getBonusTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }
        }
	    
	    String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(shareUserDao.countBySql(totalHql, values).longValue());// 设置总记录数
        
	    str.append(" order by s.CREATE_TIME desc");
	    List<Map> mapList = shareUserDao.findBySql(str.toString(),values,dg.getPage(), dg.getRows());
	    List<ShareUserInfoVO> shareList=new ArrayList<ShareUserInfoVO>();
	    if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
            	ShareUserInfoVO share=new ShareUserInfoVO();
            	String shareId=(String) map.get("SHARE_ID");
            	share.setShareId(shareId);
            	String shareName=(String) map.get("SHARE_NAME");
            	share.setShareName(shareName);
            	
            	String shareUserId=(String) map.get("SHARE_USER_ID");
            	share.setShareUserId(shareUserId);
            	
            	String userCode=(String)map.get("USER_CODE");
            	share.setUserCode(userCode);
            	
            	String userName=(String) map.get("USER_NAME");
            	share.setUserName(userName);
            	
            	BigDecimal accountType=(BigDecimal) map.get("ACCOUNT_TYPE") ;
            	share.setAccountType(accountType==null?null:accountType.intValue());
            	
            	String shareType=(String) map.get("SHARE_TYPE") ;
            	share.setShareType(shareType);
            	String privateKey=(String) map.get("PRIVATE_KEY") ;
            	share.setPrivateKey(privateKey);
            	String shareUrl=(String) map.get("SHARE_URL") ;
            	share.setShareUrl(shareUrl);
            	
            	Date createTime = (Date) map.get("CREATE_TIME");
            	share.setCreateTime(DateUtil.format(createTime,"yy-MM-dd HH:mm:ss"));
                Date bonusTime = (Date) map.get("BONUS_TIME");
                share.setBonusTime(DateUtil.format(bonusTime,"yy-MM-dd HH:mm:ss"));
                
                BigDecimal extraAmount=(BigDecimal) map.get("EXTRA_AMOUNT");
                share.setExtraAmount(extraAmount==null?null:extraAmount.doubleValue());
                
                BigDecimal extraLot=(BigDecimal)map.get("EXTRA_LOT");
                share.setExtraLot(extraLot==null?null:extraLot.intValue());
                
                BigDecimal extraScore=(BigDecimal)map.get("EXTRA_SCORE");
                share.setExtraScore(extraScore==null?null:extraScore.doubleValue());
                BigDecimal amount=(BigDecimal) map.get("AMOUNT");
                share.setAmount(amount==null?null:amount.doubleValue());
                shareList.add(share);
            }
            
	    }
	    j.setRows(shareList);
        return j;
	}
	/**
	 * 参与人奖励汇总
	 * @param shareUser
	 * @return
	 */
	public Map shareUserCount(ShareUserInfoVO shareUser){

		StringBuilder str=new StringBuilder("select sum(CASE WHEN s.ACCOUNT_TYPE = 0 THEN s.AMOUNT ELSE 0 END) as presentBase,");
		str.append(" sum(CASE WHEN s.ACCOUNT_TYPE = 1 THEN s.AMOUNT ELSE 0 END) as scoreBase,");
		str.append(" sum(CASE WHEN s.ACCOUNT_TYPE = 2 THEN s.AMOUNT ELSE 0 END) as lotBase,");
		str.append(" sum(s.EXTRA_AMOUNT ) as presentExtra,");
		str.append(" sum(s.EXTRA_SCORE ) as scoreExtra,");
		str.append(" sum(s.EXTRA_LOT ) as lotExtra ");
		str.append(" from SHARE_USER_INFO s left join USER_MEMBER u on s.USER_CODE=u.USER_CODE ");
		str.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
        if (shareUser != null) {
            // 是否模糊查询
            boolean flag = shareUser.isFlag();
            // 流水编号
            if (CommonUtil.isNotEmpty(shareUser.getShareUserId())) {
                if (flag) {
                    str.append(" and s.SHARE_USER_ID like ? ");
                    values.add("%" + shareUser.getShareUserId().trim() + "%");
                } else {
                    str.append(" and s.SHARE_USER_ID = ? ");
                    values.add(shareUser.getShareUserId().trim());
                }
            }
            //分享人
            if (CommonUtil.isNotEmpty(shareUser.getUserName())) {
                if (flag) {
                    str.append(" and u.USER_NAME like ? ");
                    values.add("%" + shareUser.getUserName().trim() + "%");
                } else {
                    str.append(" and u.USER_NAME = ? ");
                    values.add(shareUser.getUserName().trim());
                }
            }
            // 活动编号
            if (CommonUtil.isNotEmpty(shareUser.getShareId())) {
                if (flag) {
                    str.append(" and s.SHARE_ID like ? ");
                    values.add("%" + shareUser.getShareId().trim() + "%");
                } else {
                    str.append(" and s.SHARE_ID = ? ");
                    values.add(shareUser.getShareId().trim());
                }
            }
            //关联ID
            if (CommonUtil.isNotEmpty(shareUser.getPrivateKey())) {
                if (flag) {
                    str.append(" and s.PRIVATE_KEY like ? ");
                    values.add("%" + shareUser.getPrivateKey().trim() + "%");
                } else {
                    str.append(" and s.PRIVATE_KEY = ? ");
                    values.add(shareUser.getPrivateKey().trim());
                }
            }
            //奖励类型
            if(CommonUtil.isNotEmpty(shareUser.getAccountType())){
            	str.append(" and s.ACCOUNT_TYPE=?");
            	values.add(shareUser.getAccountType());
            }
            //分享类型
            if(CommonUtil.isNotEmpty(shareUser.getShareType())){
            	str.append("and s.SHARE_TYPE=?");
            	values.add(shareUser.getShareType());
            }
           
           
            //开始时间start
            if (CommonUtil.isNotEmpty(shareUser.getCreateTimeStart())) {
                str.append(" and s.CREATE_TIME >= ? ");
                values.add(DateUtil.format(shareUser.getCreateTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //开始时间end
            if (CommonUtil.isNotEmpty(shareUser.getCreateTimeEnd())) {
                str.append(" and s.CREATE_TIME <= ? ");
                values.add(DateUtil.format(shareUser.getCreateTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }

            //结束时间start
            if (CommonUtil.isNotEmpty(shareUser.getBonusTimeStart())) {
                str.append(" and s.BONUS_TIME >= ? ");
                values.add(DateUtil.format(shareUser.getBonusTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //结束时间end
            if (CommonUtil.isNotEmpty(shareUser.getBonusTimeEnd())) {
                str.append(" and s.BONUS_TIME <= ? ");
                values.add(DateUtil.format(shareUser.getBonusTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }
        }
        List<Map> mapList = shareUserDao.findBySql(str.toString(), values);
        if (CommonUtil.isNotEmpty(mapList)) {
            return mapList.get(0);
        }
        return null;
	    
	}
	/**
	 * 分享用户基本奖励
	 * @param dg
	 * @param shareUserId
	 * @return
	 */
	public EasyuiDataGridJson shareUserRecordBase(EasyuiDataGrid dg,String shareUserId) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder str=new StringBuilder("select JOIN_USER_CODE,JOIN_USER_NAME,JOIN_MOBLIE,ACCOUNT_TYPE,");
		str.append(" AMOUNT,BONUS_TIME,JOIN_TIME,STATUS from SHARE_USER_RECORD_BASE WHERE SHARE_USER_ID=?");
		List<Object> values = new ArrayList<Object>();
		values.add(shareUserId);
		String totalHql = " select count(*)  from (" + str.toString() + ")";
	    j.setTotal(shareUserDao.countBySql(totalHql, values).longValue());// 设置总记录数
	        
		str.append(" order by JOIN_TIME desc");
		List<Map> mapList = shareUserDao.findBySql(str.toString(),values,dg.getPage(), dg.getRows());
		List<ShareUserRecordBaseVO> baseList=new ArrayList<ShareUserRecordBaseVO>();
	    if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
            	ShareUserRecordBaseVO base=new ShareUserRecordBaseVO();
            	String joinUserCode=(String) map.get("JOIN_USER_CODE");
            	base.setJoinUserCode(joinUserCode);
            	String joinUserName=(String) map.get("JOIN_USER_NAME");
            	base.setJoinUserName(joinUserName);
            	String joinMoblie=(String) map.get("JOIN_MOBLIE");
            	base.setJoinMoblie(joinMoblie);
            	BigDecimal accountType=(BigDecimal) map.get("ACCOUNT_TYPE");
            	base.setAccountType(accountType==null?null:accountType.intValue());
            	BigDecimal amount=(BigDecimal) map.get("AMOUNT");
            	base.setAmount(amount==null?null:amount.doubleValue());
            	Date joinTime = (Date) map.get("JOIN_TIME");
            	base.setJoinTime(DateUtil.format(joinTime,"yy-MM-dd HH:mm:ss"));
                Date bonusTime = (Date) map.get("BONUS_TIME");
                base.setBonusTime(DateUtil.format(bonusTime,"yy-MM-dd HH:mm:ss"));
                BigDecimal status=(BigDecimal) map.get("STATUS");
                base.setStatus(status.intValue());
                baseList.add(base);
            }
	    }
	    j.setRows(baseList);
		return j;
	}
	/**
	 * 分享用户额外奖励
	 * @param dg
	 * @param shareUserId
	 * @return
	 */
	public List<ShareUserRecordExtraVO> shareUserRecordExtra(String shareUserId) {
		StringBuilder str=new StringBuilder("select JOIN_NUM,ACCOUNT_TYPE,AMOUNT,BONUS_TIME,STATUS from SHARE_USER_RECORD_EXTRA where SHARE_USER_ID=? ");
		List<Object> values = new ArrayList<Object>();
		values.add(shareUserId);
	        
		str.append(" order by BONUS_TIME desc");
		List<Map> mapList = shareUserDao.findBySql(str.toString(),values);
		List<ShareUserRecordExtraVO> extraList=new ArrayList<ShareUserRecordExtraVO>();
	    if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
            	ShareUserRecordExtraVO extra=new ShareUserRecordExtraVO();
            	BigDecimal joinNum=(BigDecimal) map.get("JOIN_NUM");
            	extra.setJoinNum(joinNum==null?null:joinNum.intValue());
            	
            	BigDecimal accountType=(BigDecimal) map.get("ACCOUNT_TYPE");
            	extra.setAccountType(accountType==null?null:accountType.intValue());
            	BigDecimal amount=(BigDecimal) map.get("AMOUNT");
            	extra.setAmount(amount==null?null:amount.doubleValue());
                Date bonusTime = (Date) map.get("BONUS_TIME");
                extra.setBonusTime(DateUtil.format(bonusTime,"yy-MM-dd HH:mm:ss"));
                BigDecimal status=(BigDecimal) map.get("STATUS");
                extra.setStatus(status.intValue());
                extraList.add(extra);
            }
	    }
	    return extraList;
	}
	
	/**
	 * 参与人奖励
	 * @param dg
	 * @param joinUser
	 * @return
	 */
	public EasyuiDataGridJson joinUserList(EasyuiDataGrid dg,JoinUserRecordVO joinUser) {

		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder str=new StringBuilder("select j.SHARE_ID,s.SHARE_NAME,j.JOIN_USER_ID,j.USER_CODE,j.USER_NAME,j.MOBILE,j.SHARE_USER_ID,j.ACCOUNT_TYPE,");
		str.append(" j.AMOUNT,j.BONUS_TIME,j.CREATE_TIME,u.USER_NAME SHARE_USER_NAME,s.USER_CODE SHARE_USER_CODE,s.SHARE_TYPE from SHARE_JOIN_USER_RECORD j left join SHARE_USER_INFO s on j.SHARE_USER_ID=s.SHARE_USER_ID left join USER_MEMBER u on s.USER_CODE=u.USER_CODE ");
		str.append(" where 1=1 ");
		List<Object> values = new ArrayList<Object>();
        if (joinUser != null) {
            // 是否模糊查询
            boolean flag = joinUser.isFlag();
            // 流水编号
            if (CommonUtil.isNotEmpty(joinUser.getJoinUserId())) {
                if (flag) {
                    str.append(" and j.JOIN_USER_ID like ? ");
                    values.add("%" + joinUser.getJoinUserId().trim() + "%");
                } else {
                    str.append(" and j.JOIN_USER_ID = ? ");
                    values.add(joinUser.getJoinUserId().trim());
                }
            }
            //参与人
            if (CommonUtil.isNotEmpty(joinUser.getUserName())) {
                if (flag) {
                    str.append(" and j.USER_NAME like ? ");
                    values.add("%" + joinUser.getUserName().trim() + "%");
                } else {
                    str.append(" and j.USER_NAME = ? ");
                    values.add(joinUser.getUserName().trim());
                }
            }
            //分享人
            if (CommonUtil.isNotEmpty(joinUser.getShareUserName())) {
                if (flag) {
                    str.append(" and u.USER_NAME like ? ");
                    values.add("%" + joinUser.getShareUserName().trim() + "%");
                } else {
                    str.append(" and u.USER_NAME = ? ");
                    values.add(joinUser.getShareUserName().trim());
                }
            }
            // 活动编号
            if (CommonUtil.isNotEmpty(joinUser.getShareId())) {
                if (flag) {
                    str.append(" and j.SHARE_ID like ? ");
                    values.add("%" + joinUser.getShareId().trim() + "%");
                } else {
                    str.append(" and j.SHARE_ID = ? ");
                    values.add(joinUser.getShareId().trim());
                }
            }
            
            //奖励类型
            if(CommonUtil.isNotEmpty(joinUser.getAccountType())){
            	str.append(" and j.ACCOUNT_TYPE=?");
            	values.add(joinUser.getAccountType());
            }
            //分享类型
            if(CommonUtil.isNotEmpty(joinUser.getShareType())){
            	str.append(" and s.SHARE_TYPE=?");
            	values.add(joinUser.getShareType());
            }
           
           
            //参与开始时间start
            if (CommonUtil.isNotEmpty(joinUser.getCreateTimeStart())) {
                str.append(" and j.CREATE_TIME >= ? ");
                values.add(DateUtil.format(joinUser.getCreateTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //参与开始时间end
            if (CommonUtil.isNotEmpty(joinUser.getCreateTimeEnd())) {
                str.append(" and j.CREATE_TIME <= ? ");
                values.add(DateUtil.format(joinUser.getCreateTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }

            //返奖结束时间start
            if (CommonUtil.isNotEmpty(joinUser.getBonusTimeStart())) {
                str.append(" and j.BONUS_TIME >= ? ");
                values.add(DateUtil.format(joinUser.getBonusTimeStart(), "yy-MM-dd HH:mm:ss"));
            }
            //返奖结束时间end
            if (CommonUtil.isNotEmpty(joinUser.getBonusTimeEnd())) {
                str.append(" and j.BONUS_TIME <= ? ");
                values.add(DateUtil.format(joinUser.getBonusTimeEnd(), "yy-MM-dd HH:mm:ss"));
            }
        }
	    
	    String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(shareUserDao.countBySql(totalHql, values).longValue());// 设置总记录数
        
	    str.append(" order by j.CREATE_TIME desc");
	    List<Map> mapList = shareUserDao.findBySql(str.toString(),values,dg.getPage(), dg.getRows());
	    List<JoinUserRecordVO> joinList=new ArrayList<JoinUserRecordVO>();
	    if (mapList != null && mapList.size() > 0) {// 转换模型
            for (Map map : mapList) {
            	JoinUserRecordVO join=new JoinUserRecordVO();
            	String shareId=(String) map.get("SHARE_ID");
            	join.setShareId(shareId);
            	String shareName=(String) map.get("SHARE_NAME");
            	join.setShareName(shareName);
            	
            	String joinUserId=(String) map.get("JOIN_USER_ID");
            	join.setJoinUserId(joinUserId);
            	
            	String userCode=(String)map.get("USER_CODE");
            	join.setUserCode(userCode);
            	
            	String userName=(String) map.get("USER_NAME");
            	join.setUserName(userName);
            	String mobile=(String) map.get("MOBILE");
            	join.setMobile(mobile);
            	
            	String shareUserName=(String) map.get("SHARE_USER_NAME");
            	join.setShareUserName(shareUserName);
            	
            	String shareUserCode=(String) map.get("SHARE_USER_CODE");
            	join.setShareUserCode(shareUserCode);
            	BigDecimal accountType=(BigDecimal) map.get("ACCOUNT_TYPE") ;
            	join.setAccountType(accountType==null?null:accountType.intValue());
            	
            	String shareType=(String) map.get("SHARE_TYPE") ;
            	join.setShareType(shareType);
            	
            	
            	Date createTime = (Date) map.get("CREATE_TIME");
            	join.setCreateTime(DateUtil.format(createTime,"yy-MM-dd HH:mm:ss"));
                Date bonusTime = (Date) map.get("BONUS_TIME");
                join.setBonusTime(DateUtil.format(bonusTime,"yy-MM-dd HH:mm:ss"));
                
                BigDecimal amount=(BigDecimal) map.get("AMOUNT");
                join.setAmount(amount==null?null:amount.doubleValue());
                joinList.add(join);
            }
            
	    }
	    j.setRows(joinList);
        return j;
	
	}

}
