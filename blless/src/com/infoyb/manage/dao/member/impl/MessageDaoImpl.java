package com.cm.manage.dao.member.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.member.IMessageDao;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.helpCenter.QuestionVo;
import com.cm.manage.vo.member.MessageVO;

@Repository
public class MessageDaoImpl implements IMessageDao {

	 @Autowired
	 private IBaseDao<Object> messageDao;
	/**
	 * 用户留言记录
	 * @param dg
	 * @param vo
	 * @return
	 */
	@Override
	public EasyuiDataGridJson messageList(EasyuiDataGrid dg, QuestionVo vo) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		StringBuilder str=new StringBuilder(" select q.CREATEDATE ,q.CONTENT,qt.QTNAME,q.STATE,u.NAME hander,");
		str.append(" a.CREATEDATE handTime from HC_QUESTION q left join HC_ANSWER_OR_REQUESTION a on q.ID=a.QUESTION_ID ");
		str.append(" left join HC_QUESTION_TYPE qt on qt.ID=q.QUESTION_TYPE_ID ");
		str.append(" left join SYS_USER u on a.SYS_USER_ID=u.ID where q.USER_CODE=? ");
		str.append(" UNION ALL ");
		str.append(" select a.CREATEDATE ,a.CONTENT,qt.QTNAME,a.STATE,u.NAME hander,");
		str.append(" a1.CREATEDATE handTime from HC_ANSWER_OR_REQUESTION a   inner join HC_ANSWER_OR_REQUESTION a1 on a1.PARENT_ID=a.ID left join HC_QUESTION q on q.ID=a.QUESTION_ID ");
		str.append(" left join HC_QUESTION_TYPE qt on qt.ID=q.QUESTION_TYPE_ID ");
		str.append(" left join SYS_USER u on a1.SYS_USER_ID=u.ID where a.USER_CODE=? ");
		List<Object> values = new ArrayList<Object>();
		values.add(vo.getUserCode());
		values.add(vo.getUserCode());
		String totalHql = " select count(*)  from (" + str.toString() + ")";
        j.setTotal(messageDao.countBySql(totalHql, values).longValue());// 设置总记录数

		List<Map> messageMapList = messageDao.findBySql(str.toString(), values, dg.getPage(), dg.getRows());// 查询分页
		
		List<MessageVO> messageList = new ArrayList<MessageVO>();
		if (messageMapList != null && messageMapList.size() > 0) {// 转换模型
            for (Map map : messageMapList) {
            	MessageVO message=new MessageVO();
            	Date createTime = (Date) map.get("CREATEDATE");
            	message.setCreateTime(DateUtil.format(createTime));
            	message.setContent((String)map.get("CONTENT"));
            	message.setQuestionType((String)map.get("QTNAME"));
            	BigDecimal status =  map.get("STATE")==null?new BigDecimal(0):(BigDecimal)map.get("STATE");
            	message.setStatus(status.intValue());
            	message.setHandler((String)map.get("HANDER"));
            	Date handleTime = (Date) map.get("HANDERTIME");
            	if(handleTime!=null){
            		
            		message.setHandleTime(DateUtil.format(handleTime));
            	}
            	messageList.add(message);
            }
	}
		j.setRows(messageList);// 设置返回的行
		return j;
	}
}
