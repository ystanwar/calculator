package eu.sezzle.calculator.calculator.supported.operations;

import eu.sezzle.calculator.operators.Operators;

import java.util.Map;
import java.util.Optional;

// This class is contract for operations supported by calculator like addition, subtraction or whatever we define
public interface SupportedOperations {

  void addOperator(Operators operator);

  void removeOperator(Operators operator);

  Optional<Operators> getOperator(String signature);

  Map<String, Operators> getAllSupportedOperators();

}
