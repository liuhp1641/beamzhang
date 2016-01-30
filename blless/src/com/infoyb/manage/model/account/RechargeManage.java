package com.cm.manage.model.account;

public class RechargeManage implements java.io.Serializable {
	private static final long serialVersionUID = 1630211507005067020L;

	private Integer id;
	private String channelCode;// 充值渠道代码
	private String channelName;// 充值渠道名称
	private String label;// 标签
	private String adNote;// 广告词
	private String remark;// 备注
	private String status;// 状态 1 启用 2停用
	private String createTime;//
	private String updateTime;//

	public RechargeManage() {

	}

	public RechargeManage(Integer id, String channelCode, String channelName, String label, String adNote, String remark, String status, String createTime, String updateTime) {
		this.id = id;
		this.channelCode = channelCode;
		this.channelName = channelName;
		this.label = label;
		this.adNote = adNote;
		this.remark = remark;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAdNote() {
		return adNote;
	}

	public void setAdNote(String adNote) {
		this.adNote = adNote;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
