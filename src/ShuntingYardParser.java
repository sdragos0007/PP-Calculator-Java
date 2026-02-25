import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ShuntingYardParser {

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int getPrecedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return -1;
    }

    public List<String> parse(String expression) {
        List<String> output = new ArrayList<>();
        Deque<Character> operators = new ArrayDeque<>();

        // Eliminăm spațiile pentru o procesare mai ușoară
        expression = expression.replaceAll("\\s+", "");

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                output.add(number.toString());
                i--; // Compensăm incrementarea din bucla
            }
            else if (c == '(') {
                operators.push(c);
            }
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    output.add(String.valueOf(operators.pop()));
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                }
            }
            else if (isOperator(c)) {
                while (!operators.isEmpty() && getPrecedence(operators.peek()) >= getPrecedence(c)) {
                    output.add(String.valueOf(operators.pop()));
                }
                operators.push(c);
            }
        }

        // Golim stiva de operatorii rămași
        while (!operators.isEmpty()) {
            output.add(String.valueOf(operators.pop()));
        }

        return output;
    }

    // Metodă temporară pentru testare
//    public static void main(String[] args) {
//        ShuntingYardParser parser = new ShuntingYardParser();
//
//        String testExpression = "2*(4-5/6)/456";
//        List<String> result = parser.parse(testExpression);
//
//        System.out.println("Expresia initiala: " + testExpression);
//        System.out.println("Forma Poloneza Inversa: " + result);
//    }
}