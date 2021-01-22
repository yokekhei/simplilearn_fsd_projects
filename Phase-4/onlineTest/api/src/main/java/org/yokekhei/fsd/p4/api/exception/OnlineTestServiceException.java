package org.yokekhei.fsd.p4.api.exception;

public class OnlineTestServiceException extends Exception {
	
	private static final long serialVersionUID = 3681271468693974029L;
	
	private String code;
	
	public OnlineTestServiceException() {
		super();
	}
	
	public OnlineTestServiceException(String message) {
		super(message);
	}
	
	public OnlineTestServiceException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
