package eu.sezzle.calculator.controller;

import eu.sezzle.calculator.calculator.BodmasCalculator;
import eu.sezzle.calculator.calculator.Calculator;
import eu.sezzle.calculator.calculator.supported.operations.SezzleSupportedOperations;
import eu.sezzle.calculator.calculator.supported.operations.SupportedOperations;
import eu.sezzle.calculator.exceptions.ValidationException;
import eu.sezzle.calculator.infixpostfix.InfixToPostfixConverter;
import eu.sezzle.calculator.infixpostfix.SezzleInfixToPostfixConverter;
import eu.sezzle.calculator.operators.AdditionOperation;
import eu.sezzle.calculator.operators.DivisionOperation;
import eu.sezzle.calculator.operators.MultiplicationOperation;
import eu.sezzle.calculator.operators.SubtractionOperation;

import java.text.DecimalFormat;
import java.util.Scanner;

// Class to connect all components, this is basically to take input
// and create calculator object with required dependencies
// was not able to write test for this class because of less time
public class CalculatorController {
  private static Calculator getCalculator() {

    SupportedOperations sezzleSupportedOperations = new SezzleSupportedOperations();

    sezzleSupportedOperations.addOperator(new AdditionOperation());
    sezzleSupportedOperations.addOperator(new SubtractionOperation());
    sezzleSupportedOperations.addOperator(new MultiplicationOperation());
    sezzleSupportedOperations.addOperator(new DivisionOperation());


    InfixToPostfixConverter sezzleInfixToPostfixConverterConverter = new SezzleInfixToPostfixConverter();

    Calculator sezzleCalculator = new BodmasCalculator(
        sezzleSupportedOperations, sezzleInfixToPostfixConverterConverter
    );

    return sezzleCalculator;

  }

  public static void calculateExpression() {
    DecimalFormat df = new DecimalFormat("0.###");
    System.out.println("Started calculator");
    Scanner takeInput = new Scanner(System.in);
    Calculator calculator = getCalculator();
    while (true) {
      System.out.println("Enter expression");
      String expression = takeInput.nextLine();
      System.out.println("Calculating ...");
      try {
        System.out.println(expression + " = " + df.format(calculator.evaluate(expression.split(" "))));
      } catch (ValidationException ex) {
        System.out.println(ex.getErrorCode() + " : " + ex.getErrorMessage());
      }
    }

  }
}
