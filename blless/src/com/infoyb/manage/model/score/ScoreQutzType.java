package com.cm.manage.model.score;

import java.io.Serializable;
import java.util.Date;

public class ScoreQutzType implements Serializable {
	private static final long serialVersionUID = 1942874307570993602L;
	private Integer id;
	private String qutzTypeId;// 竞猜类型ID
	private String qutzTypeName;// 竞猜类型名称
	private Integer bonusType;// 0 能滚存 1 一次性
	private String bonusUserCode;// 总帐用户
	private Date createTime;//
	private Date updateTime;//

	private String issueCode;//期次代码
	public ScoreQutzType() {

	}

	public ScoreQutzType(Integer id, String qutzTypeId, String qutzTypeName, Integer bonusType, String bonusUserCode, Date createTime, Date updateTime,String issueCode) {
		this.id = id;
		this.qutzTypeId = qutzTypeId;
		this.qutzTypeName = qutzTypeName;
		this.bonusType = bonusType;
		this.bonusUserCode = bonusUserCode;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.issueCode=issueCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQutzTypeId() {
		return qutzTypeId;
	}

	public void setQutzTypeId(String qutzTypeId) {
		this.qutzTypeId = qutzTypeId;
	}

	public String getQutzTypeName() {
		return qutzTypeName;
	}

	public void setQutzTypeName(String qutzTypeName) {
		this.qutzTypeName = qutzTypeName;
	}

	public Integer getBonusType() {
		return bonusType;
	}

	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}

	public String getBonusUserCode() {
		return bonusUserCode;
	}

	public void setBonusUserCode(String bonusUserCode) {
		this.bonusUserCode = bonusUserCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}
	
}
