package eu.sezzle.calculator.exceptions;


import eu.sezzle.calculator.errorcodes.InternalErrorCodes;

//Base class for all customised exceptions to extend
public class ServiceException extends Exception {
  private final InternalErrorCodes errorCode;
  private final String errorMessage;

  public ServiceException(InternalErrorCodes errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;

  }


  public ServiceException(InternalErrorCodes errorCode, String errorMessage, Exception causedByException) {
    super(errorMessage, causedByException);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;

  }

  public InternalErrorCodes getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }


}
