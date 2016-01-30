package com.cm.manage.vo.order;


import java.io.Serializable;

public class TicketCenterVO implements Serializable {
	private static final long serialVersionUID = 3373790391181339721L;
	private Long id;
	public String postCode;
    public String postName;
    public Double balance;
    public Double maxBalance;

    public TicketCenterVO() {
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMaxBalance() {
		return maxBalance;
	}

	public void setMaxBalance(Double maxBalance) {
		this.maxBalance = maxBalance;
	}
}
