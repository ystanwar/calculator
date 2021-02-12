package eu.sezzle.calculator.utils;

public class generalUtils {

  public static boolean isNumber(String str) {
    try {
      Double.valueOf(str);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
