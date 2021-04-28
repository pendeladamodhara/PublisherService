package com.pkglobal.app.model;

import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pkglobal.app.constants.PublisherConstants;

/**
 * CustomerRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2021-04-26T14:41:51.027Z")
public class CustomerRequest {
  @JsonProperty("customerNumber")
  @Size(min = 1, max = 10, message = PublisherConstants.CUSTOMER_NUMBER_ERROR_MSG)
  private String customerNumber = null;

  @JsonProperty("firstName")
  @Size(min = 10, max = 50, message = PublisherConstants.FIRST_NAME_ERROR_MSG)
  private String firstName = null;

  @JsonProperty("lastName")
  @Size(min = 10, max = 50, message = PublisherConstants.LAST_NAME_ERROR_MSG)
  private String lastName = null;

  @JsonProperty("birthDate")
  private String birthDate = null;
  @Size(min = 1, max = 50, message = PublisherConstants.COUNTRY_ERROR_MSG)
  @JsonProperty("country")
  private String country = null;

  @JsonProperty("countryCode")
  @Size(min = 1, max = 2, message = PublisherConstants.COUNTRY_CODE_ERROR_MSG)
  private String countryCode = null;

  @JsonProperty("mobileNumber")
  @Size(min = 1, max = 10, message = PublisherConstants.MOBILE_NUMBER_ERROR_MSG)
  private String mobileNumber = null;

  @JsonProperty("email")
  @Size(min = 1, max = 50, message = PublisherConstants.EMAIL_LENGTH_ERROR_MSG)
  @Pattern(regexp = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
      message = PublisherConstants.EMAIL_ERROR_MSG)
  private String email = null;

  /**
   * Gets or Sets customerStatus
   */
  public enum CustomerStatusEnum {
    OPEN("Open"),

    CLOSE("Close"),

    SUSPEND("Suspend"),

    RESTOREDED("Restoreded");

    private String value;

    CustomerStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CustomerStatusEnum fromValue(String text) {
      for (CustomerStatusEnum b : CustomerStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("customerStatus")
  private CustomerStatusEnum customerStatus = null;

  @JsonProperty("customerAddress")
  private CustomerAddress customerAddress = null;

  public CustomerRequest customerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
    return this;
  }

  /**
   * Customer Number
   * 
   * @return customerNumber
   **/
  @ApiModelProperty(required = true, value = "Customer Number")
  @NotNull
  public String getCustomerNumber() {
    return customerNumber;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public CustomerRequest firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Customer First Name
   * 
   * @return firstName
   **/
  @ApiModelProperty(required = true, value = "Customer First Name")
  @NotNull
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public CustomerRequest lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Customer Last Name
   * 
   * @return lastName
   **/
  @ApiModelProperty(required = true, value = "Customer Last Name")
  @NotNull
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public CustomerRequest birthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Customer Date of Birth
   * 
   * @return birthDate
   **/
  @ApiModelProperty(required = true, value = "Customer Date of Birth")
  @NotNull
  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public CustomerRequest country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Customer Country Name
   * 
   * @return country
   **/
  @ApiModelProperty(required = true, value = "Customer Country Name")
  @NotNull
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public CustomerRequest countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Customer Country Code
   * 
   * @return countryCode
   **/
  @ApiModelProperty(required = true, value = "Customer Country Code")
  @NotNull
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public CustomerRequest mobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  /**
   * Customer Mobile Number
   * 
   * @return mobileNumber
   **/
  @ApiModelProperty(required = true, value = "Customer Mobile Number")
  @NotNull
  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public CustomerRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Customer Email
   * 
   * @return email
   **/
  @ApiModelProperty(required = true, value = "Customer Email ")
  @NotNull
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CustomerRequest customerStatus(CustomerStatusEnum customerStatus) {
    this.customerStatus = customerStatus;
    return this;
  }

  /**
   * Get customerStatus
   * 
   * @return customerStatus
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public CustomerStatusEnum getCustomerStatus() {
    return customerStatus;
  }

  public void setCustomerStatus(CustomerStatusEnum customerStatus) {
    this.customerStatus = customerStatus;
  }

  public CustomerRequest customerAddress(CustomerAddress customerAddress) {
    this.customerAddress = customerAddress;
    return this;
  }

  /**
   * Get customerAddress
   * 
   * @return customerAddress
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull
  @Valid
  public CustomerAddress getCustomerAddress() {
    return customerAddress;
  }

  public void setCustomerAddress(CustomerAddress customerAddress) {
    this.customerAddress = customerAddress;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerRequest customerRequest = (CustomerRequest) o;
    return Objects.equals(this.customerNumber, customerRequest.customerNumber)
        && Objects.equals(this.firstName, customerRequest.firstName)
        && Objects.equals(this.lastName, customerRequest.lastName)
        && Objects.equals(this.birthDate, customerRequest.birthDate)
        && Objects.equals(this.country, customerRequest.country)
        && Objects.equals(this.countryCode, customerRequest.countryCode)
        && Objects.equals(this.mobileNumber, customerRequest.mobileNumber)
        && Objects.equals(this.email, customerRequest.email)
        && Objects.equals(this.customerStatus, customerRequest.customerStatus)
        && Objects.equals(this.customerAddress, customerRequest.customerAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerNumber, firstName, lastName, birthDate, country, countryCode,
        mobileNumber, email, customerStatus, customerAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerRequest {\n");

    sb.append("    customerNumber: ").append(toIndentedString(customerNumber)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("    mobileNumber: ").append(toIndentedString(mobileNumber)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    customerStatus: ").append(toIndentedString(customerStatus)).append("\n");
    sb.append("    customerAddress: ").append(toIndentedString(customerAddress)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
