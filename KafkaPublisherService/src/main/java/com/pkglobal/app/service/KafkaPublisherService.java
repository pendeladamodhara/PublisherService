package com.pkglobal.app.service;

import com.pkglobal.app.model.CustomerPublisher;

public interface KafkaPublisherService {

  public String publishCustomerDetails(CustomerPublisher customerRequest);

}
