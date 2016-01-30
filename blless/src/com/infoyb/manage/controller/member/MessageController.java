package com.cm.manage.controller.member;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.member.IMessageService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.helpCenter.QuestionVo;

@Controller
@RequestMapping("/messageController")
public class MessageController extends BaseController {
	
	private static final Logger logger = Logger.getLogger(MessageController.class);
	
	@Autowired
	private IMessageService messageService;
	
	/**
	 * 用户留言记录
	 * @param dg
	 * @param vo
	 * @return
	 */
	@RequestMapping(params = "messageList")
    @ResponseBody
    public EasyuiDataGridJson messageList(EasyuiDataGrid dg, QuestionVo vo) {
        return messageService.messageList(dg, vo);
    }
}
