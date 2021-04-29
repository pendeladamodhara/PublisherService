package com.pkglobal.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pkglobal.app.constants.PublisherConstants;
import com.pkglobal.app.converter.CustomerMaskConverter;
import com.pkglobal.app.converter.CustomerPublisherConverter;
import com.pkglobal.app.converter.CustomerResponseConnveter;
import com.pkglobal.app.model.CustomerAddress;
import com.pkglobal.app.model.CustomerPublisher;
import com.pkglobal.app.model.CustomerRequest;
import com.pkglobal.app.model.CustomerRequest.CustomerStatusEnum;
import com.pkglobal.app.model.CustomerResponse;
import com.pkglobal.app.service.KafkaPublisherService;
import com.pkglobal.app.util.ObjectMapperUtil;

@WebMvcTest(value = CustomerPublisherController.class)
@ExtendWith(SpringExtension.class)
public class CustomerPublisherControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@InjectMocks
	private CustomerPublisherController customerPublisherController;
	@MockBean
	private KafkaPublisherService customerPublisherService;
	@MockBean
	private CustomerMaskConverter customerMaskConverter;
	@MockBean
	private CustomerPublisherConverter customerPublisherConverter;
	@MockBean
	private CustomerResponseConnveter customerResponseConveter;

	/**
	 * Test Method when valid request passed
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPublishCustomerDetailsWhenValidRequestPassedShouldReturn200() throws Exception {

		String customerRequest = ObjectMapperUtil.convertJavaObjectToJson(getCustomerRequest());
		String uri = "/v1/publish-customer";
		when(customerMaskConverter.convert(getCustomerRequest())).thenReturn(getCustomerRequest());
		when(customerPublisherConverter.convert(getCustomerRequest())).thenReturn(getCustomerPublisher());
		when(customerPublisherService.publishCustomerDetails(getCustomerPublisher()))
				.thenReturn(PublisherConstants.SUCCESS_MESSAGE);
		when(customerResponseConveter.convertResponse(PublisherConstants.SUCCESS_MESSAGE))
				.thenReturn(getCustomerResponse());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Basic ADBCG=D");
		httpHeaders.set("Activity-Id", "ACT0001");
		httpHeaders.set("Transaction-Id", "TRAN0001");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).content(customerRequest).headers(httpHeaders)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), response.getResponse().getStatus());

	}

	/**
	 * Test method when request body passed as null
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPublishCustomerDetailsWhenCustomerRequestObjectAsNullShouldReturnBadRequest() throws Exception {
		CustomerRequest customerRequest = null;
		String customerRequestJson = ObjectMapperUtil.convertJavaObjectToJson(customerRequest);
		String uri = "/v1/publish-customer";
		when(customerMaskConverter.convert(customerRequest)).thenReturn(customerRequest);
		when(customerPublisherConverter.convert(customerRequest)).thenReturn(getCustomerPublisher());
		when(customerPublisherService.publishCustomerDetails(getCustomerPublisher()))
				.thenReturn(PublisherConstants.SUCCESS_MESSAGE);
		when(customerResponseConveter.convertResponse(PublisherConstants.SUCCESS_MESSAGE))
				.thenReturn(getCustomerResponse());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Basic ADBCG=D");
		httpHeaders.set("Activity-Id", "ACT0001");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).content(customerRequestJson)
				.headers(httpHeaders).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());

	}

	/**
	 * Test method when input validation fail
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPublishCustomerDetailsWhenInValidRequestPassedShouldReturnBadRequest() throws Exception {
		CustomerRequest customerRequest = getCustomerRequest();
		customerRequest.setMobileNumber("9492643115151");
		String customerRequestJson = ObjectMapperUtil.convertJavaObjectToJson(customerRequest);
		String uri = "/v1/publish-customer";
		when(customerMaskConverter.convert(customerRequest)).thenReturn(customerRequest);
		when(customerPublisherConverter.convert(customerRequest)).thenReturn(getCustomerPublisher());
		when(customerPublisherService.publishCustomerDetails(getCustomerPublisher()))
				.thenReturn(PublisherConstants.SUCCESS_MESSAGE);
		when(customerResponseConveter.convertResponse(PublisherConstants.SUCCESS_MESSAGE))
				.thenReturn(getCustomerResponse());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Basic ADBCG=D");
		httpHeaders.set("Activity-Id", "ACT0001");
		httpHeaders.set("Transaction-Id", "TRAN0001");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).content(customerRequestJson)
				.headers(httpHeaders).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());
	}

	/**
	 * Test method when input header not passed
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPublishCustomerDetailsWhenRequestHeaderNotPassedShouldReturnBadRequest() throws Exception {
		CustomerRequest customerRequest = getCustomerRequest();
		String customerRequestJson = ObjectMapperUtil.convertJavaObjectToJson(customerRequest);
		String uri = "/v1/publish-customer";
		when(customerMaskConverter.convert(customerRequest)).thenReturn(customerRequest);
		when(customerPublisherConverter.convert(customerRequest)).thenReturn(getCustomerPublisher());
		when(customerPublisherService.publishCustomerDetails(getCustomerPublisher()))
				.thenReturn(PublisherConstants.SUCCESS_MESSAGE);
		when(customerResponseConveter.convertResponse(PublisherConstants.SUCCESS_MESSAGE))
				.thenReturn(getCustomerResponse());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Basic ADBCG=D");
		httpHeaders.set("Activity-Id", "ACT0001");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).content(customerRequestJson)
				.headers(httpHeaders).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());
	}

	private CustomerRequest getCustomerRequest() {
		CustomerRequest customer = new CustomerRequest();
		customer.setCustomerNumber("C000000001");
		customer.setFirstName("PENDELA DAMODARA");
		customer.setLastName("Welcom CHOWDARY");
		customer.setCountry("INDIA");
		customer.setEmail("pendeladamu12@gmail.com");
		customer.setCountryCode("IN");
		customer.setMobileNumber("9492643115");
		customer.setCustomerStatus(CustomerStatusEnum.OPEN);
		customer.setBirthDate("12061994");
		customer.setCustomerAddress(getCustomerAddress());
		return customer;

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

	private CustomerResponse getCustomerResponse() {
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setStatus(PublisherConstants.SUCCESS);
		customerResponse.setMessage(PublisherConstants.SUCCESS_MESSAGE);
		return customerResponse;
	}

}
