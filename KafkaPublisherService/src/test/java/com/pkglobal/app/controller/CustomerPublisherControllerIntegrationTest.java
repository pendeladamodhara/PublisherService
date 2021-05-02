package com.pkglobal.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pkglobal.app.KafkaPublisherServiceApplication;
import com.pkglobal.app.model.CustomerAddress;
import com.pkglobal.app.model.CustomerRequest;
import com.pkglobal.app.model.CustomerRequest.CustomerStatusEnum;
import com.pkglobal.app.util.ObjectMapperUtil;

/**
 * 
 * @author Damodara Pendala
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = KafkaPublisherServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerPublisherControllerIntegrationTest {
	@LocalServerPort
	private int port;

	TestRestTemplate testRestTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	/**
	 * Test Method to invoke publish customer API when valid request passed
	 */
	@Test
	public void testPublishCustomerDetailsWhenValidCustomerReuestPassedShouldReturnSuccess() {
		headers.add("Authorization", "Bearer " + obtainAccessToken());
		headers.add("Transaction-Id", "TRAN0001");
		headers.add("Activity-Id", "ACT00001");
		headers.add("Content-Type", "application/json");
		HttpEntity<?> entity = new HttpEntity<>(ObjectMapperUtil.convertJavaObjectToJson(getCustomerRequest()),
				headers);
		ResponseEntity<String> response = testRestTemplate.exchange(formFullUriWithPort() + "/v1/publish-customer",
				HttpMethod.POST, entity, String.class);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());

	}

	/**
	 * Test Method to invoke publish customer API when invalid request passed
	 */
	@Test
	public void testPublishCustomerDetailsWhenInvalidCustomerRequestPassedShouldReturnBadRequest() {
		headers.add("Authorization", "Bearer " + obtainAccessToken());
		headers.add("Transaction-Id", "TRAN0001");
		headers.add("Activity-Id", "ACT00001");
		headers.add("Content-Type", "application/json");
		CustomerRequest customer = getCustomerRequest();
		customer.setMobileNumber("949264315551");
		HttpEntity<?> entity = new HttpEntity<>(ObjectMapperUtil.convertJavaObjectToJson(customer), headers);
		ResponseEntity<String> response = testRestTemplate.exchange(formFullUriWithPort() + "/v1/publish-customer",
				HttpMethod.POST, entity, String.class);
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());

	}

	public String obtainAccessToken() {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("username", "bill");
		params.add("password", "abc123");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(formFullUriWithPort() + "/oauth/token")
				.queryParam("grant_type", "password").queryParam("username", "bill").queryParam("password", "abc123");
		String credentials = "Damodara:secret";
		String base64Creds = new String(Base64.encodeBase64(credentials.getBytes()));
		headers.add("Authorization", "Basic " + base64Creds);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		HttpEntity<String> response = testRestTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity,
				String.class);
		return getTokenFromResponse(response.getBody(), "access_token");
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

	private CustomerAddress getCustomerAddress() {
		CustomerAddress address = new CustomerAddress();
		address.setAddressLine1("JPNAGAR");
		address.setAddressLine2("BANGALORE");
		address.setStreet("MARATHAHALLI");
		address.setPostalCode("56003");
		return address;
	}

	private String formFullUriWithPort() {
		return "http://localhost:" + port;
	}

	private String getTokenFromResponse(String input, String key) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (String) mapper.readValue(input.getBytes(), HashMap.class).get(key);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException ex) {
		}
		return "";

	}
}
