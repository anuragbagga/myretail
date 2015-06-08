package com.myretail.exception;

public class MyRetailException extends Exception {
	
	private int errorId;

	public MyRetailException(String msg, int errorId) {
		super(msg);
		this.errorId = errorId;
	}

	public MyRetailException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
}
