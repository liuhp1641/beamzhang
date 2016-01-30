package com.cm.manage.model.account;

import java.io.Serializable;
import java.util.Date;

/**
 * 作者: 邓玉明 时间：14-1-8 下午5:33 邮箱：cndym@163.com
 */
public class BankList implements Serializable {
	private static final long serialVersionUID = 1315214294834661953L;

	private Long id;
	private String bankName;// 银行名
	private String bankCode;// 银行代号
	private Integer type;// 1 充值 2 提现
	private Date createTime;
	private Date updateTime;

	public BankList() {
	}

	public BankList(Long id, String bankName, String bankCode, Integer type, Date createTime, Date updateTime) {
		this.id = id;
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.type = type;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
}
