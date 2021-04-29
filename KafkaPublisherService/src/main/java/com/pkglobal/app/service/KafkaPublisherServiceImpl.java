package com.pkglobal.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.pkglobal.app.constants.PublisherConstants;
import com.pkglobal.app.exceptions.CommonException;
import com.pkglobal.app.model.CustomerPublisher;

@Service
public class KafkaPublisherServiceImpl implements KafkaPublisherService {

	public static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublisherServiceImpl.class);

	@Value("${topic}")
	private String topic;

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@Override
	public String publishCustomerDetails(CustomerPublisher customerPublisher) {
		LOGGER.info("Entered to kafka publisher {}", customerPublisher);
		String responseMessage = publishMassageToKafka(customerPublisher);
		LOGGER.info("Exit from  to kafka publisher {}", responseMessage);
		return responseMessage;
	}

	private String publishMassageToKafka(CustomerPublisher customerPublisher) {
		try {
			kafkaTemplate.send(topic, customerPublisher);
			return PublisherConstants.SUCCESS_MESSAGE;
		} catch (KafkaException ex) {
			LOGGER.error("Error occurred while pushing message to kafka {}", ex);
			throw new CommonException(PublisherConstants.ERROR_MESSAGE);
		}
	}

}
