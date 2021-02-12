package eu.sezzle.calculator.errorcodes;

public enum InternalErrorCodes {

  // ADD/REMOVE Generic error codes here - > This file is single source for all error codes

  EMPTY_EXPRESSION("400", "expression is empty"),
  DIVISION_BY_ZERO("401", "cannot divide by zero"),
  OPERATOR_NOT_SUPPORTED("404", "operator not allowed"),
  INVALID_EXPRESSION("400", "invalid input passed"),
  SERVER_ERROR("500", "internal server error");


  private final String code;
  private final String description;

  public String getDescription() {
    return description;
  }

  public String getCode() {
    return code;
  }

  InternalErrorCodes(String code, String description) {
    this.code = code;
    this.description = description;
  }

};
