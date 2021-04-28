package com.pkglobel.app.converter;

import org.springframework.stereotype.Component;
import com.pkglobel.app.constants.PublisherConstants;
import com.pkglobel.app.model.CustomerRequest;

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
    customer.setEmail(input.getEmail().replaceFirst(input.getEmail().substring(0, 4), "****"));
    customer.setCustomerStatus(input.getCustomerStatus());
    customer.setBirthDate(input.getBirthDate().replaceFirst(input.getBirthDate().substring(0, 4),
        "****"));
    customer.setCustomerAddress(input.getCustomerAddress());

    return customer;
  }

}
