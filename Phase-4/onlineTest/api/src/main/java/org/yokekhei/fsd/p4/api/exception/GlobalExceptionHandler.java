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
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new OnlineTestError(Common.DB_ERROR_CODE, e.getMessage()));
	}
	
}
