package com.pkglobel.app.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.pkglobel.app.converter.CustomerMaskConverter;
import com.pkglobel.app.converter.CustomerResponseConnveter;
import com.pkglobel.app.model.CustomerRequest;
import com.pkglobel.app.model.CustomerResponse;
import com.pkglobel.app.service.IKafkaPublisherService;
import com.pkglobel.app.util.ObjectMapperUtil;

@RestController
public class CustomerPublisherController implements PublishCustomerApi {

  public static final Logger LOGGER = LoggerFactory.getLogger(CustomerPublisherController.class);

  @Autowired
  private IKafkaPublisherService customerPublisherService;
  @Autowired
  private CustomerMaskConverter customerMaskConverter;

  @Override
  public ResponseEntity<CustomerResponse> publishCustomerDetails(String authorization,
      String transactionId, String activityId, @Valid CustomerRequest customer) {
    String customerJson =
        ObjectMapperUtil.convertJavaObjectToJson(customerMaskConverter.convert(customer));
    LOGGER.info("Customer input request {}", customerJson);
    String message = customerPublisherService.publishCustomerDetails(customer);
    CustomerResponse customerResponse = CustomerResponseConnveter.convertResponse(message);
    LOGGER.info("Customer Response{}", customerResponse);
    return new ResponseEntity<>(customerResponse, HttpStatus.OK);
  }
}
