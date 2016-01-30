package com.cm.manage.service.member;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.helpCenter.QuestionVo;

public interface IMessageService extends IBaseService {
	/**
	 * 用户留言记录
	 * @param dg
	 * @param vo
	 * @return
	 */
	public EasyuiDataGridJson messageList(EasyuiDataGrid dg,QuestionVo vo);
}
