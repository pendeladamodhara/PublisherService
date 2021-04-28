package com.pkglobel.app.service;

import org.springframework.stereotype.Service;
import com.pkglobel.app.model.CustomerRequest;

@Service
public class KafkaPublisherServiceImpl implements IKafkaPublisherService {

  @Override
  public String publishCustomerDetails(CustomerRequest customerRequest) {
    return "Successfully published customer details";
  }



}
