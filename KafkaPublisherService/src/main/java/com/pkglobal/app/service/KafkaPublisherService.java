package com.pkglobal.app.service;

import com.pkglobal.app.model.CustomerRequest;

public interface KafkaPublisherService {

  public String publishCustomerDetails(CustomerRequest customerRequest);

}
