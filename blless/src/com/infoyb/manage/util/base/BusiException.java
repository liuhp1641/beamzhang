package com.cm.manage.util.base;

public class BusiException extends Exception {

	private static final long serialVersionUID = -8259214378753210086L;
	
	private String errcode;
	private String errmsg;
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public BusiException(String errcode, String message) {
        super(message);
        this.errcode = errcode;
    }

    public BusiException(String errcode, String message, Throwable cause) {
        super(message, cause);
        this.errcode = errcode;
    }
	

}
