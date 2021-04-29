package com.pkglobal.app.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.pkglobal.app.model.CustomerResponse;

public class CustomerResponseConverterTest {

	private CustomerResponseConnveter customerResponseConnveter;

	@BeforeEach
	public void setUp() {
		customerResponseConnveter = new CustomerResponseConnveter();
	}

	@Test
	public void testConvertResponseWhenSuccessPublishToKafkaShouldReturnSuccess() {
		CustomerResponse customerResonse = customerResponseConnveter.convertResponse("Message pushed to kafka server");
		assertEquals("success", customerResonse.getStatus());

	}
}
