package com.cm.manage.controller.indexManage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.util.ConfigUtils;
import com.cm.manage.util.lottery.LotteryList;
import com.cm.manage.util.lottery.bean.LotteryClass;
import com.cm.manage.vo.base.Json;
import com.cm.other.http.IIndexManageHttpService;
import com.cm.other.http.bean.ColumnBean;
import com.cm.other.http.bean.ContentBean;
import com.cm.other.http.bean.ItemBean;
import com.cm.other.http.bean.ItemDetailBean;
import com.cm.other.http.bean.TemplateBean;

/**
 * 首页设置控制器
 *
 * @author
 */
@Controller
@RequestMapping("/indexManageController")
public class IndexManageController extends BaseController {

    private static final Logger logger = Logger.getLogger(IndexManageController.class);

    @Resource(name = "indexManageInterface")
    private IIndexManageHttpService indexManageHttpService;

    /**
     * 初始化客户端首页设置
     *
     * @return
     */
    @RequestMapping(params = "toIndexManageList")
    public String toindexManageList(HttpServletRequest request) {
        return "/indexManage/columnList";
    }

    /**
     * 栏目列表查询
     *
     * @return
     */
    @RequestMapping(params = "columnList")
    @ResponseBody
    public  List<ColumnBean> columnList() {      
        return indexManageHttpService.listColumn();
    }

