package com.pkglobal.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.pkglobal.app.KafkaPublisherServiceApplication;
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

@WebAppConfiguration
@SpringBootTest(classes = KafkaPublisherServiceApplication.class)
@ExtendWith(SpringExtension.class)
public class CustomerPublisherControllerTest {

	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

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

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext)
				.addFilter(springSecurityFilterChain).build();
	}

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
		httpHeaders.set("Authorization", "Bearer " + obtainAccessToken("bill", "abc123"));
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
		httpHeaders.set("Authorization", "Bearer " + obtainAccessToken("bill", "abc123"));
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
		httpHeaders.set("Authorization", "Bearer " + obtainAccessToken("bill", "abc123"));
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
		httpHeaders.set("Authorization", "Bearer " + obtainAccessToken("bill", "abc123"));
		httpHeaders.set("Activity-Id", "ACT0001");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).content(customerRequestJson)
				.headers(httpHeaders).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getResponse().getStatus());
	}

	/**
	 * Test method when invalid token passed
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPublishCustomerDetailsWhenInValidTokenPassedShouldReturnUnathorized() throws Exception {
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
		httpHeaders.set("Authorization", "Bearer ABCD");
		httpHeaders.set("Activity-Id", "ACT0001");
		httpHeaders.set("Transaction-Id", "TRAN0001");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).content(customerRequestJson)
				.headers(httpHeaders).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult response = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getResponse().getStatus());
	}

	private String obtainAccessToken(String username, String password) throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("username", username);
		params.add("password", password);
		ResultActions result = mockMvc.perform(post("/oauth/token").params(params).with(httpBasic("Damodara", "secret"))
				.accept("application/json;charset=UTF-8")).andExpect(status().isOk());
		String resultString = result.andReturn().getResponse().getContentAsString();
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
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

	private CustomerResponse getCustomerResponse() {
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setStatus(PublisherConstants.SUCCESS);
		customerResponse.setMessage(PublisherConstants.SUCCESS_MESSAGE);
		return customerResponse;
	}

}
