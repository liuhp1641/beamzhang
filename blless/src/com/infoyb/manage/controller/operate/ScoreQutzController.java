package com.cm.manage.controller.operate;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
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
import com.cm.manage.model.score.ScoreQutzOption;
import com.cm.manage.model.score.ScoreQutzType;
import com.cm.manage.service.member.IMemberService;
import com.cm.manage.service.operate.IScoreQutzService;
import com.cm.manage.util.ConfigUtils;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.score.ScoreQutzTypeVO;
import com.cm.manage.vo.score.ScoreQutzUserVO;
import com.cm.manage.vo.score.ScoreQutzVO;
import com.cm.score.http.IScoreQutzHttpService;

@Controller
@RequestMapping("/scoreQutzController")
public class ScoreQutzController extends BaseController {

    private static final Logger logger = Logger.getLogger(ScoreQutzController.class);

    @Autowired
    private IScoreQutzService scoreQutzService;
    
    @Autowired
    private IMemberService memberService;
    
    @Resource
    private IScoreQutzHttpService scoreQutzInterface;
   
    /**
     * 跳转到积分竞猜
     *
     * @return
     */
    @RequestMapping(params = "toScoreQutz")
    public String toScoreQutz(HttpServletRequest request) {
    	List<Map> typeList=scoreQutzService.scoreQutzType();
    	request.setAttribute("qutzType", typeList);
        return "/score/scoreQutz";
    }


    /**
     * 积分竞猜查询列表
     *
     * @return
     */
    @RequestMapping(params = "scoreQutzList")
    @ResponseBody
    public EasyuiDataGridJson scoreQutzList(EasyuiDataGrid dg, ScoreQutzVO score) {
      
        return scoreQutzService.scoreQutzList(dg, score);
    }

