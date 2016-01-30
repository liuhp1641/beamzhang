package com.cm.manage.controller.share;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.share.JoinReward;
import com.cm.manage.model.share.ShareModule;
import com.cm.manage.model.share.ShareReward;
import com.cm.manage.service.share.IShareService;
import com.cm.manage.util.ConfigUtils;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.share.ShareInfoVO;
@Controller
@RequestMapping("/shareController")
public class ShareController extends BaseController {
	private static final Logger logger = Logger.getLogger(ShareController.class);
	@Autowired
	private IShareService shareService;
	/**
	 * 跳转到分享活动
	 * @return
	 */
	@RequestMapping(params="toShareList")
	public String toShareList(){
		return "/share/shareList";
	}
	/**
	 * 分享活动列表
	 * @param dg
	 * @param shareVo
	 * @return
	 */
	@RequestMapping(params="shareList")
	@ResponseBody
	public EasyuiDataGridJson shareList(EasyuiDataGrid dg,ShareInfoVO shareVo) {
		return shareService.shareList(dg, shareVo);
	}
	/**
	 * 新建分享活动页面
	 * @return
	 */
	@RequestMapping(params="toShareActivity")
	public String toShareActivity(){
		return "/share/shareActivity";
	}
	/**
	 * 图片上传
	 * @param imgfile
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(params="fileUpload")
	@ResponseBody
	 public Json fileUpload(@RequestParam MultipartFile  imgfile,HttpServletRequest request)throws IOException{
	    	Json j=new Json();
	    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat dff = new SimpleDateFormat("yyyyMMddHHHmmssS");
			String ymd = df.format(new Date());
			String ymdhms = dff.format(new Date());
	    	String imgPath = ConfigUtils.getValue("shareImgPath");
	    	
			//上传文件的原名(即上传前的文件名字)
			String originalFilename = null;
				if(imgfile.isEmpty()){
					j.setSuccess(false);
					j.setMsg("请选择文件后上传");
				}else{
					originalFilename = imgfile.getOriginalFilename();
					Pattern reg=Pattern.compile("[.]jpg|jpeg|gif|bmp|png$");
					Matcher matcher=reg.matcher(originalFilename);
					if(!matcher.find()) {
						j.setMsg("文件类型不允许！");
						j.setSuccess(false);
						return j;
					}
					Double AllowImgFileSize=new Double(20);  //允许上传图片文件的大小 0为无限制  单位：KB 
					Integer AllowImgWidth=220;   //允许上传的图片的宽度 ŀ为无限制　单位：px(像素)
					Integer AllowImgHeight=220;    //允许上传的图片的高度 ŀ为无限制　单位：px(像素)
					BufferedImage sourceImg =ImageIO.read(imgfile.getInputStream());   
					Double ImgFileSize=new Double(String.format("%.1f",imgfile.getSize()/1024.0));
					Integer ImgWidth=sourceImg.getWidth();   //取得图片的宽度
					Integer ImgHeight=sourceImg.getHeight();  //取得图片的高度
					if(AllowImgWidth!=0&&AllowImgWidth<ImgWidth){
						j.setMsg("\n图片宽度超过限制。请上传宽度小于"+AllowImgWidth+"px的文件，当前图片宽度为"+ImgWidth+"px");
						j.setSuccess(false);
						return j;
					}
					  if(AllowImgHeight!=0&&AllowImgHeight<ImgHeight){
						  j.setMsg("\n图片高度超过限制。请上传高度小于"+AllowImgHeight+"px的文件，当前图片高度为"+ImgHeight+"px");
						  j.setSuccess(false);
					      return j;
					  }
					  if(AllowImgFileSize!=0&&AllowImgFileSize<ImgFileSize){
						  j.setMsg("\n图片文件大小超过限制。请上传小于"+AllowImgFileSize+"KB的文件，当前文件大小为"+ImgFileSize+"KB");
						  j.setSuccess(false);
					      return j;
					  }
			        /*System.out.println("文件大小: "+String.format("%.1f",imgfile.getSize()/1024.0));  
			        System.out.println(sourceImg.getWidth());  
			        System.out.println(sourceImg.getHeight());  
					System.out.println("文件原名: " + originalFilename);
					System.out.println("文件名称: " + imgfile.getName());
					System.out.println("文件长度: " + imgfile.getSize());
					System.out.println("文件类型: " + imgfile.getContentType());
					System.out.println("========================================");*/
					try {
						//String dir =imgPath + "/" + ymd;
						String dir = new StringBuffer().append(request.getRealPath(imgPath)).append("/").append(ymd).toString();
						File dirFile = new File(dir);
						if (!dirFile.exists()) {
							dirFile.mkdirs();
						}
						String fileName = ymdhms + originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
						String filePath = dir + "/" + fileName;
						File file= new File(filePath);
						FileUtils.copyInputStreamToFile(imgfile.getInputStream(),file);
						//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
						Map<String,String> returnMap=new HashMap<String, String>();
						returnMap.put("filePath", imgPath + "/" + ymd+"/" + fileName);
						returnMap.put("fileName", file.getName());
						j.setObj(returnMap);
						j.setMsg("图片上传成功!");
						j.setSuccess(true);
						StringBuilder desc = new StringBuilder("文件上传");
						desc.append("文件原名为: ");
						desc.append(originalFilename);
						desc.append("文件名称为: ");
						desc.append(imgfile.getName());
						desc.append("文件长度为: ");
						desc.append(imgfile.getSize());
						desc.append("文件类型为: ");
						desc.append(imgfile.getContentType());
						saveLog(request, "/shareController.do?fileUpload",CommonConstants.LOG_FOR_SHARE, desc.toString());
					} catch (IOException e) {
						System.out.println("图片[" + originalFilename + "]上传失败,堆栈轨迹如下");
						e.printStackTrace();
						j.setSuccess(false);
						j.setMsg("图片上传失败，请重试！！");
					}
			}
			return j;
	    }
	 
	/**
	 * 分享活动保存
	 * @param request
	 * @param share
	 * @param options
	 * @return
	 */
	@RequestMapping(params="saveActivity")
	@ResponseBody
	public Json saveActivity(HttpServletRequest request,ShareInfoVO share,String options){
		Json j=new Json();
		try{
			shareService.saveActivity(share, options);
    		saveLog(request,"/shareController.do?saveActivity", CommonConstants.LOG_FOR_SHARE, "创建分享活动："+share.getShareName());
    		j.setSuccess(true);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setMsg("分享活动创建失败");
    		j.setSuccess(false);
    	}
		return j;
	}
	/**
	 * 分享活动状态修改
	 * @param request
	 * @param operate
	 * @param shareId
	 * @param shareName
	 * @param status
	 * @return
	 */
	@RequestMapping(params="updateActivityStatus")
	@ResponseBody
	public Json updateActivityStatus(HttpServletRequest request,String operate,String shareId,String shareName,Integer status){
    	Json j=new Json();
    	try{
    		boolean flag=shareService.updateActivityStatus(shareId, status);
    		if(flag){
    			saveLog(request,"/shareController.do?updateActivityStatus", CommonConstants.LOG_FOR_SHARE, "("+shareName+")分享活动"+operate);
    		}
    		j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
	/**
	 * 跳转活动编辑页
	 * @param request
	 * @param shareId
	 * @return
	 */
	@RequestMapping(params="toShareActivityEdit")
	 public String toShareActivityEdit(HttpServletRequest request,String shareId){
	  ShareInfoVO shareInfo= shareService.shareInfo(shareId);
	  request.setAttribute("shareInfo", shareInfo);
	  String imgUrl=shareInfo.getShareImgUrl();
	  if(CommonUtil.isNotEmpty(imgUrl)){
	  		Pattern pattern = Pattern.compile("[^/\\\\]+$");  
	  		Matcher matcher = pattern.matcher(imgUrl);  
	  		if(matcher.find()) {  
	  			System.out.println(matcher.group());  
	  			//String fileName = remoteFilePath.substring(remoteFilePath.lastIndexOf("/")+1); 
	  			String fileName=matcher.group();
	  			request.setAttribute("fileName", fileName);
	  		}  
	  		
	  	}
	  List<ShareReward> baseReward=shareService.shareRewardInfo(shareId,0);
	  if(CommonUtil.isNotEmpty(baseReward)){
		  request.setAttribute("baseReward", baseReward.get(0));
	  }
	  List<ShareReward> extraReward=shareService.shareRewardInfo(shareId,1);
	  if(CommonUtil.isNotEmpty(extraReward)&&extraReward.size()>0){
		  request.setAttribute("extraReward", "true");
	  }else{
		  request.setAttribute("extraReward", "false");
	  }
	  JoinReward joinReward=shareService.joinRewardInfo(shareId);
	  request.setAttribute("joinReward", joinReward);
	  return "/share/shareActivityEdit";
	    	
	 }
	/**
	 * 额外奖励
	 * @param shareId
	 * @param baseType
	 * @return
	 */
	@RequestMapping(params="extraReward")
	@ResponseBody
	public List<ShareReward> extraReward(String shareId,Integer baseType){
		return shareService.shareRewardInfo(shareId, baseType);
	}
	/**
	 * 跳转到分享活动详情页
	 * @param request
	 * @param shareId
	 * @return
	 */
	@RequestMapping(params="toShareActivityDetail")
	 public String toShareActivityDetail(HttpServletRequest request,String shareId){
		  ShareInfoVO shareInfo= shareService.shareInfo(shareId);
		  request.setAttribute("shareInfo", shareInfo);
		  List<ShareReward> baseReward=shareService.shareRewardInfo(shareId,0);
		  if(CommonUtil.isNotEmpty(baseReward)){
			  request.setAttribute("baseReward", baseReward.get(0));
		  }
		  List<ShareReward> extraReward=shareService.shareRewardInfo(shareId,1);
		  if(CommonUtil.isNotEmpty(extraReward)&&extraReward.size()>0){
			  request.setAttribute("extraReward", "true");
		  }else{
			  request.setAttribute("extraReward", "false");
		  }
		  JoinReward joinReward=shareService.joinRewardInfo(shareId);
		  request.setAttribute("joinReward", joinReward);
		  return "/share/shareActivityDetail";
	    	
	 }
	/**
	 * 分享设置
	 * @return
	 */
	@RequestMapping(params="toShareSet")
	public String toShareSet(){
		return "/share/shareSet";
	}
	/**
	 * 分享模块列表
	 * @return
	 */
	@RequestMapping(params="shareModuleList")
	@ResponseBody
	public List<ShareModule> shareModuleList(){
		return shareService.shareModuleList();
	}
	/**
	 * 分享模块分享设置
	 * @param request
	 * @param shareType
	 * @param shareName
	 * @param status
	 * @return
	 */
	@RequestMapping(params="moduleShare")
	@ResponseBody
	public Json moduleShare(HttpServletRequest request,String shareType,String shareName,Integer status){
    	Json j=new Json();
    	try{
			boolean flag=shareService.moduleShare(shareType, shareName, status);
			if(flag){
				String msg="设置("+shareName+")分享模板";
				if(status==0){
					msg+="不可分享";
				}
				if(status==1){
					msg+="可分享";
				}
				saveLog(request,"/shareController.do?moduleShare", CommonConstants.LOG_FOR_SHARE, msg);
			}
			j.setSuccess(flag);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
	/**
	 * 额外奖励删除
	 * @param request
	 * @param num
	 * @param awardType
	 * @param amount
	 * @param shareRewardId
	 * @return
	 */
	@RequestMapping(params="deleteExtraReward")
	@ResponseBody
	  public Json deleteExtraReward(HttpServletRequest request,String shareName,Integer num,Integer awardType,Double amount,String shareRewardId){
	    	Json j=new Json();
	    	try{
				boolean flag=shareService.deleteExtraReward(shareRewardId);
				if(flag){
				  StringBuilder str=new StringBuilder("删除("+shareName+")");
				  str.append("分享活动的额外奖励：注册人数");
				  str.append(num);
				  str.append("奖励类别");
				
				  if(awardType==0){
					  str.append("红包");
				  }
				  if(awardType==1){
					  str.append("积分");
				  }
				  if(awardType==2){
					  str.append("彩票");
				  }
				  str.append("奖励数量").append(amount);
				  
				 saveLog(request,"/shareController.do?deleteExtraReward", CommonConstants.LOG_FOR_SHARE,str.toString() );
				}
				j.setSuccess(flag);
	    			
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		j.setSuccess(false);
	    	}
	    	return j;
	    }
	/**
	 * 分享活动删除
	 * @param request
	 * @param shareId
	 * @param shareName
	 * @return
	 */
	@RequestMapping(params="deleteShareActivity")
	@ResponseBody
	 public Json deleteShareActivity(HttpServletRequest request,String shareId,String shareName){
	    	Json j=new Json();
	    	try{
				boolean flag=shareService.deleteShareActivity(shareId);
				if(flag){
				  StringBuilder str=new StringBuilder("删除("+shareName+")");
				  str.append("分享活动");
				  saveLog(request,"/shareController.do?deleteShareActivity", CommonConstants.LOG_FOR_SHARE,str.toString() );
				}
				j.setSuccess(flag);
	    			
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		j.setSuccess(false);
	    	}
	    	return j;
	    }
}
