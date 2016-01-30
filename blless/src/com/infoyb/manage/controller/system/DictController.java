package com.cm.manage.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.system.IDictService;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.system.DictDetail;
import com.cm.manage.vo.system.DictType;
/**
 * 字典控制器
 * @author 
 *
 */
@Controller
@RequestMapping("/dictController")
public class DictController extends BaseController {

    private static final Logger logger = Logger.getLogger(DictController.class);

    @Autowired
    private IDictService dictService;

    @RequestMapping(params="accountDict")
    public String accoutDict(){
    	return "/dict/account";
    }
    @RequestMapping(params = "tmsDict")
    public String tmsDict() {
        return "/dict/tms";
    }
    
    @RequestMapping(params = "userDict")
    public String userDict() {
        return "/dict/user";
    }
    @RequestMapping(params = "dictType")
	@ResponseBody
	public List<EasyuiTreeNode> tree(String model) {
		return dictService.getDictType(model);
	}
    @RequestMapping(params = "dictDetail")
    @ResponseBody
    public EasyuiDataGridJson dictDetail(EasyuiDataGrid dg,String model, Long type_id) {
        return dictService.getDictDetail(dg, model, type_id);
    }
    
    /**
	 * 添加类型
	 * 
	 * @param dict
	 * @return
	 */
	@RequestMapping(params = "typeAdd")
	@ResponseBody
	public DictType typeAdd(HttpServletRequest request,DictType dict) {
		Long id=dict.getId();
		String model=dict.getModel();//0：账号;1： 订单; 2： 用户
		String modelStr="";
		if(model.equals("0")){
			modelStr="账户";
		}
		if(model.equals("1")){
			modelStr="订单";
		}
		if(model.equals("2")){
			modelStr="用户";
		}
		if(CommonUtil.isNotEmpty(id)){
			saveLog(request, "/dictController.do?typeAdd", CommonConstants.LOG_FOR_SECURITY, modelStr+"更新类型"+dict.getName());
		}else{
			saveLog(request, "/dictController.do?typeAdd", CommonConstants.LOG_FOR_SECURITY, modelStr+"新增类型"+dict.getName());
		}
		return dictService.typeAdd(dict);
	}
	
	@RequestMapping(params = "typeInfo")
	@ResponseBody
	public Json typeInfo(String model,Long id) {
		Json j = new Json();
		DictType t=dictService.typeInfo(model, id);
		j.setObj(t);
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 删除类型
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "typeDel")
	@ResponseBody
	public Json typeDel(HttpServletRequest request,String model,Long id,String text) {
		Json j = new Json();
		dictService.typeDel(model,id);
		String modelStr="";
		if(model.equals("0")){
			modelStr="账户";
		}
		if(model.equals("1")){
			modelStr="订单";
		}
		if(model.equals("2")){
			modelStr="用户";
		}
		saveLog(request, "/dictController.do?typeDel", CommonConstants.LOG_FOR_SECURITY, modelStr+"删除类型"+text);
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 添加字典详细
	 * 
	 * @param dict
	 * @return
	 */
	@RequestMapping(params = "detailAdd")
	@ResponseBody
	public DictDetail detailAdd(HttpServletRequest request,DictDetail dict) {
        try {
            dictService.detailAdd(dict);
            //更新memcache
            dictService.initMemcached(dict.getTypeId(), dict.getModel());
            Long id=dict.getId();
            String model=dict.getModel();//0：账号;1： 订单; 2： 用户
    		String modelStr="";
    		if(model.equals("0")){
    			modelStr="账户";
    		}
    		if(model.equals("1")){
    			modelStr="订单";
    		}
    		if(model.equals("2")){
    			modelStr="用户";
    		}
    		if(CommonUtil.isNotEmpty(id)){
    			saveLog(request, "/dictController.do?detailAdd", CommonConstants.LOG_FOR_SECURITY, modelStr+"更新详细"+dict.getName());
    		}else{
    			saveLog(request, "/dictController.do?detailAdd", CommonConstants.LOG_FOR_SECURITY, modelStr+"新增详细"+dict.getName());
    		}
            return dict;
        } catch (Exception e) {
            return dict;
        }
	}
	

	/**
	 * 删除字典详细
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "detailDel")
	@ResponseBody
	public Json detailDel(HttpServletRequest request,String model,String ids) {
		Json j = new Json();
		dictService.detailDel(model,ids);
		String modelStr="";
		if(model.equals("0")){
			modelStr="账户";
		}
		if(model.equals("1")){
			modelStr="订单";
		}
		if(model.equals("2")){
			modelStr="用户";
		}
		String[] idList=ids.split(",");
		saveLog(request, "/dictController.do?detailDel", CommonConstants.LOG_FOR_SECURITY,modelStr+ idList.length+"条详细被删除");
		j.setSuccess(true);
		return j;
	}
}
