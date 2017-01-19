package com.huiyee.tfmodel;



public class HYException extends Exception {
    private int statusCode = -1;
    private int errorCode = -1;
    private String request;
    private String error;
    private static final long serialVersionUID = 1L;

    public HYException(String msg) {
        super(msg);
    }

    public HYException(Exception cause) {
        super(cause);
    }
    
    public HYException(String msg , int statusCode) {
    	super(msg);
    	this.statusCode = statusCode;
    }

 

    public HYException(String msg, Exception cause) {
        super(msg, cause);
    }

    public HYException(String msg, Exception cause, int statusCode) {
        super(msg, cause);
        this.statusCode = statusCode;

    }

    public int getStatusCode() {
        return this.statusCode;
    }

	public int getErrorCode() {
		return errorCode;
	}

	public String getRequest() {
		return request;
	}

	public String getError() {
		return error;
	}
    
}