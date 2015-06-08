package com.myretail.exception;

public class ItemNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private int errorId;
	 
	 public ItemNotFoundException(String msg, int errorId) {
	        super(msg);
	        this.errorId = errorId;
	    }
	 
	    public ItemNotFoundException(String msg, Throwable cause) {
	        super(msg, cause);
	    }

		public int getErrorId() {
			return errorId;
		}

		public void setErrorId(int errorId) {
			this.errorId = errorId;
		}
}
