package com.cm.manage.vo.score;

import java.io.Serializable;

public class ScoreQutzTypeVO implements Serializable {
	private static final long serialVersionUID = 1942874307570993602L;
	private Integer id;
	private String qutzTypeId;// 竞猜类型ID
	private String qutzTypeName;// 竞猜类型名称
	private Integer bonusType;// 0 能滚存 1 一次性
	private String bonusUserCode;// 总帐用户
	private String createTime;//
	private String updateTime;//

	private String issueCode;//期次代码
	private String bonusUserName;//
	public ScoreQutzTypeVO() {

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

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
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

	public String getBonusUserName() {
		return bonusUserName;
	}

	public void setBonusUserName(String bonusUserName) {
		this.bonusUserName = bonusUserName;
	}
}
