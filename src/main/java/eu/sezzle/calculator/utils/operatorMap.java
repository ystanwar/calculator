package eu.sezzle.calculator.utils;

import eu.sezzle.calculator.constants.Operator;

import java.util.HashMap;
import java.util.Map;

public class operatorMap {
  public static final Map<String, Integer> getOperatorsWithPriority() {

    Map<String, Integer> allOperators = new HashMap<>();
    for (Operator operator : Operator.values()) {
      allOperators.put(operator.getSignature(), operator.getPriority());
    }

    return allOperators;
  }
}
