package eu.sezzle.calculator.operators;

import eu.sezzle.calculator.constants.Operator;
import eu.sezzle.calculator.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionOperationTest {

  private DivisionOperation divisionOperation;

  @BeforeEach
  public void setUp() {
    divisionOperation = new DivisionOperation();
  }

  @Test
  public void shouldDivideNumbers() throws ValidationException {
    Double result1 = divisionOperation.calculate(1, 2);
    assertEquals(0.5, result1);

    Double result2 = divisionOperation.calculate(10, 2);
    assertEquals(5, result2);
  }

  @Test
  public void shouldThrowValidationErrorWhenDividedByZero() {
    assertThrows(ValidationException.class, () -> divisionOperation.calculate(1, 0));
  }

  @Test
  void shouldHaveCorrectSignature() {
    assertEquals(Operator.DIVISION.getSignature(), divisionOperation.getSignature());
  }


}