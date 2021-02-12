package eu.sezzle.calculator.calculator.supported.operations.implementation.sezzle;

import eu.sezzle.calculator.calculator.supported.operations.SezzleSupportedOperations;
import eu.sezzle.calculator.constants.Operator;
import eu.sezzle.calculator.exceptions.ValidationException;
import eu.sezzle.calculator.operators.AdditionOperation;
import eu.sezzle.calculator.operators.DivisionOperation;
import eu.sezzle.calculator.operators.Operators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SezzleSupportedOperationsTest {

  private SezzleSupportedOperations sezzleSupportedOperations;

  @BeforeEach
  public void setUp() {
    sezzleSupportedOperations = new SezzleSupportedOperations();
  }

  @Test
  void addOperator() {
    addAddtionOperator();
    assertTrue(sezzleSupportedOperations.getOperator(Operator.ADDITION.getSignature()).isPresent());
    assertEquals(1, sezzleSupportedOperations.getAllSupportedOperators().size());
  }

  @Test
  void removeOperator() {
    addAddtionOperator();

    sezzleSupportedOperations.removeOperator(new AdditionOperation());
    assertFalse(sezzleSupportedOperations.getOperator(Operator.ADDITION.getSignature()).isPresent());
    assertEquals(0, sezzleSupportedOperations.getAllSupportedOperators().size());

  }

  @Test
  void getOperator() throws ValidationException {
    addAddtionOperator();
    Optional<Operators> operator = sezzleSupportedOperations.getOperator(Operator.ADDITION.getSignature());
    assertTrue(operator.isPresent());
    assertEquals(5, operator.get().calculate(2, 3));
  }

  @Test
  void getAllSupportedOperators() {
    addAddtionOperator();
    sezzleSupportedOperations.addOperator(new DivisionOperation());
    Map<String, Operators> allOperators = sezzleSupportedOperations.getAllSupportedOperators();

    assertEquals(2, allOperators.size());
    assertTrue(allOperators.containsKey(Operator.ADDITION.getSignature()));
    assertTrue(allOperators.containsKey(Operator.DIVISION.getSignature()));
  }

  private void addAddtionOperator() {
    sezzleSupportedOperations.addOperator(new AdditionOperation());
  }
}