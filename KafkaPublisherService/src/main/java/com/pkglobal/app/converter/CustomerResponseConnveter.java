package com.pkglobal.app.converter;

import com.pkglobal.app.constants.PublisherConstants;
import com.pkglobal.app.model.CustomerResponse;

/**
 * 
 * @author Damodara Pendala
 *
 */
public class CustomerResponseConnveter {

  private CustomerResponseConnveter() {}


  /**
   * Method to create response object
   * 
   * @param message
   * @return
   */
  public static CustomerResponse convertResponse(String message) {
    CustomerResponse customerResponse = new CustomerResponse();
    customerResponse.setStatus(PublisherConstants.SUCCESS);
    customerResponse.setMessage(message);
    return customerResponse;
  }


}
