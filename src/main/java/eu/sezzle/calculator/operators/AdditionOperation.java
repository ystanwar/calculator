package eu.sezzle.calculator.operators;

import eu.sezzle.calculator.constants.Operator;

public class AdditionOperation implements Operators {
  private static final Operator operator = Operator.ADDITION;

  @Override
  public String getSignature() {
    return operator.getSignature();
  }

  @Override
  public double calculate(double operand1, double operand2) {
    return operand1 + operand2;
  }

}
