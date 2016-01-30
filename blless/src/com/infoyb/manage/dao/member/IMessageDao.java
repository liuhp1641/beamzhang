package com.cm.manage.dao.member;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.helpCenter.QuestionVo;

public interface IMessageDao {
	
	/**
	 * 用户留言记录
	 * @param dg
	 * @param vo
	 * @return
	 */
	public EasyuiDataGridJson messageList(EasyuiDataGrid dg,QuestionVo vo);
}
