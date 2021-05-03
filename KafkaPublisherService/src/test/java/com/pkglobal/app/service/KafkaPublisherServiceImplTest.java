package com.pkglobal.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.apache.kafka.common.KafkaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
	KafkaPublisherServiceImpl kafkaPublisherService;
	@MockBean
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Test
	public void testPublishCustomerDetailsWhenValidCustomerObjectPassedShouldReturnSuccess() {
		when(kafkaTemplate.send("customer", getCustomerPublisher())).thenReturn(null);
		String message = kafkaPublisherService.publishCustomerDetails(getCustomerPublisher());
		assertEquals(PublisherConstants.SUCCESS_MESSAGE, message);
	}

	@Test
	public void testPublishCustomerDetailsWhenKafkaServerNotAvailableShouldThrowException() {
		doThrow(KafkaException.class).when(kafkaTemplate).send(Mockito.anyString(), Mockito.any());
		Assertions.assertThrows(KafkaException.class, () -> {
			kafkaPublisherService.publishCustomerDetails(getCustomerPublisher());
		});
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
