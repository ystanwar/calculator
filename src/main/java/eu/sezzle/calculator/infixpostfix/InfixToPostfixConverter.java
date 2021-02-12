package eu.sezzle.calculator.infixpostfix;

import eu.sezzle.calculator.exceptions.ValidationException;

import java.util.Queue;

//This defines the contract for infix to postfix
public interface InfixToPostfixConverter {
  Queue<String> getPostfix(String[] infixExpression) throws ValidationException;
}
