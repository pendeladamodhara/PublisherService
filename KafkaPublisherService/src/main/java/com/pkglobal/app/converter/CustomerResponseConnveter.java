package com.pkglobal.app.converter;

import org.springframework.stereotype.Service;

import com.pkglobal.app.constants.PublisherConstants;
import com.pkglobal.app.model.CustomerResponse;

/**
 * 
 * @author Damodara Pendala
 *
 */
@Service
public class CustomerResponseConnveter {

	/**
	 * Method to create response object
	 * 
	 * @param message
	 * @return
	 */
	public CustomerResponse convertResponse(String message) {
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setStatus(PublisherConstants.SUCCESS);
		customerResponse.setMessage(message);
		return customerResponse;
	}

}
