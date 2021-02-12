package eu.sezzle.calculator.operators;

import eu.sezzle.calculator.exceptions.ValidationException;

//This class defines the contract for different calculator operations
//responsibility of every operator is to do calculation
public interface Operators {
  String getSignature();

  double calculate(double operand1, double operand2) throws ValidationException;
}
