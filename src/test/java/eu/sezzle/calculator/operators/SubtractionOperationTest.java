package eu.sezzle.calculator.operators;

import eu.sezzle.calculator.constants.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionOperationTest {
  private SubtractionOperation subtractionOperation;

  @BeforeEach
  void setUp() {
    subtractionOperation = new SubtractionOperation();
  }

  @Test
  void shouldAddNumbers() {
    Double result = subtractionOperation.calculate(1, 2);
    assertEquals(-1, result);
  }

  @Test
  void shouldHaveCorrectSignature() {
    assertEquals(Operator.SUBTRACTION
        .getSignature(), subtractionOperation.getSignature());
  }

}