package com.pkglobel.app.converter;

import com.pkglobel.app.model.CustomerRequest;

public interface Converter {
  /**
   * Method is used to mask customer details
   * 
   * @param customer
   * @return
   */
  public CustomerRequest convert(CustomerRequest customer);
}
