package org.yokekhei.fsd.capstone.api.exception;

public class FoodBoxServiceException extends Exception {

	private static final long serialVersionUID = -4329651578816857112L;

	private String code;

	public FoodBoxServiceException() {
		super();
	}

	public FoodBoxServiceException(String message) {
		super(message);
	}

	public FoodBoxServiceException(String code, String message) {
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
