package com.pkglobal.app.service;

import org.springframework.stereotype.Service;
import com.pkglobal.app.model.CustomerRequest;

@Service
public class KafkaPublisherServiceImpl implements KafkaPublisherService {

  @Override
  public String publishCustomerDetails(CustomerRequest customerRequest) {
    return "Successfully published customer details";
  }



}
