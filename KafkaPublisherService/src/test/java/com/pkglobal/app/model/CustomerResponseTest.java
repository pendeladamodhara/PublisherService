package com.pkglobal.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.pkglobal.app.constants.PublisherConstants;

@Tag("unit")
public class CustomerResponseTest {

	@Test
	public void testCustomerResponseObjectWithSetterAndGetters() {

		CustomerResponse response = getCustomerResponse();
		assertEquals(PublisherConstants.SUCCESS, response.getStatus());
		assertEquals(PublisherConstants.SUCCESS_MESSAGE, response.getMessage());
		assertEquals(response.toString(), getCustomerResponse().toString());
		assertEquals(response.hashCode(), getCustomerResponse().hashCode());
		assertTrue(getCustomerResponse().equals(response));

	}

	private CustomerResponse getCustomerResponse() {
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setStatus(PublisherConstants.SUCCESS);
		customerResponse.setMessage(PublisherConstants.SUCCESS_MESSAGE);
		return customerResponse;
	}

}
