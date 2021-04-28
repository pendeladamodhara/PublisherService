package com.pkglobal.app.converter;

import org.springframework.stereotype.Component;
import com.pkglobal.app.constants.PublisherConstants;
import com.pkglobal.app.model.CustomerRequest;

@Component
public class CustomerMaskConverter implements Converter {

  @Override
  public CustomerRequest convert(CustomerRequest input) {

    CustomerRequest customer = new CustomerRequest();
    customer.setCustomerNumber(input.getCustomerNumber().replaceAll(
        PublisherConstants.CUSTOMER_NUMBER_MASK, PublisherConstants.ASTERISK));
    customer.setFirstName(input.getFirstName());
    customer.setLastName(input.getLastName());
    customer.setCountry(input.getCountry());
    customer.setCountryCode(input.getCountryCode());
    customer.setEmail(input.getEmail().replaceAll(PublisherConstants.FIRST_FOUR_CHARACTERS_MASK,
        PublisherConstants.ASTERISKS));
    customer.setMobileNumber(input.getMobileNumber());
    customer.setCustomerStatus(input.getCustomerStatus());
    customer.setBirthDate(input.getBirthDate().replaceAll(
        PublisherConstants.FIRST_FOUR_CHARACTERS_MASK, PublisherConstants.ASTERISKS));
    customer.setCustomerAddress(input.getCustomerAddress());

    return customer;
  }

}
