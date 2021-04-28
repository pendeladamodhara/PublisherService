package com.pkglobel.app.service;

import com.pkglobel.app.model.CustomerRequest;

public interface IKafkaPublisherService {

  public String publishCustomerDetails(CustomerRequest customerRequest);

}
