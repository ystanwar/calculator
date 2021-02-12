package eu.sezzle.calculator.operators;

import eu.sezzle.calculator.constants.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationOperationTest {

  private MultiplicationOperation multiplicationOperation;

  @BeforeEach
  void setUp() {
    multiplicationOperation = new MultiplicationOperation();
  }

  @Test
  void shouldAddNumbers() {
    Double result = multiplicationOperation.calculate(2, 3);
    assertEquals(6, result);
  }

  @Test
  void shouldHaveCorrectSignature() {
    assertEquals(Operator.MULTIPLICATION.getSignature(), multiplicationOperation.getSignature());
  }


}