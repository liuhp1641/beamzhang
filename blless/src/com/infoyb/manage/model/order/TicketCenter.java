package com.cm.manage.model.order;


import java.io.Serializable;

public class TicketCenter implements Serializable {
	private static final long serialVersionUID = 3373790391181339721L;
	private Long id;
	public String postCode;
    public String postName;
    public Double balance;

    public TicketCenter() {
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
    
}
