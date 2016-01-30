package com.cm.manage.controller.cms;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cm.manage.controller.base.BaseController;

/**
 * 客服管理控制器
 * @author chw
 *
 */
@Controller
@RequestMapping("/cmsController")
public class CmsController extends BaseController {
	private static final Logger logger = Logger.getLogger(CmsController.class);
	@RequestMapping(params="home")
	public String home(){
		return "/cms/home";
	}
   @RequestMapping(params="query")
	public String query(){
		return "/cms/query";
	}
   @RequestMapping(params="message")
   public String message(){
	   return "/cms/message";
   }
	@RequestMapping(params="widthdraw")
	public String withdraw(){
		return "/cms/withdraw";
	}
}
