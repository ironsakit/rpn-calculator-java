public class Main {
  public static void main(String[] args) {
    MathEvaluator eval = new MathEvaluator();

    String[] expressions = {
      "3 + 4 * (2 - 1)",
      "5 * -2 + (3 - -1)",
      "(3.5 + 2.5) * 2"
    };

    for (String expr : expressions) {
      double result = eval.calculate(expr);
      System.out.println("Expression: " + expr);
      System.out.println("Result: " + result + "\n");
    }
  }
}
