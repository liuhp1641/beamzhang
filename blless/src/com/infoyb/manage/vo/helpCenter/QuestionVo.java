package com.cm.manage.vo.helpCenter;

import java.io.Serializable;


public class QuestionVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//查询条件 start
	private Long qid;
	
	private String userName;
	
	private String content;
	
	private String createDateStart;
	
	private String createDateEnd;
	
	private String lastDateStart;
	
	private String lastDateEnd;
	
	private Integer state;
	
	private Integer processingState;
	//查询条件 end
	
	//显示list属性 start 含上面部分属性
	private String userCode;
	
	private Integer pageView;
	
	private String createDate;
	
	private String lastDate;
	
	private Integer weight;
	
	private String replyNum;
	
	private Integer questionTypeId;
	
	

	public Long getQid() {
		return qid;
	}

	public void setQid(Long qid) {
		this.qid = qid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getLastDateStart() {
		return lastDateStart;
	}

	public void setLastDateStart(String lastDateStart) {
		this.lastDateStart = lastDateStart;
	}

	public String getLastDateEnd() {
		return lastDateEnd;
	}

	public void setLastDateEnd(String lastDateEnd) {
		this.lastDateEnd = lastDateEnd;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(String replyNum) {
		this.replyNum = replyNum;
	}

	public Integer getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(Integer questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public Integer getProcessingState() {
		return processingState;
	}

	public void setProcessingState(Integer processingState) {
		this.processingState = processingState;
	}

	//显示list属性 end
	
	
	


	

	
}
