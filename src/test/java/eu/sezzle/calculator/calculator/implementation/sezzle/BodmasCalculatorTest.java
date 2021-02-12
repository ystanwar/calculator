package eu.sezzle.calculator.calculator.implementation.sezzle;

import eu.sezzle.calculator.calculator.BodmasCalculator;
import eu.sezzle.calculator.calculator.supported.operations.SupportedOperations;
import eu.sezzle.calculator.errorcodes.InternalErrorCodes;
import eu.sezzle.calculator.exceptions.ValidationException;
import eu.sezzle.calculator.infixpostfix.InfixToPostfixConverter;
import eu.sezzle.calculator.operators.AdditionOperation;
import eu.sezzle.calculator.operators.DivisionOperation;
import eu.sezzle.calculator.operators.MultiplicationOperation;
import eu.sezzle.calculator.operators.SubtractionOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BodmasCalculatorTest {

  private BodmasCalculator bodmasCalculator;

  @Mock
  private SupportedOperations supportedOperations;

  @Mock
  private InfixToPostfixConverter infixToPostfixConverter;

  @BeforeEach
  public void setUp() {
    bodmasCalculator = new BodmasCalculator(supportedOperations, infixToPostfixConverter);
  }

  @Test
  public void shouldThrowValidationExceptionIfEmptyInfixExpression() {
    String[] emptyInfix = {};
    try {
      bodmasCalculator.evaluate(emptyInfix);
    } catch (ValidationException ex) {
      assertEquals("expression is empty", ex.getErrorCode().getDescription());
      assertEquals("enter some expression to get it evaluated", ex.getErrorMessage());
    } catch (Exception ex) {
      assertTrue(false);
    }
  }

  @Test
  public void shouldCallInfixConverterWithCorrectValues() throws ValidationException {
    String[] infix = {"2", "+", "3"};
    Queue<String> postfix = new LinkedList<>();
    postfix.add(infix[2]);
    postfix.add(infix[0]);
    postfix.add(infix[1]);

    Mockito.when(infixToPostfixConverter.getPostfix(any())).thenReturn(postfix);
    Mockito.when(supportedOperations.getOperator(any())).thenReturn(java.util.Optional.of(new AdditionOperation()));

    bodmasCalculator.evaluate(infix);

    ArgumentCaptor<String[]> infixCaptor = ArgumentCaptor.forClass(String[].class);
    Mockito.verify(infixToPostfixConverter, Mockito.times(1)).getPostfix(infixCaptor.capture());

    String[] capturedInfix = infixCaptor.getValue();
    assertEquals(infix, capturedInfix);

  }


  @Test
  public void shouldCallSupportedOperationsWithCorrectValues() throws ValidationException {
    String[] infix = {"2", "+", "3"};
    Queue<String> postfix = new LinkedList<>();
    postfix.add(infix[2]);
    postfix.add(infix[0]);
    postfix.add(infix[1]);

    Mockito.when(infixToPostfixConverter.getPostfix(any())).thenReturn(postfix);
    Mockito.when(supportedOperations.getOperator(any())).thenReturn(java.util.Optional.of(new AdditionOperation()));

    bodmasCalculator.evaluate(infix);

    ArgumentCaptor<String> fetchedOperator = ArgumentCaptor.forClass(String.class);
    Mockito.verify(supportedOperations, Mockito.times(1)).getOperator(fetchedOperator.capture());


    String capturedOperator = fetchedOperator.getValue();
    assertEquals(infix[1], capturedOperator);
  }

  @Test
  public void shouldThrowValidationExceptionForUnSupportedOperators() throws ValidationException {
    String[] infix = {"2", "%", "3"};
    Queue<String> postfix = new LinkedList<>();
    postfix.add(infix[2]);
    postfix.add(infix[0]);
    postfix.add(infix[1]);

    Mockito.when(infixToPostfixConverter.getPostfix(any())).thenReturn(postfix);
    Mockito.when(supportedOperations.getOperator(infix[1])).thenReturn(Optional.empty());

    try {
      bodmasCalculator.evaluate(infix);
    } catch (ValidationException ex) {
      assertEquals(InternalErrorCodes.OPERATOR_NOT_SUPPORTED.getDescription(), ex.getErrorCode().getDescription());
      assertEquals("% is not supported yet", ex.getErrorMessage());
    }

  }

  @ParameterizedTest
  @CsvSource({
      "22, 22, false",
      "2 3,2 3, false",
      "2 + 3 4, 2 3 4 +, true",
  })
  public void shouldThrowValidationExceptionForInvalidPostfixExpressions(String infix, String postfix,
                                                                         boolean hasOperator)
      throws ValidationException {
    Queue<String> calculatedPostfix = getCalculatedPostfix(postfix);
    Mockito.when(infixToPostfixConverter.getPostfix(any())).thenReturn(calculatedPostfix);
    if (hasOperator) {
      Mockito.when(supportedOperations.getOperator(any())).thenReturn(Optional.of(new AdditionOperation()));
    }

    try {
      bodmasCalculator.evaluate(infix.split(" "));
    } catch (ValidationException ex) {
      assertEquals(InternalErrorCodes.INVALID_EXPRESSION.getDescription(), ex.getErrorCode().getDescription());
      assertEquals(infix, ex.getErrorMessage());
    }

  }


  @ParameterizedTest()
  @CsvSource({
      "2 + 3, 2 3 +",
      "( 2 + 3 ), 2 3 +",
  })
  public void shouldEvaluateValidPostfixExpressionsWithAdditionOperator(String infix, String postfix)
      throws ValidationException {
    Queue<String> calculatedPostfix = getCalculatedPostfix(postfix);

    Mockito.when(infixToPostfixConverter.getPostfix(any())).thenReturn(calculatedPostfix);
    Mockito.when(supportedOperations.getOperator(any())).thenReturn(java.util.Optional.of(new AdditionOperation()));

    Double result = bodmasCalculator.evaluate(infix.split(" "));
    assertEquals(5, result);

  }

  @ParameterizedTest
  @CsvSource({
      "3 + 2 * 3, 3 2 3 * +",
  })
  public void shouldEvaluateValidPostfixExpressionsWithMultiplicationOperator(String infix, String postfix)
      throws ValidationException {
    Queue<String> calculatedPostfix = getCalculatedPostfix(postfix);

    Mockito.when(infixToPostfixConverter.getPostfix(any())).thenReturn(calculatedPostfix);
    Mockito.when(supportedOperations.getOperator(any()))
        .thenReturn(java.util.Optional.of(new MultiplicationOperation()))
        .thenReturn(java.util.Optional.of(new AdditionOperation()));

    Double result = bodmasCalculator.evaluate(infix.split(" "));
    assertEquals(9, result);

  }

  @ParameterizedTest
  @CsvSource({
      "3 - 2 / 3, 3 2 3 / -"
  })
  public void shouldEvaluateValidPostfixExpressionsWithDivisionOperator(String infix, String postfix)
      throws ValidationException {
    Queue<String> calculatedPostfix = getCalculatedPostfix(postfix);

    Mockito.when(infixToPostfixConverter.getPostfix(any())).thenReturn(calculatedPostfix);
    Mockito.when(supportedOperations.getOperator(any()))
        .thenReturn(java.util.Optional.of(new DivisionOperation()))
        .thenReturn(java.util.Optional.of(new SubtractionOperation()));

    Double result = bodmasCalculator.evaluate(infix.split(" "));
    assertEquals(2.3333333333333335, result);

  }

  @ParameterizedTest
  @CsvSource({
      "3 + 2 - 3 * 4, 3 2 + 3 4 * -"
  })
  public void shouldEvaluateValidPostfixExpressionsWithMultipleOperator(String infix, String postfix)
      throws ValidationException {
    Queue<String> calculatedPostfix = getCalculatedPostfix(postfix);

    Mockito.when(infixToPostfixConverter.getPostfix(any())).thenReturn(calculatedPostfix);
    Mockito.when(supportedOperations.getOperator(any()))
        .thenReturn(java.util.Optional.of(new AdditionOperation()))
        .thenReturn(java.util.Optional.of(new MultiplicationOperation()))
        .thenReturn(java.util.Optional.of(new SubtractionOperation()));

    Double result = bodmasCalculator.evaluate(infix.split(" "));
    assertEquals(-7, result);

  }

  private Queue<String> getCalculatedPostfix(String postfix) {
    Queue<String> calculatedPostfix = new LinkedList<>();
    String[] postfixExpression = postfix.split(" ");
    Arrays.stream(postfixExpression).forEach(s -> calculatedPostfix.add(s));
    return calculatedPostfix;
  }


}