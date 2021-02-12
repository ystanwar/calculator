package eu.sezzle.calculator.infixpostfix;

import eu.sezzle.calculator.errorcodes.InternalErrorCodes;
import eu.sezzle.calculator.exceptions.ValidationException;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import static eu.sezzle.calculator.utils.generalUtils.isNumber;
import static eu.sezzle.calculator.utils.operatorMap.getOperatorsWithPriority;


//This class in an implementation of calculator specific to sezzle aand converts infix to postfix
//conversion to postfix makes our calculation easy
//the code is written in such way,we can replace this implementation with any other and bodmas calculator will work
public class SezzleInfixToPostfixConverter implements InfixToPostfixConverter {

  public final Queue<String> getPostfix(String[] infixExpression) throws ValidationException {


    String openBracket = "(";
    String closeBracket = ")";
    Map<String, Integer> operatorMap = getOperatorsWithPriority();
    Queue<String> postfixExpression = new LinkedList<>();
    Stack<String> processingStack = new Stack<>();

    for (String currentChar : infixExpression) {

      if (openBracket.equals(currentChar)) {

        processingStack.push(currentChar);
        continue;

      } else if (closeBracket.equals(currentChar)) {

        while (!processingStack.empty() && !openBracket.equals(processingStack.peek())) {
          postfixExpression.add(processingStack.pop());
        }
        processingStack.pop();
        continue;

      } else if (operatorMap.containsKey(currentChar)) {

        while (isLowerPrecedence(operatorMap, processingStack, currentChar)) {
          postfixExpression.add(processingStack.pop());
        }
        processingStack.push(currentChar);
        continue;
      } else if (isNumber(currentChar)) {

        postfixExpression.add(currentChar);
        continue;
      }

      throw new ValidationException(InternalErrorCodes.INVALID_EXPRESSION,
          String.format("%s is invalid", String.join(" ", infixExpression)));
    }
    // at the end, pop all the elements in S to Q
    while (!processingStack.isEmpty()) {
      postfixExpression.add(processingStack.pop());
    }

    return postfixExpression;
  }

  static boolean isLowerPrecedence(Map<String, Integer> operatorMap,
                                   Stack<String> processingStack,
                                   String currentOperator) {
    return !processingStack.empty()
        && operatorMap.get(currentOperator) <= operatorMap.get(processingStack.peek());
  }

}
