package eu.sezzle.calculator.operators;

import eu.sezzle.calculator.constants.Operator;
import eu.sezzle.calculator.errorcodes.InternalErrorCodes;
import eu.sezzle.calculator.exceptions.ValidationException;

public class DivisionOperation implements Operators {
  private static final Operator operator = Operator.DIVISION;

  @Override
  public String getSignature() {
    return operator.getSignature();
  }

  @Override
  public double calculate(double operand1, double operand2) throws ValidationException {

    if (operand2 == 0) {
      throw new ValidationException(InternalErrorCodes.DIVISION_BY_ZERO,
          String.format("cannot divide %s by %s", operand1, operand2));
    }
    return operand1 / operand2;
  }
}
