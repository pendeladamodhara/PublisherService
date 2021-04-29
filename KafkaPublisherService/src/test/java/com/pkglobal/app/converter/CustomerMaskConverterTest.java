package com.pkglobal.app.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.pkglobal.app.model.CustomerAddress;
import com.pkglobal.app.model.CustomerRequest;
import com.pkglobal.app.model.CustomerRequest.CustomerStatusEnum;

@Tag("unit")
public class CustomerMaskConverterTest {


  private CustomerMaskConverter customerMaskConverter;

  @BeforeEach
  public void setUp() {
    customerMaskConverter = new CustomerMaskConverter();
  }

  @Test
  public void testConvertWhenPassingValidCustomerObjectShouldApplyMaskingToCustomerObject() {
    CustomerRequest result = customerMaskConverter.convert(getCustomerRequest());
  }

  private CustomerRequest getCustomerRequest() {
    CustomerRequest customer = new CustomerRequest();
    customer.setCustomerNumber("C000000001");
    customer.setFirstName("PENDELA DAMODARA");
    customer.setLastName("CHOWDARY");
    customer.setCountry("INDIA");
    customer.setEmail("pendeladamu12@gmail.com");
    customer.setCountryCode("IN");
    customer.setMobileNumber("9492643115");
    customer.setCustomerStatus(CustomerStatusEnum.OPEN);
    customer.setBirthDate("12061994");
    customer.setCustomerAddress(getCustomerAddress());
    return customer;


  }


  public CustomerAddress getCustomerAddress() {
    CustomerAddress address = new CustomerAddress();
    address.setAddressLine1("JPNAGAR");
    address.setAddressLine2("BANGALORE");
    address.setStreet("MARATHAHALLI");
    address.setPostalCode("560037");
    return address;
  }
}
