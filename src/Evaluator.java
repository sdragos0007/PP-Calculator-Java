import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Arrays;

public class Evaluator {

    public double evaluate(List<String> Tokens) {
        // Folosim ArrayDeque ca o stivă explicită
        Deque<Double> stack = new ArrayDeque<>();

        for (String token : Tokens) {

            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {

                double right = stack.pop();
                double left = stack.pop();

                switch (token) {
                    case "+": stack.push(left + right); break;
                    case "-": stack.push(left - right); break;
                    case "*": stack.push(left * right); break;
                    case "/": stack.push(left / right); break;
                }
            } else {
                stack.push(Double.parseDouble(token));
            }
        }
        return stack.pop();
    }

    // Metodă temporară pentru testare
//    public static void main(String[] args) {
//        Evaluator evaluator = new Evaluator();
//
//        // Aceasta este lista exactă pe care am obținut-o din ShuntingYardParser
//        // pentru expresia "2*(4-5/6)/456"
//        List<String> testTokens = Arrays.asList("2", "4", "5", "6", "/", "-", "*", "456", "/");
//
//        double result = evaluator.evaluate(testTokens);
//        System.out.println("Lista RPN: " + testTokens);
//        System.out.println("Rezultat calculat: " + result);
//    }
}