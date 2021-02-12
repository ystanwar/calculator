package eu.sezzle.calculator.calculator;

import eu.sezzle.calculator.calculator.supported.operations.SupportedOperations;
import eu.sezzle.calculator.errorcodes.InternalErrorCodes;
import eu.sezzle.calculator.exceptions.ValidationException;
import eu.sezzle.calculator.infixpostfix.InfixToPostfixConverter;
import eu.sezzle.calculator.operators.Operators;

import java.util.Optional;
import java.util.Queue;
import java.util.Stack;

import static eu.sezzle.calculator.utils.generalUtils.isNumber;

//This class in an implementation of calculator specific to sezzle
//here I have used infix to postfix approach, but I can also use different flavours of it if I want
//Responsibility of this class is to evaluate infix expressions
public class BodmasCalculator extends Calculator {

  private final InfixToPostfixConverter infixToPostfixConverter;

  public BodmasCalculator(SupportedOperations supportedOperations, InfixToPostfixConverter infixToPostfixConverter) {
    super(supportedOperations);
    this.infixToPostfixConverter = infixToPostfixConverter;
  }

  @Override
  public double evaluate(String[] expression) throws ValidationException {

    if (expression.length == 0) {
      throw new ValidationException(InternalErrorCodes.EMPTY_EXPRESSION,
          "enter some expression to get it evaluated");
    }
    Queue<String> postfixExpression = infixToPostfixConverter.getPostfix(expression);
    Stack<String> processingStack = new Stack<>();


    for (String currentChar : postfixExpression) {

      if (isNumber(currentChar)) {
        processingStack.push(currentChar);
      } else {

        if (processingStack.size() >= 2) {

          Double operand1 = Double.valueOf(processingStack.peek());
          processingStack.pop();
          Double operand2 = Double.valueOf(processingStack.peek());
          processingStack.pop();

          Optional<Operators> currentOp = supportedOperations.getOperator(currentChar);

          if (currentOp.isPresent()) {
            Double cal = currentOp.get().calculate(operand2, operand1);
            processingStack.push(cal.toString());
          } else {
            throw new ValidationException(InternalErrorCodes.OPERATOR_NOT_SUPPORTED,
                String.format("%s is not supported yet", currentChar));
          }

        } else {
          throw new ValidationException(InternalErrorCodes.INVALID_EXPRESSION, String.join(" ", expression));
        }

      }
    }

    if (processingStack.size() != 1) {
      throw new ValidationException(InternalErrorCodes.INVALID_EXPRESSION, String.join(" ", expression));
    }


    return Double.valueOf(processingStack.peek());
  }


}
