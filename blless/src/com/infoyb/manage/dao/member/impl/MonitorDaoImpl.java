package com.cm.manage.dao.member.impl;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.member.IMonitorDao;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.util.base.Pager;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.MonitorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
@Repository
public class MonitorDaoImpl implements IMonitorDao {

	@Autowired
	private IBaseDao<Object> monitorDao;
	@Override
	public EasyuiDataGridJson monitorList(EasyuiDataGrid dg, MonitorVO monitor) {

        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuilder str1=new StringBuilder(" select u.MACH_ID machId,count(distinct u.USER_CODE) regCount,0 loginCount from USER_MEMBER u ");
        str1.append(" left join  USER_MEMBER_LOGIN l on  u.USER_CODE=l.USER_CODE  where 1=1 ");
        StringBuffer str2=new StringBuffer(" select l.MACH_ID machId ,0 regCount,count(distinct l.USER_CODE) loginCount from USER_MEMBER_LOGIN_HISTORY l ");
        str2.append(" left join  USER_MEMBER u on  u.USER_CODE=l.USER_CODE  where 1=1 ");
        StringBuffer str=new StringBuffer();
        List<Object> values = new ArrayList<Object>();
        List<Object> values1 = new ArrayList<Object>();
        List<Object> values2 = new ArrayList<Object>();
        if (monitor != null) {// 添加查询条件
        	boolean flag=monitor.isFlag();
        	// 注册开始时间
 			if (CommonUtil.isNotEmpty(monitor.getRegstart())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getRegstart(), "yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 0);
 				cal.set(Calendar.MINUTE, 0);
 				cal.set(Calendar.SECOND, 0); 
 				str1.append(" and  u.CREATE_TIME >= ?");
                str2.append(" and  u.CREATE_TIME >= ?");
                values1.add(cal.getTime());
                values2.add(cal.getTime());
 			}
 			if (CommonUtil.isNotEmpty(monitor.getRegend())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getRegend(),"yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				str1.append( " and u.CREATE_TIME <= ? ");
 				str2.append( " and u.CREATE_TIME <= ? ");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
        
			//登录时间
            if (CommonUtil.isNotEmpty(monitor.getLoginstart())) {
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(DateUtil.format(monitor.getLoginstart(), "yy-MM-dd"));
            	cal.set(Calendar.HOUR_OF_DAY, 0);
            	cal.set(Calendar.MINUTE, 0);
            	cal.set(Calendar.SECOND, 0); 
            	str1.append(" and  l.CREATE_TIME >= ?");
 				str2.append(" and  l.CREATE_TIME >= ?");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
 			if (CommonUtil.isNotEmpty(monitor.getLoginend())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getLoginend(),"yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				str1.append( " and l.CREATE_TIME <= ? ");
 				str2.append( " and l.CREATE_TIME <= ? ");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
            //姓名
 			if (CommonUtil.isNotEmpty(monitor.getRealName())) {
				if (flag) {
					str1.append(" and u.REAL_NAME like ? ");
					str2.append(" and u.REAL_NAME like ? ");
					values1.add("%" + monitor.getRealName().trim() + "%");
					values2.add("%" + monitor.getRealName().trim() + "%");
				} else {
					str1.append(" and u.REAL_NAME = ? ");
					str2.append(" and u.REAL_NAME = ? ");
					values1.add(monitor.getRealName().trim());
					values2.add(monitor.getRealName().trim());
				}
			}
           
            //身份证
 			
 			if (CommonUtil.isNotEmpty(monitor.getCardCode())) {
				if (flag) {
					str1.append(" and u.CARD_CODE like ? ");
					str2.append(" and u.CARD_CODE like ? ");
					values1.add("%" + monitor.getCardCode().trim() + "%");
					values2.add("%" + monitor.getCardCode().trim() + "%");
				} else {
					str1.append(" and u.CARD_CODE = ? ");
					str2.append(" and u.CARD_CODE = ? ");
					values1.add(monitor.getCardCode().trim());
					values2.add(monitor.getCardCode().trim());
				}
			}
 			
 			str1.append(" group by u.MACH_ID ");
 			
 			str2.append(" group by l.MACH_ID ");
 			
 			//监控类别 
 			if (CommonUtil.isNotEmpty(monitor.getMonitorType())) {
				if(monitor.getMonitorType()==0){
					str.append(str1);
					values.addAll(values1);
				}
				if(monitor.getMonitorType()==1){
					str.append(str2);
					values.addAll(values2);
				}
			}
 			else{
 				str.append(str1);
 				str.append(" union ");
 				str.append(str2);
 				values.addAll(values1);
 				values.addAll(values2);
 			}

        }
        List<Map> monitorMapList = monitorDao.findBySql(str.toString(), values);// 查询分页
        List<MonitorVO> monitorList = new ArrayList<MonitorVO>();
        Map<String,MonitorVO> monitorMap=new HashMap<String, MonitorVO>();
        if (monitorMapList != null && monitorMapList.size() > 0) {// 转换模型
            for (Map map : monitorMapList) {
            	String machId=(String)map.get("MACHID");
            	MonitorVO vo=(MonitorVO)monitorMap.get(machId);
            	if(vo==null){
            		vo= new MonitorVO();
            		
            		vo.setMachId(machId);
            		
            		BigDecimal regCount=(BigDecimal) map.get("REGCOUNT");
            		vo.setRegCount(regCount.intValue());
            		BigDecimal loginCount=(BigDecimal) map.get("LOGINCOUNT");
            		vo.setLoginCount(loginCount.intValue());
                    monitorMap.put(machId, vo);
            	}else{
            		
            		BigDecimal regCount=(BigDecimal) map.get("REGCOUNT");
            		vo.setRegCount(vo.getRegCount()+regCount.intValue());
            		BigDecimal loginCount=(BigDecimal) map.get("LOGINCOUNT");
            		vo.setLoginCount(vo.getLoginCount()+loginCount.intValue());
                    monitorMap.put(machId, vo);
            	}
            }
            
            
        }
        if(monitorMap!=null&&monitorMap.size()>0){
           Set set =monitorMap.keySet();
           Iterator it=set.iterator();
           while(it.hasNext()){
               String key= (String) it.next();
               monitorList.add(monitorMap.get(key));
           }
        }
        j.setTotal((long)monitorList.size());
        Pager p=new Pager(dg.getPage(), dg.getRows(), monitorList);
        j.setRows(p.getPagerList());// 设置返回的行
        return j;
    }
	
	/**
	 * ip监控列表
	 * @param dg
	 * @param monitor
	 * @return
	 */
	public EasyuiDataGridJson ipMonitorList(EasyuiDataGrid dg, MonitorVO monitor){

        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuilder str1=new StringBuilder(" select u.IP ip,count(distinct u.USER_CODE) regCount,0 loginCount from USER_MEMBER u ");
        str1.append(" left join  USER_MEMBER_LOGIN l on  u.USER_CODE=l.USER_CODE  where 1=1 ");
        StringBuffer str2=new StringBuffer(" select l.IP ip ,0 regCount,count(distinct l.USER_CODE) loginCount from USER_MEMBER_LOGIN_HISTORY l ");
        str2.append(" left join  USER_MEMBER u on  u.USER_CODE=l.USER_CODE  where 1=1 ");
        StringBuffer str=new StringBuffer();
        List<Object> values = new ArrayList<Object>();
        List<Object> values1 = new ArrayList<Object>();
        List<Object> values2 = new ArrayList<Object>();
        if (monitor != null) {// 添加查询条件
        	boolean flag=monitor.isFlag();
        	// 注册开始时间
 			if (CommonUtil.isNotEmpty(monitor.getRegstart())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getRegstart(), "yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 0);
 				cal.set(Calendar.MINUTE, 0);
 				cal.set(Calendar.SECOND, 0); 
 				str1.append(" and  u.CREATE_TIME >= ?");
                str2.append(" and  u.CREATE_TIME >= ?");
                values1.add(cal.getTime());
                values2.add(cal.getTime());
 			}
 			if (CommonUtil.isNotEmpty(monitor.getRegend())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getRegend(),"yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				str1.append( " and u.CREATE_TIME <= ? ");
 				str2.append( " and u.CREATE_TIME <= ? ");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
        
			//登录时间
            if (CommonUtil.isNotEmpty(monitor.getLoginstart())) {
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(DateUtil.format(monitor.getLoginstart(), "yy-MM-dd"));
            	cal.set(Calendar.HOUR_OF_DAY, 0);
            	cal.set(Calendar.MINUTE, 0);
            	cal.set(Calendar.SECOND, 0); 
            	str1.append(" and  l.CREATE_TIME >= ?");
 				str2.append(" and  l.CREATE_TIME >= ?");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
 			if (CommonUtil.isNotEmpty(monitor.getLoginend())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getLoginend(),"yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				str1.append( " and l.CREATE_TIME <= ? ");
 				str2.append( " and l.CREATE_TIME <= ? ");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
            //姓名
 			if (CommonUtil.isNotEmpty(monitor.getRealName())) {
				if (flag) {
					str1.append(" and u.REAL_NAME like ? ");
					str2.append(" and u.REAL_NAME like ? ");
					values1.add("%" + monitor.getRealName().trim() + "%");
					values2.add("%" + monitor.getRealName().trim() + "%");
				} else {
					str1.append(" and u.REAL_NAME = ? ");
					str2.append(" and u.REAL_NAME = ? ");
					values1.add(monitor.getRealName().trim());
					values2.add(monitor.getRealName().trim());
				}
			}
           
            //身份证
 			
 			if (CommonUtil.isNotEmpty(monitor.getCardCode())) {
				if (flag) {
					str1.append(" and u.CARD_CODE like ? ");
					str2.append(" and u.CARD_CODE like ? ");
					values1.add("%" + monitor.getCardCode().trim() + "%");
					values2.add("%" + monitor.getCardCode().trim() + "%");
				} else {
					str1.append(" and u.CARD_CODE = ? ");
					str2.append(" and u.CARD_CODE = ? ");
					values1.add(monitor.getCardCode().trim());
					values2.add(monitor.getCardCode().trim());
				}
			}
 			if (CommonUtil.isNotEmpty(monitor.getIp())) {
				if (flag) {
					str1.append(" and u.IP like ? ");
					str2.append(" and l.IP like ? ");
					values1.add("%" + monitor.getIp().trim() + "%");
					values2.add("%" + monitor.getIp().trim() + "%");
				} else {
					str1.append(" and u.IP = ? ");
					str2.append(" and l.IP = ? ");
					values1.add(monitor.getIp().trim());
					values2.add(monitor.getIp().trim());
				}
			}
 			str1.append(" group by u.IP ");
 			
 			str2.append(" group by l.IP ");
 			str.append(str1);
 			str.append(" union ");
 			str.append(str2);
 			values.addAll(values1);
 			values.addAll(values2);

        }
        List<Map> monitorMapList = monitorDao.findBySql(str.toString(), values);// 查询分页
        List<MonitorVO> monitorList = new ArrayList<MonitorVO>();
        Map<String,MonitorVO> monitorMap=new HashMap<String, MonitorVO>();
        if (monitorMapList != null && monitorMapList.size() > 0) {// 转换模型
            for (Map map : monitorMapList) {
            	String ip=(String)map.get("IP");
            	MonitorVO vo=(MonitorVO)monitorMap.get(ip);
            	if(vo==null){
            		vo= new MonitorVO();
            		
            		vo.setIp(ip);
            		
            		BigDecimal regCount=(BigDecimal) map.get("REGCOUNT");
            		vo.setRegCount(regCount.intValue());
            		BigDecimal loginCount=(BigDecimal) map.get("LOGINCOUNT");
            		vo.setLoginCount(loginCount.intValue());
                    monitorMap.put(ip, vo);
            	}else{
            		
            		BigDecimal regCount=(BigDecimal) map.get("REGCOUNT");
            		vo.setRegCount(vo.getRegCount()+regCount.intValue());
            		BigDecimal loginCount=(BigDecimal) map.get("LOGINCOUNT");
            		vo.setLoginCount(vo.getLoginCount()+loginCount.intValue());
                    monitorMap.put(ip, vo);
            	}
            }
            
            
        }
        if(monitorMap!=null&&monitorMap.size()>0){
           Set set =monitorMap.keySet();
           Iterator it=set.iterator();
           while(it.hasNext()){
               String key= (String) it.next();
               monitorList.add(monitorMap.get(key));
           }
        }
        j.setTotal((long)monitorList.size());
        Pager p=new Pager(dg.getPage(), dg.getRows(), monitorList);
        j.setRows(p.getPagerList());// 设置返回的行
        return j;
    }
	
	/**
	 * 身份监控
	 * @param dg
	 * @param monitor
	 * @return
	 */
	public EasyuiDataGridJson cardCodeMonitorList(EasyuiDataGrid dg, MonitorVO monitor){


        EasyuiDataGridJson j = new EasyuiDataGridJson();
        StringBuilder str1=new StringBuilder(" select u.CARD_CODE cardCode,u.REAL_NAME realName ,count(distinct u.USER_CODE) regCount,0 loginCount from USER_MEMBER u ");
        str1.append(" left join  USER_MEMBER_LOGIN l on  u.USER_CODE=l.USER_CODE  where u.CARD_CODE is not null ");
        StringBuffer str2=new StringBuffer(" select u.CARD_CODE cardCode,u.REAL_NAME realName ,0 regCount,count(distinct l.USER_CODE) loginCount from USER_MEMBER_LOGIN_HISTORY l ");
        str2.append(" left join  USER_MEMBER u on  u.USER_CODE=l.USER_CODE  where u.CARD_CODE is not null ");
        StringBuffer str=new StringBuffer();
        List<Object> values = new ArrayList<Object>();
        List<Object> values1 = new ArrayList<Object>();
        List<Object> values2 = new ArrayList<Object>();
        if (monitor != null) {// 添加查询条件
        	boolean flag=monitor.isFlag();
        	// 注册开始时间
 			if (CommonUtil.isNotEmpty(monitor.getRegstart())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getRegstart(), "yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 0);
 				cal.set(Calendar.MINUTE, 0);
 				cal.set(Calendar.SECOND, 0); 
 				str1.append(" and  u.CREATE_TIME >= ?");
                str2.append(" and  u.CREATE_TIME >= ?");
                values1.add(cal.getTime());
                values2.add(cal.getTime());
 			}
 			if (CommonUtil.isNotEmpty(monitor.getRegend())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getRegend(),"yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				str1.append( " and u.CREATE_TIME <= ? ");
 				str2.append( " and u.CREATE_TIME <= ? ");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
        
			//登录时间
            if (CommonUtil.isNotEmpty(monitor.getLoginstart())) {
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(DateUtil.format(monitor.getLoginstart(), "yy-MM-dd"));
            	cal.set(Calendar.HOUR_OF_DAY, 0);
            	cal.set(Calendar.MINUTE, 0);
            	cal.set(Calendar.SECOND, 0); 
            	str1.append(" and  l.CREATE_TIME >= ?");
 				str2.append(" and  l.CREATE_TIME >= ?");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
 			if (CommonUtil.isNotEmpty(monitor.getLoginend())) {
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(monitor.getLoginend(),"yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				str1.append( " and l.CREATE_TIME <= ? ");
 				str2.append( " and l.CREATE_TIME <= ? ");
 				values1.add(cal.getTime());
 				values2.add(cal.getTime());
 			}
            //姓名
 			if (CommonUtil.isNotEmpty(monitor.getRealName())) {
				if (flag) {
					str1.append(" and u.REAL_NAME like ? ");
					str2.append(" and u.REAL_NAME like ? ");
					values1.add("%" + monitor.getRealName().trim() + "%");
					values2.add("%" + monitor.getRealName().trim() + "%");
				} else {
					str1.append(" and u.REAL_NAME = ? ");
					str2.append(" and u.REAL_NAME = ? ");
					values1.add(monitor.getRealName().trim());
					values2.add(monitor.getRealName().trim());
				}
			}
           
            //身份证
 			
 			if (CommonUtil.isNotEmpty(monitor.getCardCode())) {
				if (flag) {
					str1.append(" and u.CARD_CODE like ? ");
					str2.append(" and u.CARD_CODE like ? ");
					values1.add("%" + monitor.getCardCode().trim() + "%");
					values2.add("%" + monitor.getCardCode().trim() + "%");
				} else {
					str1.append(" and u.CARD_CODE = ? ");
					str2.append(" and u.CARD_CODE = ? ");
					values1.add(monitor.getCardCode().trim());
					values2.add(monitor.getCardCode().trim());
				}
			}
 			
 			str1.append(" group by u.CARD_CODE ,u.REAL_NAME ");
 			
 			str2.append(" group by u.CARD_CODE ,u.REAL_NAME ");
 			str.append(str1);
 			str.append(" union ");
 			str.append(str2);
 			values.addAll(values1);
 			values.addAll(values2);

        }
        List<Map> monitorMapList = monitorDao.findBySql(str.toString(), values);// 查询分页
        List<MonitorVO> monitorList = new ArrayList<MonitorVO>();
        Map<String,MonitorVO> monitorMap=new HashMap<String, MonitorVO>();
        if (monitorMapList != null && monitorMapList.size() > 0) {// 转换模型
            for (Map map : monitorMapList) {
            	String cardCode=(String)map.get("CARDCODE");
            	String realName=(String) map.get("REALNAME");
            	MonitorVO vo=(MonitorVO)monitorMap.get(cardCode);
            	if(vo==null){
            		vo= new MonitorVO();
            		
            		vo.setCardCode(cardCode);
            		vo.setRealName(realName);
            		BigDecimal regCount=(BigDecimal) map.get("REGCOUNT");
            		vo.setRegCount(regCount.intValue());
            		BigDecimal loginCount=(BigDecimal) map.get("LOGINCOUNT");
            		vo.setLoginCount(loginCount.intValue());
                    monitorMap.put(cardCode, vo);
            	}else{
            		
            		BigDecimal regCount=(BigDecimal) map.get("REGCOUNT");
            		vo.setRegCount(regCount.intValue());
            		BigDecimal loginCount=(BigDecimal) map.get("LOGINCOUNT");
            		vo.setLoginCount(loginCount.intValue());
                    monitorMap.put(cardCode, vo);
            	}
            }
            
            
        }
        if(monitorMap!=null&&monitorMap.size()>0){
           Set set =monitorMap.keySet();
           Iterator it=set.iterator();
           while(it.hasNext()){
               String key= (String) it.next();
               monitorList.add(monitorMap.get(key));
           }
        }
        j.setTotal((long)monitorList.size());
        Pager p=new Pager(dg.getPage(), dg.getRows(), monitorList);
        j.setRows(p.getPagerList());// 设置返回的行
        return j;
    
	}

}
