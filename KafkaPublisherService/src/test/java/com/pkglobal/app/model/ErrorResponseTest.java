package com.pkglobal.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ErrorResponseTest {
	@Test
	public void testCustomerResponseObjectWithSetterAndGetters() {
		ErrorResponse errorResponse = getErrorResponse();
		assertEquals("failed", errorResponse.getStatus());
		assertEquals("Validation error", errorResponse.getMessage());
		assertEquals("MethodArgumentNotValidException", errorResponse.getErrorType());
		assertEquals(getErrorResponse().toString(), errorResponse.toString());
		assertEquals(getErrorResponse().hashCode(), errorResponse.hashCode());
		assertTrue(getErrorResponse().equals(errorResponse));

	}

	public ErrorResponse getErrorResponse() {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus("failed");
		errorResponse.setMessage("Validation error");
		errorResponse.setErrorType("MethodArgumentNotValidException");
		return errorResponse;

	}
}
