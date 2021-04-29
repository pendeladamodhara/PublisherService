package com.pkglobal.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import com.pkglobal.app.constants.PublisherConstants;
import com.pkglobal.app.model.CustomerAddress;
import com.pkglobal.app.model.CustomerPublisher;
import com.pkglobal.app.model.CustomerRequest.CustomerStatusEnum;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class KafkaPublisherServiceImplTest {

	@Autowired
	KafkaPublisherService kafkaPublisherService;
	@MockBean
	KafkaTemplate<String, Object> kafkaTemplate;

	@Test
	public void test() {
		when(kafkaTemplate.send("customer", getCustomerPublisher())).thenReturn(null);
		String message = kafkaPublisherService.publishCustomerDetails(getCustomerPublisher());
		assertEquals(PublisherConstants.SUCCESS_MESSAGE, message);
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
		customer.setBirthDate(LocalDate.now());
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
