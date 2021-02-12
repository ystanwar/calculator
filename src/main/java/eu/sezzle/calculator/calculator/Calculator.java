package eu.sezzle.calculator.calculator;

import eu.sezzle.calculator.calculator.supported.operations.SupportedOperations;
import eu.sezzle.calculator.exceptions.ValidationException;

import java.util.Optional;

public abstract class Calculator {
  public SupportedOperations supportedOperations;

  public Calculator(SupportedOperations supportedOperations) {
    this.supportedOperations = supportedOperations;
  }

  public abstract double evaluate(String[] expression) throws ValidationException;

}
