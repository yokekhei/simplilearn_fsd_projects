package org.yokekhei.fsd.p4.api.dto;

public class OnlineTestError {

	String code;
	String message;
	
	public OnlineTestError(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "OnlineTestError [code=" + code + ", message=" + message + "]";
	}
	
}
