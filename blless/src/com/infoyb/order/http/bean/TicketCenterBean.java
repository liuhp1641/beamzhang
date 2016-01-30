package com.cm.order.http.bean;


import java.io.Serializable;

public class TicketCenterBean implements Serializable {

    public String postCode;
    public String postName;
    public Double balance;

    public TicketCenterBean() {
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
}
