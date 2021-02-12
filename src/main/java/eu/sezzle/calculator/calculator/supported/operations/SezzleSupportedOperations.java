package eu.sezzle.calculator.calculator.supported.operations;

import eu.sezzle.calculator.operators.Operators;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//This class is an implementation of SupportedOperations for sezzle,
//basically we can have different flavours of calculator and hence different flavours of SupportedOperations
public class SezzleSupportedOperations implements SupportedOperations {

  private final Map<String, Operators> allOperators;

  public SezzleSupportedOperations() {
    allOperators = new HashMap<>();
  }

  public SezzleSupportedOperations(Map<String, Operators> allOperators) {
    this.allOperators = allOperators;
  }

  public void addOperator(Operators operator) {
    allOperators.put(operator.getSignature(), operator);
  }

  public void removeOperator(Operators operator) {
    allOperators.remove(operator.getSignature());
  }

  public Optional<Operators> getOperator(String signature) {
    if (allOperators.containsKey(signature)) {
      return Optional.of(allOperators.get(signature));
    }

    return Optional.empty();
  }

  public Map<String, Operators> getAllSupportedOperators() {
    return allOperators;
  }


}
