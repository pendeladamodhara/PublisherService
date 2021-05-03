package com.pkglobal.app.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.pkglobal.app.model.CustomerRequest.CustomerStatusEnum;

@Tag("unit")
public class CustomerPublisherTest {

	@Test
	public void testCustomerPublisherObjectWithSetterAndGetters() {
		CustomerPublisher customer = getCustomerPublisher();
		assertEquals("C000000001", customer.getCustomerNumber());
		assertEquals("PENDELA DAMODARA", customer.getFirstName());
		assertEquals("CHOWDARY", customer.getLastName());
		assertEquals("INDIA", customer.getCountry());
		assertEquals("pendeladamu12@gmail.com", customer.getEmail());
		assertEquals("IN", customer.getCountryCode());
		assertEquals("9492643115", customer.getMobileNumber());
		assertEquals("Open", customer.getCustomerStatus().toString());
		assertEquals(LocalDate.now().toString(), customer.getBirthDate());
		assertNotNull(customer.getCustomerAddress());

	}

	private CustomerPublisher getCustomerPublisher() {
		CustomerPublisher customer = new CustomerPublisher();
		customer.setCustomerNumber("C000000001");
		customer.setFirstName("PENDELA DAMODARA");
		customer.setLastName("CHOWDARY");
		customer.setCountry("INDIA");
		customer.setEmail("pendeladamu12@gmail.com");
		customer.setCountryCode("IN");
		customer.setMobileNumber("9492643115");
		customer.setCustomerStatus(CustomerStatusEnum.OPEN.toString());
		customer.setBirthDate(LocalDate.now().toString());
		customer.setCustomerAddress(getCustomerAddress());
		return customer;

	}

	private CustomerAddress getCustomerAddress() {
		CustomerAddress address = new CustomerAddress();
		address.setAddressLine1("JPNAGAR");
		address.setAddressLine2("BANGALORE");
		address.setStreet("MARATHAHALLI");
		address.setPostalCode("56003");
		return address;
	}
}
