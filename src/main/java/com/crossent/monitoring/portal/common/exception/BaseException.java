package com.crossent.monitoring.portal.common.exception;

import org.springframework.core.NestedRuntimeException;


abstract class BaseException extends NestedRuntimeException {

	BaseException(String message) {
		super(message);
	}


	BaseException(String message, Throwable cause) {
		super(message, cause);
	}

}
   