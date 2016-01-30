package com.cm.manage.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.member.IMessageDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.member.IMessageService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.helpCenter.QuestionVo;
@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl implements
		IMessageService {
	
	@Autowired
	private IMessageDao messageDao;

	/**
	 * 用户留言记录
	 * @param dg
	 * @param vo
	 * @return
	 */
	@Override
	public EasyuiDataGridJson messageList(EasyuiDataGrid dg, QuestionVo vo) {
		return messageDao.messageList(dg, vo);
	}

}