    /***
     * @describe 栏目编辑
     * @param request
     * @param columnBean
     * @return
     */
    @RequestMapping(params = "editColumn")
    @ResponseBody
	public Json editColumn(HttpServletRequest request, ColumnBean columnBean) {
		Json j = new Json();
		try {
			boolean result = indexManageHttpService.editColumn(columnBean);
			if (true == result) {
				j.setMsg("编辑成功");
				j.setSuccess(true);
				StringBuilder desc = new StringBuilder("修改的一级栏目的");
				desc.append("栏目编码为: ");
				desc.append(columnBean.getColumnCode());
				desc.append("栏目描述为: ");
				desc.append(columnBean.getColumnDesc());
				desc.append("推广语为: ");
				desc.append(columnBean.getRemark());
				desc.append("权重为: ");
				desc.append(columnBean.getSort());
				saveLog(request, "indexManageController.do?editColumn",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
			} else {
				j.setMsg("编辑失败");
				j.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return j;
	}
    
    /**
     * 跳转到子栏目设置
     *
     * @return
     */
    @RequestMapping(params = "toItemList")
    public String toMsgTemplate() {
        return "/indexManage/itemList";
    }

    /**
     * 子栏目列表查询
     *
     * @return
     */
    @RequestMapping(params = "itemList")
    @ResponseBody
    public List<ItemDetailBean> itemList(HttpServletRequest request) {
    	List<ItemDetailBean> itemList=(List<ItemDetailBean>)indexManageHttpService.listItem();
    	StringBuffer templateString=new StringBuffer();
    	StringBuilder desc = new StringBuilder("二级栏目查询");
    	try {
        	for(ItemDetailBean iBean:itemList){
        		List<TemplateBean> tList=(List<TemplateBean>)iBean.getTemplateList();
        		List<ContentBean> cList=(List<ContentBean>)iBean.getContentList();
        		if(tList==null&&cList==null){
        			iBean.setTemplateString("");
        		}else{
        			if(tList!=null&&tList.size()>0){
            			for(TemplateBean tBean:tList){
                			templateString.append(tBean.getContent()+"<br>");
                		}
            			iBean.setTemplateString(templateString.toString());
        			}else if(cList!=null&&cList.size()>0){
        				for(ContentBean cBean:cList){
            				templateString.append(cBean.getContent()+"<br>");
                		}
            			iBean.setTemplateString(templateString.toString());
        			}
        		}
        		templateString.delete(0, templateString.length());
        		desc.append("栏目编码为: ");
        		desc.append(iBean.getItemCode());
        	}
        	saveLog(request, "indexManageController.do?itemList",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
        return itemList;
    }

    /**
     * 通过itemCode查询模板列表
     *
     * @return
     */
	@RequestMapping(params="templateList")
	@ResponseBody
	public List<TemplateBean> templateList(HttpServletRequest request,String itemCode) {
		List<TemplateBean> list = indexManageHttpService.listTemplate(itemCode);
		StringBuilder desc = new StringBuilder("通过itemCode查询模板列表");
		desc.append("itemCode为: ");
		desc.append(itemCode);
		saveLog(request, "indexManageController.do?templateList",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		return list;
	}
	
    /***
     * @describe 子栏目编辑
     * @param request
     * @param itemBean
     * @return
     */
    @RequestMapping(params = "editItem")
    @ResponseBody
	public Json editItem(HttpServletRequest request, ItemBean itemBean) {
		Json j = new Json();
		boolean result = indexManageHttpService.editItem(itemBean);
		if(true==result){
			j.setMsg("编辑成功");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("子栏目编辑");
			desc.append("栏目编码为: ");
    		desc.append(itemBean.getItemCode());
			saveLog(request, "indexManageController.do?editItem",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		}else{
			j.setMsg("编辑失败");
			j.setSuccess(false);	
		}
		return j;
	}
    
    /***
     * @describe 子栏目模板编辑
     * @param request
     * @param itemBean
     * @return
     */
    @RequestMapping(params = "editTemplate")
    @ResponseBody
	public Json editTemplate(HttpServletRequest request, TemplateBean templateBean) {
		Json j = new Json();
		boolean result=indexManageHttpService.editTemplate(templateBean);
		if(true==result){
			j.setMsg("编辑成功");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("子栏目模板编辑");
			desc.append("栏目编码为: ");
    		desc.append(templateBean.getItemCode());
			saveLog(request, "indexManageController.do?editTemplate",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		}else{
			j.setMsg("编辑失败");
			j.setSuccess(false);	
		}
		return j;
	}
    
    /***
     * @describe 子栏目模板删除
     * @param request
     * @param itemBean
     * @return
     */
    @RequestMapping(params = "delTemplate")
    @ResponseBody
	public Json delTemplate(HttpServletRequest request, Long id) {
		Json j = new Json();
		j.setMsg("删除失败");
		j.setSuccess(false);
		try {
			indexManageHttpService.removeTemplate(id);
			j.setMsg("删除成功");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("子栏目模板删除");
			desc.append("主键为: ");
    		desc.append(id);
			saveLog(request, "indexManageController.do?delTemplate",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
    
    /**
     * 通过itemCode查询内容列表
     *
     * @return
     */
	@RequestMapping(params="contentList")
	@ResponseBody
	public List<ContentBean> contentList(HttpServletRequest request, String itemCode) {
		List<ContentBean> list = indexManageHttpService.getContentList(itemCode);
		LotteryList lottery = new LotteryList();
		for(ContentBean cBean:list){
			if(null!=cBean.getPrimaryKey()&&""!=cBean.getPrimaryKey()){
				LotteryClass lotteryClass = lottery.getLotteryClass(cBean.getPrimaryKey());
				cBean.setPrimaryKeyChinese(lotteryClass.getName());
			}
		}
		StringBuilder desc = new StringBuilder("通过itemCode查询内容列表");
		desc.append("itemCode为: ");
		desc.append(itemCode);
		saveLog(request, "indexManageController.do?contentList",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		return list;
	}
	
    /***
     * @describe 新增天天加奖
     * @param request
     * @param itemBean
     * @return
     */
    @RequestMapping(params = "addBonus")
    @ResponseBody
	public Json addBonus(HttpServletRequest request,String lotteryCode, String content) {
		Json j = new Json();
		boolean result=indexManageHttpService.addBonus(lotteryCode, content);
		if(true==result){
			j.setMsg("编辑成功");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("新增天天加奖");
			desc.append("lotteryCode为: ");
			desc.append(lotteryCode);
			desc.append("content为: ");
			desc.append(content);
			saveLog(request, "indexManageController.do?addBonus",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		}else{
			j.setMsg("编辑失败");
			j.setSuccess(false);	
		}
		return j;
	}
    
    /***
     * @describe 天天加奖删除
     * @param request
     * @param itemBean
     * @return
     */
    @RequestMapping(params = "cancelAddBonus")
    @ResponseBody
	public Json cancelAddBonus(HttpServletRequest request, Long id) {
		Json j = new Json();
		j.setMsg("删除失败");
		j.setSuccess(false);
		try {
			indexManageHttpService.cancelAddBonus(id);
			j.setMsg("删除成功");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("天天加奖删除");
			desc.append("id为: ");
			desc.append(id);
			saveLog(request, "indexManageController.do?cancelAddBonus",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
    
    /***
     * @describe 新增幸运荐号
     * @param request
     * @param lotteryCode
     * @return
     */
    @RequestMapping(params = "luckNumber")
    @ResponseBody
	public Json luckNumber(HttpServletRequest request,String lotteryCode) {
		Json j = new Json();
		boolean result=indexManageHttpService.luckNumber(lotteryCode);
		if(true==result){
			j.setMsg("编辑成功");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("新增幸运荐号");
			desc.append("lotteryCode为: ");
			desc.append(lotteryCode);
			saveLog(request, "indexManageController.do?luckNumber",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		}else{
			j.setMsg("编辑失败");
			j.setSuccess(false);	
		}
		return j;
	}
    
    /***
     * @describe 幸运荐号删除
     * @param request
     * @param itemBean
     * @return
     */
    @RequestMapping(params = "cancelLuckNumber")
    @ResponseBody
	public Json cancelLuckNumber(HttpServletRequest request, Long id) {
		Json j = new Json();
		j.setMsg("删除失败");
		j.setSuccess(false);
		try {
			indexManageHttpService.cancelLuckNumber(id);
			j.setMsg("删除成功");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("幸运荐号删除");
			desc.append("id为: ");
			desc.append(id);
			saveLog(request, "indexManageController.do?cancelLuckNumber",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
    
    /**
     * 文件上传
     * @param bean
     * @return
     */
    @RequestMapping(params="fileUpload")
    @ResponseBody
    public Json fileUpload(@RequestParam MultipartFile  myfile,HttpServletRequest request)throws IOException{
    	Json j=new Json();
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dff = new SimpleDateFormat("yyyyMMddHHHmmssS");
		String ymd = df.format(new Date());
		String ymdhms = dff.format(new Date());
    	String imgPath = ConfigUtils.getValue("indexTxtPath");
		//上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
			if(myfile.isEmpty()){
				j.setSuccess(false);
				j.setMsg("请选择文件后上传");
			}else{
				originalFilename = myfile.getOriginalFilename();
				 if(!originalFilename.endsWith(".bmp")&&!originalFilename.endsWith(".jpg")&&!originalFilename.endsWith(".gif")&&!originalFilename.endsWith(".psd")&&!originalFilename.endsWith(".jpeg")&&!originalFilename.endsWith(".png")){  
		                j.setSuccess(false);
						j.setMsg("只允许上传图片格式的文件");
						return j;
		            }
				System.out.println("文件原名: " + originalFilename);
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("========================================");
				try {
					//String dir = msgTxtPath + "/" + ymd;
					String dir = new StringBuffer().append(request.getRealPath(imgPath)).append("/").append(ymd).toString();
					File dirFile = new File(dir);
					if (!dirFile.exists()) {
						dirFile.mkdirs();
					}
					String fileName = ymdhms + originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
					String filePath = dir + "/" + fileName;
					File file= new File(filePath);
					FileUtils.copyInputStreamToFile(myfile.getInputStream(),file);
					Map<String,String> returnMap=new HashMap<String, String>();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
					String out="/index/"+ ymd+"/" + fileName;
					String src = basePath + out;
					returnMap.put("filePath",out);
					returnMap.put("src", src);
					j.setObj(returnMap);
					j.setMsg("文件上传成功!");
					j.setSuccess(true);
					StringBuilder desc = new StringBuilder("文件上传");
					desc.append("文件原名为: ");
					desc.append(originalFilename);
					desc.append("文件名称为: ");
					desc.append(myfile.getName());
					desc.append("文件长度为: ");
					desc.append(myfile.getSize());
					desc.append("文件类型为: ");
					desc.append(myfile.getContentType());
					saveLog(request, "indexManageController.do?fileUpload",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
				} catch (IOException e) {
					System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
					e.printStackTrace();
					j.setSuccess(false);
					j.setMsg("文件上传失败，请重试！！");
				}
		}
		return j;
    }
    
    /***
     * @describe 编辑条目状态
     * @param request
     * @param status 0：隐藏，1：展示
     * @return
     */
    @RequestMapping(params = "editItemStatus")
    @ResponseBody
	public Json editItemStatus(HttpServletRequest request,String itemCode, Integer status) {
		Json j = new Json();
		boolean result=indexManageHttpService.editItemStatus(itemCode,status);
		if(true==result){
			j.setMsg("编辑成功");
			j.setSuccess(true);
			StringBuilder desc = new StringBuilder("编辑条目状态");
			desc.append("itemCode为: ");
			desc.append(itemCode);
			desc.append("status为: ");
			desc.append(status);
			saveLog(request, "indexManageController.do?editItemStatus",CommonConstants.LOG_FOR_INDEX_MANAGE, desc.toString());
		}else{
			j.setMsg("编辑失败");
			j.setSuccess(false);	
		}
		return j;
	}
}
