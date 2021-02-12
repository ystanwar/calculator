package eu.sezzle.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static eu.sezzle.calculator.controller.CalculatorController.calculateExpression;

@SpringBootApplication
public class CalculatorApplication {

  public static void main(String[] args) {
    //this method will call our calculator
    //NOTE: only limitation that our calculator has is we have to give space separated expression
    //correct ex: 2 + 3 or 5 / 2 - 3
    //incorrect ex: 2+3 or 5/2-3 will be considered invalid
    calculateExpression();
    SpringApplication.run(CalculatorApplication.class, args);
  }

}
