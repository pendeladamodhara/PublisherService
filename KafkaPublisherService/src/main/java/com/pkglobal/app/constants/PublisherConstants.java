package com.pkglobal.app.constants;

public class PublisherConstants {

  private PublisherConstants() {}

  public static final String ERROR = "failed";
  public static final String SUCCESS = "success";

  // Customer Request Error Messages
  public static final String CUSTOMER_NUMBER_ERROR_MSG =
      "Customer number must be less than 10 characters";
  public static final String FIRST_NAME_ERROR_MSG = "First Name length must be in between 10 to 50";
  public static final String LAST_NAME_ERROR_MSG = "Last Name length must be in between 10 to 50";
  public static final String COUNTRY_ERROR_MSG = "Country must be in between 50 characters";
  public static final String COUNTRY_CODE_ERROR_MSG = "Country code must be 2 characters";
  public static final String MOBILE_NUMBER_ERROR_MSG = "Mobile number must be 10 digit";
  public static final String EMAIL_ERROR_MSG = "Email format is incorrect";
  public static final String EMAIL_LENGTH_ERROR_MSG = "Email  must be less than 50 characters";


  // Regex for masking
  public static final String CUSTOMER_NUMBER_MASK = "\\d(?=(?:\\D*\\d){0,3}\\D*$)";

  public static final String FIRST_FOUR_CHARACTERS_MASK = "^.{1,4}";
  public static final String ASTERISK = "*";
  public static final String ASTERISKS = "****";

  public static final String ddMMyyyy = "ddMMyyyy";


}
