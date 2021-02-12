package eu.sezzle.calculator.infixpostfix.implementation.sezzle;

import eu.sezzle.calculator.exceptions.ValidationException;
import eu.sezzle.calculator.infixpostfix.SezzleInfixToPostfixConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Queue;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SezzleInfixToPostfixConverterTest {

  private SezzleInfixToPostfixConverter sezzleInfixToPostfixConverter;

  @BeforeEach
  public void setUp() {
    sezzleInfixToPostfixConverter = new SezzleInfixToPostfixConverter();
  }

  @ParameterizedTest
  @CsvSource({
      "2 + 3, 2 3 +",
      "( 2 + 3 ), 2 3 +",
      "3 + 2 * 3, 3 2 3 * +",
      "3 - 2 / 3, 3 2 3 / -",
      "( 3 + 2 ) * 3,3 2 + 3 *",
      "3 + 2 + 3 * 4, 3 2 + 3 4 * +"
  })
  public void shouldReturnPostfixForValidInfixExpression(String infix, String expectedPostfix)
      throws ValidationException {
    Queue<String> convertedPostfix = sezzleInfixToPostfixConverter.getPostfix(
        infix.split(" ")
    );
    assertEquals(expectedPostfix,
        convertedPostfix.stream()
            .map(Object::toString)
            .collect(Collectors.joining(" ")));
  }

  @ParameterizedTest
  @CsvSource({
      "3 + 2 % 3",
      "3 - 2 # 3",
  })
  public void shouldThrowValidationExceptionForUnSupportedCharacters(String invalidInfix) {
    try {
      sezzleInfixToPostfixConverter.getPostfix(invalidInfix.split(" "));
    } catch (ValidationException ex) {
      assertEquals(String.format("%s is invalid", invalidInfix), ex.getErrorMessage());
    } catch (Exception ex) {
      assertTrue(false);
    }
  }


}