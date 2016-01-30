package com.cm.manage.controller.msg;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

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
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.DateUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.message.http.IMessageManagesHttpService;
import com.cm.message.http.bean.HttpPageBean;
import com.cm.message.http.bean.MessageCustomInfoBean;
import com.cm.message.http.bean.MessageInfoBean;
import com.cm.message.http.bean.MessageInfoQueryBean;
import com.cm.message.http.bean.MessageTemplateBean;
import com.cm.message.http.bean.MessageTypeBean;

/**
 * 消息控制器
 *
 * @author
 */
@Controller
@RequestMapping("/msgController")
public class MsgController extends BaseController {

    private static final Logger logger = Logger.getLogger(MsgController.class);

    @Resource(name = "messageManagesHttpService")
    private IMessageManagesHttpService messageHttpService;

    /**
     * 初始化消息查询
     *
     * @return
     */
    @RequestMapping(params = "toMsgList")
    public String toMsgList(HttpServletRequest request, String userCode) {
        return "/msg/msgList";
    }

    /**
     * 消息内容查询
     *
     * @return
     */
    @RequestMapping(params = "msgList")
    @ResponseBody
    public EasyuiDataGridJson msgList(EasyuiDataGrid dg, String messageType,Integer status,Integer readStatus) {
        MessageInfoBean messageInfoBean = new MessageInfoBean();
        messageInfoBean.setMessageCode(messageType);
        messageInfoBean.setStatus(status);
        if(readStatus==null){
        	messageInfoBean.setReadNum(null);
        }else{
        	messageInfoBean.setReadNum(readStatus.longValue());
        }

        HttpPageBean<MessageInfoBean> httpPageBean = messageHttpService.getMessageInfoList(messageInfoBean, dg.getPage(), dg.getRows());
        EasyuiDataGridJson eJson = new EasyuiDataGridJson();
        eJson.setTotal(httpPageBean.getItemTotal());
        eJson.setRows(httpPageBean.getPageContent());
        return eJson;
    }

    /**
     * 跳转到消息模板
     *
     * @return
     */
    @RequestMapping(params = "toMsgTemplate")
    public String toMsgTemplate() {
        return "/msg/msgTemplateList";
    }

    /**
     * 消息模板查询
     *
     * @return
     */
    @RequestMapping(params = "msgTemplateList")
    @ResponseBody
    public List<MessageTemplateBean> msgTemplateList() {
        List<MessageTemplateBean> messageTemplateBeanList =  messageHttpService.getMessageTemplateList();
        return messageTemplateBeanList;
    }

    /**
     * 跳转到消息类型排序管理
     *
     * @return
     */
    @RequestMapping(params = "toMsgTypePriority")
    public String toMsgTypePriority() {
        return "/msg/msgTypePriority";
    }

    /**
     * 消息模板查询
     *
     * @return
     */
    @RequestMapping(params = "msgTypePriorityList")
    @ResponseBody
    public List<MessageTypeBean> msgTypePriorityList() {
        return messageHttpService.getMessageTypeList();
    }

    /**
     * 更改 权重
     *
     * @return
     */
    @RequestMapping(params = "updateTypePriority")
    @ResponseBody
    public Json updateTypePriority(HttpServletRequest request, String typeCode, String typeName, int priority) {
        Json j = new Json();
        try {
            MessageTypeBean msgTypePriorityBean = new MessageTypeBean();
            msgTypePriorityBean.setTypeCode(typeCode);
            msgTypePriorityBean.setPriority(priority);
            messageHttpService.updateMessageType(msgTypePriorityBean);
            j.setMsg("修改操作项成功");
            StringBuilder desc = new StringBuilder("修改");
            desc.append("类型名称: ");
            desc.append(typeName);
            desc.append("操作项为: ");
            desc.append(priority);
            saveLog(request, "/msgController.do?updateTypePriority",
                    CommonConstants.LOG_FOR_MESSAGE, desc.toString());
        } catch (Exception e) {
            j.setMsg("修改操作项失败");
            e.printStackTrace();
        }
        j.setSuccess(true);
        return j;
    }
    /**
     * 推送管理
     * @param request
     * 
     * @return
     */
    @RequestMapping(params = "toMsgCustomList")
    public String toMsgCustomList(HttpServletRequest request) {
        return "/msg/msgCustomList";
    }

