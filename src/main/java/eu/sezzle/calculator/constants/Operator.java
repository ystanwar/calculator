package eu.sezzle.calculator.constants;

//Operators that aan infix expression can contain and we have specified priority of each, 0 being the highest
public enum Operator {

  OPEN_BRACKET("(", 0),
  ADDITION("+", 1),
  SUBTRACTION("-", 1),
  MULTIPLICATION("*", 2),
  DIVISION("/", 2);

  private final String signature;
  private final int priority;

  Operator(String signature, int priority) {
    this.signature = signature;
    this.priority = priority;
  }

  public String getSignature() {
    return this.signature;
  }

  public int getPriority() {
    return this.priority;
  }


}
