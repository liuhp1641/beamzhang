package com.cm.manage.dao.member.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.member.IDeviceDao;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.Customer;
@Repository
public class DeviceDaoImpl implements IDeviceDao {
	
	@Autowired
	private IBaseDao<Object> deviceDao;

	@Override
	public EasyuiDataGridJson deviceList(EasyuiDataGrid dg, Customer customer,boolean flag) {

        EasyuiDataGridJson j = new EasyuiDataGridJson();

        StringBuilder str=new StringBuilder("select u.USER_NAME,u.USER_CODE,u.REAL_NAME, u.MOBILE,u.MACH_NAME regMach,u.MACH_ID regMachId,");
        str.append(" l.MACH_NAME logMach,l.MACH_ID logMachId ,u.SOFT_VER ");
        str.append(" from USER_MEMBER u left join  USER_MEMBER_LOGIN l on  u.USER_CODE=l.USER_CODE where 1=1");
        List<Object> values = new ArrayList<Object>();
        if (customer != null) {// 添加查询条件
        	//用户类型
        	if(CommonUtil.isNotEmpty(customer.getMemberType())){
        		str.append(" and u.MEMBER_TYPE = ? ");
                values.add(customer.getMemberType());
        	}
        	// 用户编号
			if (CommonUtil.isNotEmpty(customer.getUserCode())) {
				if (flag) {
					str.append(" and u.user_code like ? ");
					values.add("%" + customer.getUserCode().trim() + "%");
				} else {
					str.append(" and u.user_code = ? ");
					values.add(customer.getUserCode().trim());
				}
			}

			// 用户名称
			if (CommonUtil.isNotEmpty(customer.getUserName())) {
				if (flag) {
					str.append(" and u.user_name like ? ");
					values.add("%" + customer.getUserName().trim() + "%");
				} else {
					str.append(" and u.user_name = ? ");
					values.add(customer.getUserName().trim());
				}
			}
            //手机号
			if (CommonUtil.isNotEmpty(customer.getMobile())) {
				if (flag) {
					str.append(" and u.MOBILE like ? ");
					values.add("%" + customer.getMobile().trim() + "%");
				} else {
					str.append(" and u.MOBILE = ? ");
					values.add(customer.getMobile().trim());
				}
			}
			//登录时间
            if (CommonUtil.isNotEmpty(customer.getLoginstart())) {
 				str.append(" and  l.CREATE_TIME >= ?");
 				Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtil.format(customer.getLoginstart(), "yy-MM-dd"));
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0); 
                values.add(cal.getTime());
 			}
 			if (CommonUtil.isNotEmpty(customer.getLoginend())) {
 				str.append( " and l.CREATE_TIME <= ? ");
 				Calendar cal = Calendar.getInstance();
 				cal.setTime(DateUtil.format(customer.getLoginend(),"yy-MM-dd"));
 				cal.set(Calendar.HOUR_OF_DAY, 23);
 				cal.set(Calendar.MINUTE, 59);
 				cal.set(Calendar.SECOND, 59); // 设置时分秒都为0
 				values.add(cal.getTime());
 			}
            //姓名
 			if (CommonUtil.isNotEmpty(customer.getRealName())) {
				if (flag) {
					str.append(" and u.REAL_NAME like ? ");
					values.add("%" + customer.getRealName().trim() + "%");
				} else {
					str.append(" and u.REAL_NAME = ? ");
					values.add(customer.getRealName().trim());
				}
			}
           
            //身份证
 			
 			if (CommonUtil.isNotEmpty(customer.getCardCode())) {
				if (flag) {
					str.append(" and u.CARD_CODE like ? ");
					values.add("%" + customer.getCardCode().trim() + "%");
				} else {
					str.append(" and u.CARD_CODE = ? ");
					values.add(customer.getCardCode().trim());
				}
			}
           

            //渠道
            if (customer.getSid() != null && !customer.getSid().trim().equals("")) {
                str.append(" and u.SID = ?");
                values.add(customer.getSid().trim());
            }
          
            //登录串号
            if (CommonUtil.isNotEmpty(customer.getLoginMachId())) {
				if (flag) {
					str.append(" and l.MACH_ID like ? ");
					values.add("%" + customer.getLoginMachId().trim() + "%");
				} else {
					str.append(" and l.MACH_ID = ? ");
					values.add(customer.getLoginMachId().trim());
				}
			}
           //登录设备
            if (CommonUtil.isNotEmpty(customer.getMachName())) {
				if (flag) {
					str.append(" and l.MACH_NAME like ? ");
					values.add("%" + customer.getMachName().trim() + "%");
				} else {
					str.append(" and l.MACH_NAME = ? ");
					values.add(customer.getMachName().trim());
				}
			}
        }
        String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(deviceDao.countBySql(totalHql, values).longValue());// 设置总记录数
        List<Map> deviceMapList = deviceDao.findBySql(str.toString(), values, dg.getPage(), dg.getRows());// 查询分页
        List<Customer> deviceList = new ArrayList<Customer>();
        if (deviceMapList != null && deviceMapList.size() > 0) {// 转换模型
            for (Map map : deviceMapList) {
            	
                Customer u = new Customer();
                
                u.setUserCode((String) map.get("USER_CODE"));
                
                u.setUserName((String) map.get("USER_NAME"));
                
                u.setRealName((String) map.get("REAL_NAME"));
                
                u.setMobile((String) map.get("MOBILE"));
               
                u.setSoftVer((String) map.get("SOFT_VER"));
                
                String regMach=(String)map.get("REGMACH");
                u.setMachName(regMach);
                
                String logMach=(String) map.get("LOGMACH");
                u.setLoginMach(logMach);
                
                String regMachId=(String) map.get("REGMACHID");
                u.setMachId(regMachId);
                
                String logMachId=(String) map.get("LOGMACHID");
                u.setLoginMachId(logMachId);
                
                deviceList.add(u);
            }
        }
        j.setRows(deviceList);// 设置返回的行
        return j;
    }

}
