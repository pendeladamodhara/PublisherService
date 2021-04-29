package com.pkglobal.app.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import com.pkglobal.app.constants.PublisherConstants;
import com.pkglobal.app.model.CustomerPublisher;
import com.pkglobal.app.model.CustomerRequest;

@Service
public class CustomerPublisherConverter {


  public CustomerPublisher convert(CustomerRequest input) {
    CustomerPublisher customer = new CustomerPublisher();
    customer.setCustomerNumber(input.getCustomerNumber());
    customer.setFirstName(input.getFirstName());
    customer.setLastName(input.getLastName());
    customer.setCountry(input.getCountry());
    customer.setEmail(input.getEmail());
    customer.setCountryCode(input.getCountryCode());
    customer.setMobileNumber(input.getMobileNumber());
    customer.setCustomerStatus(input.getCustomerStatus().toString());
    customer.setBirthDate(LocalDate.parse(input.getBirthDate(),
        DateTimeFormatter.ofPattern(PublisherConstants.ddMMyyyy)));
    customer.setCustomerAddress(input.getCustomerAddress());
    return customer;

  }
}
