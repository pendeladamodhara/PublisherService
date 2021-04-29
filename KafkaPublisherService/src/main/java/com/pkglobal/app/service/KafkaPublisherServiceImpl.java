package com.pkglobal.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.pkglobal.app.model.CustomerPublisher;
import com.pkglobal.app.util.ObjectMapperUtil;

@Service
public class KafkaPublisherServiceImpl implements KafkaPublisherService {


  public static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublisherServiceImpl.class);
  @Value("${topic}")
  private String topic;

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public String publishCustomerDetails(CustomerPublisher customerPublisher) {
    String responseMessage = "";
    LOGGER.info("Entered to kafka publisher {}", customerPublisher);
    try {
      kafkaTemplate.send(topic, customerPublisher);
      responseMessage = "Customer info published to kafka server";
    } catch (KafkaException ex) {
      LOGGER.error("Error occured while publishing to kafka {}",
          ObjectMapperUtil.convertJavaObjectToJson(customerPublisher));
      responseMessage = "Error occured while publishing to kafka";
    }
    LOGGER.info("Exit from  to kafka publisher {}", responseMessage);

    return responseMessage;
  }

}
