package com.pkglobal.app.service;

import org.springframework.stereotype.Service;
import com.pkglobal.app.model.CustomerPublisher;

@Service
public class KafkaPublisherServiceImpl implements KafkaPublisherService {

  @Override
  public String publishCustomerDetails(CustomerPublisher customerPublisher) {
    return "Successfully published customer details";
  }



}
