package com.cm.manage.controller.operate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.information.Url;
import com.cm.manage.model.operate.SoftwareVersion;
import com.cm.manage.service.operate.ISoftwareVersionService;
import com.cm.manage.vo.base.Json;

@Controller
@RequestMapping("/softwareVersionController")
public class SoftwareVersionController extends BaseController{
	
	@Autowired
	private ISoftwareVersionService softwareVersionService;
	/**
	 * 跳转 软件版本 管理页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "toSoftwareVersion")
	public String toUrlList(HttpServletRequest request) {
		return "/operate/softwareVersionManage";
	}

	/**
	 * 查询软件版本列表
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(params = "softwareVersionList")
	@ResponseBody
	public List<SoftwareVersion> urlList(String svType) {
		return softwareVersionService.selectSoftwareVersionByType(svType);
	}

	/**
	 * 添加软件版本
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	@RequestMapping(params = "addSoftwareVersion")
	@ResponseBody
	public Json addSoftwareVersion(HttpServletRequest request, SoftwareVersion softwareVersion ) {
		Json j = new Json();
		try {
			softwareVersion.setSvType("");
			softwareVersion = softwareVersionService.addSoftwareVersion(softwareVersion);
			j.setObj(softwareVersion);
			StringBuilder desc = new StringBuilder("增加");
			desc.append("软件版本:");
			desc.append(" code:");
			desc.append(softwareVersion.getSvCode());
			String svType=softwareVersion.getSvType();
			if(svType!=null){
				desc.append(" 类型:");
				if(svType.equals("01")){
					svType="android";
				}else if(svType.equals("02")){
					svType="ios";
				}
				desc.append(svType);
			}
			desc.append(" 名称:");
			desc.append(softwareVersion.getSvName());
			saveLog(request, "/softwareVersionController.do?addSoftwareVersion",
					CommonConstants.LOG_FOR_SOFTWARE_VERSION, desc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除软件版本
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	@RequestMapping(params = "delSoftwareVersion")
	@ResponseBody
	public Json delSoftwareVersion(HttpServletRequest request, SoftwareVersion softwareVersion ) {
		Json j = new Json();
		try {
			softwareVersionService.delSoftwareVersion(softwareVersion);
			StringBuilder desc = new StringBuilder("删除");
			desc.append("软件版本:");
			desc.append(softwareVersion.getSvCode());
			saveLog(request, "/softwareVersionController.do?delSoftwareVersion",
					CommonConstants.LOG_FOR_SOFTWARE_VERSION, desc.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑软件版本
	 * 
	 * @param request
	 * @param url
	 * @return
	 */
	@RequestMapping(params = "editSoftwareVersion")
	@ResponseBody
	public Json editSoftwareVersion(HttpServletRequest request, SoftwareVersion softwareVersion ) {
		Json j = new Json();
		try {
			softwareVersionService.editSoftwareVersion(softwareVersion);
			StringBuilder desc = new StringBuilder("编辑");
			desc.append("软件版本:");
			desc.append(" code:");
			desc.append(softwareVersion.getSvCode());
			String svType=softwareVersion.getSvType();
			if(svType!=null){
				desc.append(" 类型:");
				if(svType.equals("01")){
					svType="android";
				}else if(svType.equals("02")){
					svType="ios";
				}
				desc.append(svType);
			}
			desc.append(" 名称:");
			desc.append(softwareVersion.getSvName());
			saveLog(request, "/softwareVersionController.do?editSoftwareVersion",
					CommonConstants.LOG_FOR_SOFTWARE_VERSION, desc.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}
}
