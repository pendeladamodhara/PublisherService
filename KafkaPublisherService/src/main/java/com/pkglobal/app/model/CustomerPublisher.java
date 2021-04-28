package com.pkglobal.app.model;

import java.time.LocalDate;


public class CustomerPublisher {

  private String customerNumber;
  private String firstName;
  private String lastName;
  private String country;
  private String countryCode;;
  private String mobileNumber;;
  private String email;
  private LocalDate birthDate;
  private String customerStatus;
  private CustomerAddress customerAddress;

  /**
   * @return the customerNumber
   */
  public String getCustomerNumber() {
    return customerNumber;
  }

  /**
   * @param customerNumber the customerNumber to set
   */
  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return the countryCode
   */
  public String getCountryCode() {
    return countryCode;
  }

  /**
   * @param countryCode the countryCode to set
   */
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  /**
   * @return the mobileNumber
   */
  public String getMobileNumber() {
    return mobileNumber;
  }

  /**
   * @param mobileNumber the mobileNumber to set
   */
  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the birthDate
   */
  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * @param birthDate the birthDate to set
   */
  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * @return the customerStatus
   */
  public String getCustomerStatus() {
    return customerStatus;
  }

  /**
   * @param customerStatus the customerStatus to set
   */
  public void setCustomerStatus(String customerStatus) {
    this.customerStatus = customerStatus;
  }

  /**
   * @return the customerAddress
   */
  public CustomerAddress getCustomerAddress() {
    return customerAddress;
  }

  /**
   * @param customerAddress the customerAddress to set
   */
  public void setCustomerAddress(CustomerAddress customerAddress) {
    this.customerAddress = customerAddress;
  }



}