    /**
     * 推送列表查询
     *
     * @return
     */
    @RequestMapping(params = "msgCustomList")
    @ResponseBody
    public EasyuiDataGridJson msgCustomList(EasyuiDataGrid dg, String startSendTime,String endSendTime ,Integer status,String title) {
    	MessageInfoQueryBean messageInfoBean = new MessageInfoQueryBean();
    	if(CommonUtil.isNotEmpty(startSendTime)){
    		
    		messageInfoBean.setStartSendTime(DateUtil.format(startSendTime,"yy-MM-dd HH:mm:ss"));
    	}
    	if(CommonUtil.isNotEmpty(endSendTime)){
    		
    		messageInfoBean.setEndSendTime(DateUtil.format(endSendTime,"yy-MM-dd HH:mm:ss"));
    	}
    	messageInfoBean.setStatus(status);
    	messageInfoBean.setTitle(title);
    	HttpPageBean<MessageCustomInfoBean> httpPageBean = messageHttpService.getMessageInfoList(messageInfoBean, dg.getPage(), dg.getRows());
        EasyuiDataGridJson eJson = new EasyuiDataGridJson();
        eJson.setTotal(httpPageBean.getItemTotal());
        eJson.setRows(httpPageBean.getPageContent());
        return eJson;
    }
    /**
     * 推送发布
     * @return
     */
    @RequestMapping(params="addMsg")
    public String addMsg(){
    	return "/msg/addMsg";
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
    	String msgTxtPath = ConfigUtils.getValue("msgTxtPath");
    	//String realPath = request.getSession().getServletContext().getRealPath(msgTxtPath);
		//上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
			if(myfile.isEmpty()){
				j.setSuccess(false);
				j.setMsg("请选择文件后上传");
			}else{
				originalFilename = myfile.getOriginalFilename();
				 if(!originalFilename.endsWith(".txt")){  
		                j.setSuccess(false);
						j.setMsg("只允许上传图片txt格式");
						return j;
		            }
				System.out.println("文件原名: " + originalFilename);
				System.out.println("文件名称: " + myfile.getName());
				System.out.println("文件长度: " + myfile.getSize());
				System.out.println("文件类型: " + myfile.getContentType());
				System.out.println("========================================");
				try {
					String dir = msgTxtPath + "/" + ymd;
					File dirFile = new File(dir);
					if (!dirFile.exists()) {
						dirFile.mkdirs();
					}
					String fileName = ymdhms + ".txt";
					String filePath = dir + "/" + fileName;
					File file= new File(filePath);
					FileUtils.copyInputStreamToFile(myfile.getInputStream(),file);
					Map<String,String> returnMap=new HashMap<String, String>();
					returnMap.put("filePath", file.getPath());
					returnMap.put("fileName", file.getName());
					j.setObj(returnMap);
					j.setMsg("文件上传成功!");
					j.setSuccess(true);
				} catch (IOException e) {
					System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
					e.printStackTrace();
					j.setSuccess(false);
					j.setMsg("文件上传失败，请重试！！");
				}
		}
		return j;
    }
    @RequestMapping(params="saveMsg")
    @ResponseBody
    public Json saveMsg(HttpServletRequest request,MessageCustomInfoBean bean){
    	Json j=new Json();
    	try{
    	boolean flag=messageHttpService.saveCustomMessage(bean);
    	if(flag){
    		StringBuilder desc = new StringBuilder("发布");
    		desc.append("推送: ");
    		desc.append(bean.getTitle());
    		
    		saveLog(request, "/msgController.do?saveMsg",
    				CommonConstants.LOG_FOR_MESSAGE, desc.toString());
    	}
    	j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 读取文件
     * @param filePath
     * @return
     */
    @RequestMapping(params="readTxt")
    @ResponseBody
    public Json readTxt(String filePath){
    	Json j=new Json();
    	File file=new File(filePath);
    	 try {
             String content = FileUtils.readFileToString(file, "GBK");
             j.setObj(content);
             j.setSuccess(true);
         } catch (IOException e) {
        	 j.setMsg(e.getMessage());
        	 j.setSuccess(false);
             e.printStackTrace();
         }
    	 return j;
    }
    /**
     * 发送消息
     * @param bean
     * @return
     */
    @RequestMapping(params="sendMessage")
    @ResponseBody
    public Json sendMessage(HttpServletRequest request,MessageCustomInfoBean bean){
    	Json j=new Json();
    	try{
    	boolean flag=messageHttpService.sendMessage(bean);
    	if(flag){
    		StringBuilder desc = new StringBuilder(bean.getMessageCode());
    		desc.append("消息发送 ");
    		saveLog(request, "/msgController.do?sendMessage",
    				CommonConstants.LOG_FOR_MESSAGE, desc.toString());
    	}
    	j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 再次发送
     * @param messageCode
     * @return
     */
    @RequestMapping(params="reSendMessage")
    @ResponseBody
    public Json reSendMessage(HttpServletRequest request,String messageCode){
    	Json j=new Json();
    	try{
    	boolean flag=messageHttpService.reSendMessage(messageCode);
    	if(flag){
    		StringBuilder desc = new StringBuilder(messageCode);
    		desc.append("消息再次发送 ");
    		saveLog(request, "/msgController.do?reSendMessage",
    				CommonConstants.LOG_FOR_MESSAGE, desc.toString());
    	}
    	j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 消息编辑
     * @param request
     * @param messageStr
     * @return
     */
    @RequestMapping(params="toMagEdit")
    public String toMagEdit(HttpServletRequest request,String messagStr){
    	try{
    		messagStr=URLDecoder.decode(messagStr, "UTF-8");
    		JSONObject jsonObj =(JSONObject) JSONSerializer.toJSON(messagStr);
    		jsonObj.remove("createTime");
    		MessageCustomInfoBean bean = (MessageCustomInfoBean) JSONObject.toBean(jsonObj, MessageCustomInfoBean.class);
    		String remoteFilePath=bean.getRemoteFilePath();
    		Pattern pattern = Pattern.compile("[^/\\\\]+$");  
    	    Matcher matcher = pattern.matcher(remoteFilePath);  
    	    if(matcher.find()) {  
    	        System.out.println(matcher.group());  
    	        //String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/")+1); 
    	        String fileName=matcher.group();
    	        request.setAttribute("fileName", fileName);
    	    }  
    		request.setAttribute("message", bean);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return "/msg/msgEdit";
    }
    @RequestMapping(params="editCustomMessage")
    @ResponseBody
    public Json editCustomMessage(HttpServletRequest request,MessageCustomInfoBean bean){
    	Json j=new Json();
    	try{
    	boolean flag=messageHttpService.editCustomMessage(bean);
    	if(flag){
    		StringBuilder desc = new StringBuilder("编辑");
    		desc.append("定制推送: ");
    		desc.append(bean.getTitle());
    		saveLog(request, "/msgController.do?editCustomMessage",
    				CommonConstants.LOG_FOR_MESSAGE, desc.toString());
    	}
    	j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
}
