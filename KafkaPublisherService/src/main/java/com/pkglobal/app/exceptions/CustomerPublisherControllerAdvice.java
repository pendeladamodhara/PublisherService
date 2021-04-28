package com.pkglobal.app.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.security.sasl.AuthenticationException;
import javax.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.pkglobal.app.constants.PublisherConstants;
import com.pkglobal.app.model.ErrorResponse;
import com.pkglobal.app.util.ObjectMapperUtil;

@ControllerAdvice
public class CustomerPublisherControllerAdvice {


  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException ex,
      WebRequest request) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    Map<String, List<String>> validationErrors = new HashMap<>();
    setExceptionTrace(fieldErrors, validationErrors);
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("Input validation error "
        + ObjectMapperUtil.convertJavaObjectToJson(validationErrors));
    errorResponse.setStatus(PublisherConstants.ERROR);
    errorResponse.setErrorType(MethodArgumentNotValidException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UnexpectedTypeException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(UnexpectedTypeException ex,
      WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setStatus(PublisherConstants.ERROR);
    errorResponse.setErrorType(UnexpectedTypeException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }



  @ExceptionHandler({MissingRequestHeaderException.class})
  public ResponseEntity<ErrorResponse> exceptionHandler(MissingRequestHeaderException ex,
      WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(ex.getHeaderName() + " Header is required");
    errorResponse.setStatus(PublisherConstants.ERROR);
    errorResponse.setErrorType(MissingRequestHeaderException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(CommonException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(InternalServerError ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("General error occurred::" + ex.getMessage());
    errorResponse.setStatus(PublisherConstants.ERROR);
    errorResponse.setErrorType(CommonException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(AuthenticationException ex,
      WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("General error occurred::" + ex.getMessage());
    errorResponse.setStatus(PublisherConstants.ERROR);
    errorResponse.setErrorType(CommonException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }


  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(NoHandlerFoundException ex,
      WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("General error occurred::" + ex.getMessage());
    errorResponse.setStatus(PublisherConstants.ERROR);
    errorResponse.setErrorType(CommonException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> exceptionHandler(AccessDeniedException ex, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage("General error occurred::" + ex.getMessage());
    errorResponse.setStatus(PublisherConstants.ERROR);
    errorResponse.setErrorType(CommonException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);

  }

  private void setExceptionTrace(List<FieldError> fieldErrors,
      Map<String, List<String>> validationErrors) {
    for (FieldError fieldError : fieldErrors) {
      if (validationErrors.containsKey(fieldError.getField())) {
        List<String> error = validationErrors.get(fieldError.getField());
        error.add(fieldError.getDefaultMessage());
        validationErrors.put(fieldError.getField(), error);
      } else {
        List<String> error = new ArrayList<>();
        error.add(fieldError.getDefaultMessage());
        validationErrors.put(fieldError.getField(), error);
      }
    }
  }
}
