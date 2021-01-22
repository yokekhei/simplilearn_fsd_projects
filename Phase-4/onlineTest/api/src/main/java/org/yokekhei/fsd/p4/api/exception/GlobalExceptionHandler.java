package org.yokekhei.fsd.p4.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yokekhei.fsd.p4.api.Common;
import org.yokekhei.fsd.p4.api.dto.OnlineTestError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(OnlineTestServiceException.class)
	public ResponseEntity<OnlineTestError> handleException(OnlineTestServiceException e) {
		String code = Common.DB_ERROR_CODE;
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		if (e.getCode() != null && !e.getCode().isEmpty()) {
			code = e.getCode();
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status)
				.body(new OnlineTestError(code, e.getMessage()));
	}
	
}
