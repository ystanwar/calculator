package eu.sezzle.calculator.exceptions;


import eu.sezzle.calculator.errorcodes.InternalErrorCodes;

//Common validation exceptions we can throw
public class ValidationException extends ServiceException {

  public ValidationException(InternalErrorCodes errorCode, String errorMessage) {
    super(errorCode, errorMessage);
  }

  public ValidationException(InternalErrorCodes errorCode, String errorMessage, Exception causedByException) {
    super(errorCode, errorMessage, causedByException);
  }

}
