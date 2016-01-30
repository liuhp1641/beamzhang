package com.cm.manage.dao.sms.impl;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.sms.ISmsDao;
import com.cm.manage.model.sms.Sms;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.sms.MemberSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("smsDao")
public class SmsDaoImpl implements ISmsDao {
    @Autowired
    private IBaseDao<Sms> smsDao;

    @Override
    public EasyuiDataGridJson smsDatagrid(EasyuiDataGrid dg, MemberSms sms) {
        EasyuiDataGridJson j = new EasyuiDataGridJson();
        String sql = " select s.id as id,s.mobile as mobile,s.model as model,s.template_id as templateId,s.status as status,s.send_time as sendTime,"
                + " s.operator as operator,s.memo as memo,s.body as body,s.type as type from USER_SMS s where 1=1";
        List<Object> values = new ArrayList<Object>();
        if (sms != null) {
            if (CommonUtil.isNotEmpty(sms.getMemo())) {
                sql += " and s.memo like ? ";
                values.add("%" + sms.getMemo() + "%");
            }
            if (CommonUtil.isNotEmpty(sms.getMobile())) {
                sql += " and s.mobile like ? ";
                values.add("%" + sms.getMobile() + "%");
            }
            if (sms.getStatus() != null) {
                sql += " and s.status = ?";
                values.add(sms.getStatus());
            }
            if (CommonUtil.isNotEmpty(sms.getTemplateId())) {
                sql += " and s.template_id = ?";
                values.add(sms.getTemplateId());
            }
            if (CommonUtil.isNotEmpty(sms.getType())) {
                sql += " and s.type = ?";
                values.add(sms.getType());
            }
        }
        String totalHql = " select count(*)  from (" + sql + ")";
        j.setTotal(smsDao.countBySql(totalHql, values).longValue());// 设置总记录数
        sql += " order by s.create_time desc";
        List<Map> smsMaps = smsDao.findBySql(sql, values, dg.getPage(), dg.getRows());// 查询分页
        List<MemberSms> smsList = new ArrayList<MemberSms>();
        if (CommonUtil.isNotEmpty(smsMaps)) {// 转换模型
            for (Map map : smsMaps) {
                MemberSms s = new MemberSms();
                BigDecimal id = (BigDecimal) map.get("ID");
                s.setId(id.longValue());
                s.setMobile((String) map.get("MOBILE"));
                s.setMemo((String) map.get("MEMO"));
                s.setTemplateId((String) map.get("TEMPLATEID"));
                s.setType((String) map.get("TYPE"));
                BigDecimal status = (BigDecimal) map.get("STATUS");
                s.setStatus(status.intValue());
                Date sendTime = (Date) map.get("SENDTIME");
                if (CommonUtil.isNotEmpty(sendTime)) {
                    s.setSendTime(DateUtil.format(sendTime));
                }
                s.setOperator((String) map.get("OPERATOR"));
                s.setBody((String) map.get("BODY"));
                smsList.add(s);
            }
        }
        j.setRows(smsList);// 设置返回的行
        return j;
    }

    /**
     * 短信发送
     *
     * @param sms
     * @return
     */
    public void sendSms(MemberSms sms) {
        String mobileStr = sms.getMobileStr();
        String[] mobiles = mobileStr.split(",");
        if (mobiles != null && mobiles.length > 0) {
            String sql = "insert into USER_SMS(ID,MOBILE,BODY,STATUS,SEND_TIME,MODEL,TEMPLATE_ID,OPERATOR,MEMO,type,create_time) VALUES (SEQ_USER_SMS.NEXTVAL,?,?,0,?,'sms',?,?,?,?,sysdate)";
            for (int i = 0; i < mobiles.length; i++) {
                List<Object> values = new ArrayList<Object>();
                values.add(mobiles[i]);
                values.add(sms.getBody());
                values.add(DateUtil.format(sms.getSendTime(), "yyyy-MM-dd HH:mm:ss"));
                values.add(sms.getTemplateId());
                values.add(sms.getOperator());
                values.add(sms.getMemo());
                values.add(sms.getType());
                smsDao.executeSql(sql, values);
            }
        }

    }


}
