package com.pkglobal.app.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pkglobal.app.converter.CustomerMaskConverter;
import com.pkglobal.app.converter.CustomerPublisherConverter;
import com.pkglobal.app.converter.CustomerResponseConnveter;
import com.pkglobal.app.model.CustomerPublisher;
import com.pkglobal.app.model.CustomerRequest;
import com.pkglobal.app.model.CustomerResponse;
import com.pkglobal.app.service.KafkaPublisherService;
import com.pkglobal.app.util.ObjectMapperUtil;

/**
 * 
 * @author Damodara Pendala
 *
 */
@RestController
@Validated
@RequestMapping(value = "/v1")
public class CustomerPublisherController {

  public static final Logger LOGGER = LoggerFactory.getLogger(CustomerPublisherController.class);

  @Autowired
  private KafkaPublisherService customerPublisherService;
  @Autowired
  private CustomerMaskConverter customerMaskConverter;
  @Autowired
  private CustomerPublisherConverter customerPublisherConverter;


  /**
   * API to push customer information to kafka server
   * 
   * @param authorization
   * @param transactionId
   * @param activityId
   * @param body
   * @return
   */
  @PostMapping(value = "/publish-customer", produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<CustomerResponse> publishCustomerDetails(@RequestHeader(
      value = "Authorization", required = true) String authorization, @RequestHeader(
      value = "Transaction-Id", required = true) String transactionId, @RequestHeader(
      value = "Activity-Id", required = true) String activityId,
      @Valid @RequestBody CustomerRequest customer) {
    String customerJson =
        ObjectMapperUtil.convertJavaObjectToJson(customerMaskConverter.convert(customer));
    LOGGER.info("Customer input request {}", customerJson);
    CustomerPublisher customerPublisher = customerPublisherConverter.convert(customer);
    String message = customerPublisherService.publishCustomerDetails(customerPublisher);
    CustomerResponse customerResponse = CustomerResponseConnveter.convertResponse(message);
    LOGGER.info("Customer Response{}", customerResponse);
    return new ResponseEntity<>(customerResponse, HttpStatus.OK);
  }
}
