package com.cm.manage.vo.member;

public class MessageVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 8296531282317589593L;
	
	private String createTime;//留言时间
	
	private String content;//留言内容
	
	private String questionType;//所属版块
	
	private Integer status;//留言状态
	
	private String handler;//处理员
	
	private String handleTime;//处理时间

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

}