    /**
     * 新建奖池页面
     * @return
     */
    @RequestMapping(params="toScoreQutzType")
    public String toScoreQutzType(){
    	return "/score/scoreQutzType";
    }
    /**
     * 最大奖池ID
     * @return
     */
    @RequestMapping(params="maxQutzType")
    @ResponseBody
    public Json maxQutzType(){
    	Json j=new Json();
    	Integer maxId=scoreQutzService.maxQutzTypeId();
    	j.setObj(maxId);
    	return j;
    			
    }
    /**
     * 奖池保存
     * @param request
     * @param vo
     * @return
     */
    @RequestMapping(params="saveQutzType")
    @ResponseBody
    public Json saveQutzType(HttpServletRequest request,ScoreQutzType vo){
    	Json j=new Json();
    	try{
    		scoreQutzService.saveQutzType(vo);
    		j.setSuccess(true);
    		saveLog(request,"/scoreQutzController.do?saveQutzType", CommonConstants.LOG_FOR_SCORE_QUTZ, "创建竞猜奖池:"+vo.getQutzTypeName());
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 奖池删除
     * @param request
     * @param qutzTypeId
     * @return
     */
    @RequestMapping(params="delQutzType")
    @ResponseBody
    public Json delQutzType(HttpServletRequest request,String qutzTypeId,String qutzTypeName){
    	Json j=new Json();
    	try{
    		boolean flag=scoreQutzService.delQutzType(qutzTypeId);
    		saveLog(request,"/scoreQutzController.do?delQutzType", CommonConstants.LOG_FOR_SCORE_QUTZ, "删除("+qutzTypeName+")竞猜奖池");
    		j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    
    /**
     * 
     * 奖池信息列表
     * @return
     */
    @RequestMapping(params="scoreQutzTypeList")
    @ResponseBody
    public List<ScoreQutzTypeVO> scoreQutzTypeList(){
    	List<Map> typeList=scoreQutzService.scoreQutzType();
    	List<ScoreQutzTypeVO> list=new ArrayList<ScoreQutzTypeVO>();
    	if(typeList!=null&&typeList.size()>0){
    		for(int i=0;i<typeList.size();i++){
    			Map map=typeList.get(i);
    			ScoreQutzTypeVO vo=new ScoreQutzTypeVO();
    			BigDecimal id=(BigDecimal) map.get("ID");
    			vo.setId(id.intValue());
    			BigDecimal bonusType=(BigDecimal) map.get("BONUS_TYPE");
    			vo.setBonusType(bonusType.intValue());
    			vo.setBonusUserCode((String)map.get("BONUS_USER_CODE"));
    			vo.setIssueCode((String)map.get("ISSUE_CODE"));
    			vo.setQutzTypeId((String)map.get("QUTZ_TYPE_ID"));
    			vo.setQutzTypeName((String)map.get("QUTZ_TYPE_NAME"));
    			vo.setBonusUserName((String)map.get("USER_NAME"));
    			list.add(vo);
    		}
    	}
    	return list;
    }
    
    /**
     * 创建活动界面
     * @return
     */
    @RequestMapping(params="toQutzActivity")
    public String toQutzActivity(HttpServletRequest request){
    	List<Map> typeList=scoreQutzService.scoreQutzType();
    	request.setAttribute("qutzType", typeList);
    	
    	List<EasyuiTreeNode> channel = memberService.channeltree();
        request.setAttribute("channel", channel);
        
    	return "/score/scoreQutzActivity";
    }
    
    /**
     * 奖池信息及期次
     * @param qutzTypeId
     * @return
     */
    @RequestMapping(params="qutzTypeAndIssue")
    @ResponseBody
    public Json qutzTypeAndIssue(String qutzTypeId){
    	Json j=new Json();
    	if(CommonUtil.isNotEmpty(qutzTypeId)){
    		Map<String,Object> resultMap=new HashMap<String, Object>();
    		Map qutzType=scoreQutzService.qutzTypeInfo(qutzTypeId);
    		resultMap.put("qutzType", qutzType);
    		BigDecimal bonusType=(BigDecimal) qutzType.get("BONUS_TYPE");
    		Map lastScoreQutz=scoreQutzService.lastScoreQutz(qutzTypeId, bonusType.intValue());
    		resultMap.put("lastScoreQutz", lastScoreQutz);
    		j.setObj(resultMap);
    		
    	}else{
    		j.setObj(null);
    	}
    	return j;
    }
    
    /**
	  * 竞猜答案
	  * @param qutzId
	  * @return
	  */
    @RequestMapping(params="scoreQutzOption")
    @ResponseBody
	 public List<ScoreQutzOption> scoreQutzOption(String qutzId){
		 return scoreQutzService.scoreQutzOption(qutzId);
	 }

    /**
     * 文件上传
     * @param bean
     * @return
     */
    @RequestMapping(params="fileUpload")
    @ResponseBody
    public Json fileUpload(@RequestParam MultipartFile  imgfile,HttpServletRequest request)throws IOException{
    	Json j=new Json();
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat dff = new SimpleDateFormat("yyyyMMddHHHmmssS");
		String ymd = df.format(new Date());
		String ymdhms = dff.format(new Date());
    	String imgPath = ConfigUtils.getValue("imgPath");
    	
		//上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
			if(imgfile.isEmpty()){
				j.setSuccess(false);
				j.setMsg("请选择文件后上传");
			}else{
				originalFilename = imgfile.getOriginalFilename();
				Pattern reg=Pattern.compile("[.]jpg|png|jpeg|gif$");
				Matcher matcher=reg.matcher(originalFilename);
				if(!matcher.find()) {
					j.setMsg("文件类型不允许！");
					j.setSuccess(false);
					return j;
				}
				System.out.println("文件原名: " + originalFilename);
				System.out.println("文件名称: " + imgfile.getName());
				System.out.println("文件长度: " + imgfile.getSize());
				System.out.println("文件类型: " + imgfile.getContentType());
				System.out.println("========================================");
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
    /**
     * 竞猜活动保存
     * @param scoreQutz
     * @param options
     * @return
     */
    @RequestMapping(params="saveQutzActivity")
    @ResponseBody
    public Json saveQutzActivity(HttpServletRequest request,ScoreQutzVO scoreQutz,String options){
    	Json j=new Json();
    	try{
    		scoreQutzService.saveQutzActivity(scoreQutz, options);
    		saveLog(request,"/scoreQutzController.do?saveQutzActivity", CommonConstants.LOG_FOR_SCORE_QUTZ, "创建竞猜活动："+scoreQutz.getQutzName());
    		j.setSuccess(true);
    	}catch(BusiException be){
        	logger.error("竞猜活动创建失败", be);
        	j.setMsg(be.getMessage());
        	j.setSuccess(false);
        }catch(Exception e){
    		e.printStackTrace();
    		j.setMsg("竞猜活动创建失败");
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 竞猜活动状态修改
     * @param qutzId
     * @param status
     * @return
     */
    @RequestMapping(params="updateActivityStatus")
    @ResponseBody
    public Json updateActivityStatus(HttpServletRequest request,String qutzId,String qutzName,Integer status,Integer bonusStatus,String operate){
    	Json j=new Json();
    	try{
    		boolean flag=scoreQutzService.updateQutzActivityStatus(qutzId, status,bonusStatus);
    		saveLog(request,"/scoreQutzController.do?updateActivityStatus", CommonConstants.LOG_FOR_SCORE_QUTZ, "("+qutzName+")竞猜活动"+operate);
    		j.setSuccess(flag);
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 公布答案
     * @param request
     * @param qutzId
     * @return
     */
    @RequestMapping(params="toScoreQutzAnswer")
    public String toScoreQutzAnswer(HttpServletRequest request,String qutzId,String qutzIssue){
    	ScoreQutzVO scoreQutz=scoreQutzService.qutzActivityInfo(qutzId,qutzIssue);
    	request.setAttribute("scoreQutz", scoreQutz);
    	return "/score/scoreQutzAnswer";
    }
    /**
     * 竞猜答案公布
     * @param request
     * @param scoreQutz
     * @return
     */
    @RequestMapping(params="saveQutzAnswer")
    @ResponseBody
    public Json saveQutzAnswer(HttpServletRequest request,ScoreQutzVO scoreQutz){
    	Json j=new Json();
    	try{
    		boolean flag=scoreQutzService.saveQutzAnswer(scoreQutz);
    		if(flag){
				flag=scoreQutzInterface.openAnswer(scoreQutz.getQutzId());
				saveLog(request,"/scoreQutzController.do?saveQutzAnswer", CommonConstants.LOG_FOR_SCORE_QUTZ, "("+scoreQutz.getQutzName()+")竞猜活动公布竞猜答案:"+scoreQutz.getQutzAnswerNote());
				j.setSuccess(flag);
    			
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 执行派奖
     * @param qutzId
     * @return
     */
    @RequestMapping(params="returnBonus")
    @ResponseBody
    public Json returnBonus(HttpServletRequest request,String qutzId,String qutzName){
    	Json j=new Json();
    	try{
			boolean flag=scoreQutzInterface.returnBonus(qutzId);
			saveLog(request,"/scoreQutzController.do?returnBonus", CommonConstants.LOG_FOR_SCORE_QUTZ, "("+qutzName+")竞猜活动执行派奖");
			j.setSuccess(flag);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 竞猜活动删除
     * @param qutzId
     * @return
     */
    @RequestMapping(params="delQutzActivity")
    @ResponseBody
    public Json delQutzActivity(HttpServletRequest request,String qutzId,String qutzName){
    	Json j=new Json();
    	try{
			boolean flag=scoreQutzService.delQutzActivity(qutzId);
			saveLog(request,"/scoreQutzController.do?delQutzActivity", CommonConstants.LOG_FOR_SCORE_QUTZ, "("+qutzName+")竞猜活动删除");
			j.setSuccess(flag);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 竞猜活动隐藏
     * @param request
     * @param qutzId
     * @param qutzIssue
     * @param status
     * @return
     */
    @RequestMapping(params="qutzActivityHide")
    @ResponseBody
    public Json qutzActivityHide(HttpServletRequest request,String qutzId,String qutzIssue,Integer status){
    	Json j=new Json();
    	try{
			boolean flag=scoreQutzService.qutzActivityHide(qutzId, status);
			if(flag){
				String msg="("+qutzIssue+")期竞猜活动";
				if(status==0){
					msg+="显示";
				}
				if(status==1){
					msg+="隐藏";
				}
				saveLog(request,"/scoreQutzController.do?qutzActivityHide", CommonConstants.LOG_FOR_SCORE_QUTZ, msg);
			}
			j.setSuccess(flag);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 竞猜活动分享设置
     * @param request
     * @param qutzId
     * @param qutzIssue
     * @param status
     * @return
     */
    @RequestMapping(params="qutzActivityShare")
    @ResponseBody
    public Json qutzActivityShare(HttpServletRequest request,String qutzId,String qutzIssue,Integer status){
    	Json j=new Json();
    	try{
			boolean flag=scoreQutzService.qutzActivityShare(qutzId, status);
			if(flag){
				String msg="("+qutzIssue+")期竞猜活动";
				if(status==0){
					msg+="可分享";
				}
				if(status==1){
					msg+="不可分享";
				}
				saveLog(request,"/scoreQutzController.do?qutzActivityShare", CommonConstants.LOG_FOR_SCORE_QUTZ, msg);
			}
			j.setSuccess(flag);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 竞猜活动推荐
     * @param request
     * @param qutzId
     * @param qutzIssue
     * @return
     */
    @RequestMapping(params="activityRecommend")
    @ResponseBody
    public Json activityRecommend(HttpServletRequest request,String qutzId,String qutzIssue){
    	Json j=new Json();
    	try{
			boolean flag=scoreQutzService.activityRecommend(qutzId,1);
			if(flag){
				String msg="推荐("+qutzIssue+")期竞猜活动";
				saveLog(request,"/scoreQutzController.do?activityRecommend", CommonConstants.LOG_FOR_SCORE_QUTZ, msg);
				}
			j.setSuccess(flag);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 竞猜活动取消推荐
     * @param request
     * @param qutzId
     * @param qutzIssue
     * @return
     */
    @RequestMapping(params="activityCancelRecommend")
    @ResponseBody
    public Json activityCancelRecommend(HttpServletRequest request,String qutzId,String qutzIssue){
    	Json j=new Json();
    	try{
			boolean flag=scoreQutzService.activityRecommend(qutzId,0);
			if(flag){
				String msg="取消推荐("+qutzIssue+")期竞猜活动";
				saveLog(request,"/scoreQutzController.do?activityCancelRecommend", CommonConstants.LOG_FOR_SCORE_QUTZ, msg);
				}
			j.setSuccess(flag);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 竞猜活动详情
     * @param qutzId
     * @return
     */
    @RequestMapping(params="qutzActivityDetail")
    public String qutzActivityDetail(HttpServletRequest request,String qutzId,String qutzIssue){
    	ScoreQutzVO scoreQutz=scoreQutzService.qutzActivityInfo(qutzId,qutzIssue);
    	request.setAttribute("scoreQutz", scoreQutz);
    	return "/score/qutzActivityDetail";
    }
    /**
     * 竞猜活动编辑
     * @param request
     * @param qutzId
     * @return
     */
    @RequestMapping(params="toQutzActivityEdit")
    public String toQutzActivityEdit(HttpServletRequest request,String qutzId,String qutzIssue){
    	ScoreQutzVO scoreQutz=scoreQutzService.qutzActivityInfo(qutzId,qutzIssue);
    	request.setAttribute("scoreQutz", scoreQutz);
    	String imgUrl=scoreQutz.getImgUrl();
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
    	List<Map> typeList=scoreQutzService.scoreQutzType();
		request.setAttribute("qutzType", typeList);
		
		List<EasyuiTreeNode> channel = memberService.channeltree();
		request.setAttribute("channel", channel);
		return "/score/qutzActivityEdit";
    	/*Integer status=scoreQutz.getStatus();
    	if(status==0){
    		List<Map> typeList=scoreQutzService.scoreQutzType();
    		request.setAttribute("qutzType", typeList);
    		
    		List<EasyuiTreeNode> channel = memberService.channeltree();
    		request.setAttribute("channel", channel);
    		return "/score/qutzActivityEdit";
    		
    	}
    	else{
    		 return "/score/qutzActivityTimeEdit";
    	}*/
    }
    /**
     * 积分竞猜结束时间保存
     * @param qutzId
     * @param endTime
     * @return
     */
    @RequestMapping(params="updateActivityTime")
    @ResponseBody
    public Json updateActivityTime(String qutzId,String endTime){
    	Json j=new Json();
    	try{
    		scoreQutzService.updateActivityTime(qutzId, endTime);
    		j.setSuccess(true);
    	}catch(Exception e){
    		j.setSuccess(false);
    		e.printStackTrace();
    	}
    	return j;
    }
    /**
     * 竞猜答案删除
     * @param optionId
     * @return
     */
    @RequestMapping(params="delScoreQutzAnswer")
    @ResponseBody
    public Json delScoreQutzAnswer(HttpServletRequest request,String qutzName,String optionId,String optionNote){
    	Json j=new Json();
    	try{
			boolean flag=scoreQutzService.delScoreQutzAnswer(optionId);
			saveLog(request,"/scoreQutzController.do?delScoreQutzAnswer", CommonConstants.LOG_FOR_SCORE_QUTZ, "("+qutzName+")竞猜活动删除("+optionNote+")竞猜答案选项");
			j.setSuccess(flag);
    			
    	}catch(Exception e){
    		e.printStackTrace();
    		j.setSuccess(false);
    	}
    	return j;
    }
    /**
     * 积分竞猜活动日志界面
     * @return
     */
    @RequestMapping(params="toScoreQutzLog")
    public String toScoreQutzLog(HttpServletRequest request,String qutzUserId){
    	List<Map> typeList=scoreQutzService.scoreQutzType();
    	request.setAttribute("qutzType", typeList);
    	 request.setAttribute("qutzUserId", CommonUtil.formatStr(qutzUserId, ""));
    	return "/score/scoreQutzLog";
    }
    /**
     * 积分竞猜活动日志列表
     * @param dg
     * @param vo
     * @return
     */
    @RequestMapping(params="scoreQutzLog")
    @ResponseBody
    public EasyuiDataGridJson scoreQutzLog(EasyuiDataGrid dg, ScoreQutzUserVO vo) {
        
        return scoreQutzService.scoreQutzLog(dg, vo);
    }
    /**
     * 跳转到积分竞猜中奖用户
     * @param request
     * @param qutzId
     * @return
     */
    @RequestMapping(params="toQutzBonusUser")
    public String toQutzBonusUser(HttpServletRequest request,String qutzId){
    	request.setAttribute("qutzId", qutzId);
    	return "/score/qutzBonusUser";
    	
    }
    /**
	  * 积分竞猜中奖用户
	  * @param qutzId
	  * @return
	  */
    @RequestMapping(params="qutzBonusUser")
    @ResponseBody
	 public EasyuiDataGridJson qutzBonusUser(EasyuiDataGrid dg,String qutzId){
		 return scoreQutzService.qutzBonusUser(dg,qutzId);
	 }
}
