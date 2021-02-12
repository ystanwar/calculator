package eu.sezzle.calculator.operators;

import eu.sezzle.calculator.constants.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionOperationTest {
  private AdditionOperation additionOperation;

  @BeforeEach
  void setUp() {
    additionOperation = new AdditionOperation();
  }

  @Test
  void shouldAddNumbers() {
    Double result = additionOperation.calculate(1, 2);
    assertEquals(3, result);
  }

  @Test
  void shouldHaveCorrectSignature() {
    assertEquals(Operator.ADDITION.getSignature(), additionOperation.getSignature());
  }

}