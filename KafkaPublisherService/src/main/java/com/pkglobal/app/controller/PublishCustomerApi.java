package com.pkglobal.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import com.pkglobal.app.model.CustomerRequest;
import com.pkglobal.app.model.CustomerResponse;
import com.pkglobal.app.model.ErrorResponse;

/**
 * 
 * @author Damodara Pendala
 *
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2021-04-26T14:41:51.027Z")
@Validated
@Api(value = "publishCustomer", description = "the publishCustomer API")
@RequestMapping(value = "/v1")
public interface PublishCustomerApi {

  /**
   * API to push customer information to kafka server
   * 
   * @param authorization
   * @param transactionId
   * @param activityId
   * @param body
   * @return
   */
  @ApiOperation(value = "Publishes customer information to Kafka Server",
      nickname = "addCustomerInfo", notes = "", response = CustomerResponse.class,
      tags = {"Customer Publisher",})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success Message", response = CustomerResponse.class),
      @ApiResponse(code = 400, message = "Invalid Customer Details supplied",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "Customer Publisher Not Available",
          response = ErrorResponse.class),
      @ApiResponse(code = 500, message = "Something Went Wrong Please try again",
          response = CustomerResponse.class)})
  @PostMapping(value = "/publish-customer", produces = {"application/json"},
      consumes = {"application/json"})
  ResponseEntity<CustomerResponse> publishCustomerDetails(
      @ApiParam(value = "Authorization token", required = true) @RequestHeader(
          value = "Authorization", required = true) String authorization, @ApiParam(
          value = "Transaction Id for customer info", required = true) @RequestHeader(
          value = "Transaction-Id", required = true) String transactionId, @ApiParam(
          value = "Activity Id ", required = true) @RequestHeader(value = "Activity-Id",
          required = true) String activityId, @ApiParam(value = "Updated user object",
          required = true) @Valid @RequestBody CustomerRequest body);


}
