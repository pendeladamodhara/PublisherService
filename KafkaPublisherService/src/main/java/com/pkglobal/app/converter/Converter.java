package com.pkglobal.app.converter;

import com.pkglobal.app.model.CustomerRequest;

public interface Converter {
  /**
   * Method is used to mask customer details
   * 
   * @param customer
   * @return
   */
  public CustomerRequest convert(CustomerRequest customer);
}
