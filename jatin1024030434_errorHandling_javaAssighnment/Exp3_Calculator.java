import java.util.Scanner;

/*
 * Experiment 3
 * Calculator handling:
 *  - Division by zero
 *  - Invalid numeric input
 *  - Invalid operators
 * using separate exception handlers.
 */
public class Exp3_Calculator {

    // Custom exception for invalid operator
    static class InvalidOperatorException extends Exception {
        public InvalidOperatorException(String message) {
            super(message);
        }
    }

    public static double calculate(double num1, double num2, char operator) throws InvalidOperatorException {
        switch (operator) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/':
                return num1 / num2; // may throw ArithmeticException at runtime for doubles it gives Infinity,
                                     // so we explicitly check below in main for integer-style div by zero
            default:
                throw new InvalidOperatorException("Invalid operator: '" + operator + "'. Use +, -, *, /");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        String input1 = sc.nextLine();
        System.out.print("Enter second number: ");
        String input2 = sc.nextLine();
        System.out.print("Enter operator (+, -, *, /): ");
        String opInput = sc.nextLine();

        try {
            double num1 = Double.parseDouble(input1);
            double num2 = Double.parseDouble(input2);

            if (opInput.length() != 1) {
                throw new InvalidOperatorException("Operator must be a single character.");
            }
            char operator = opInput.charAt(0);

            if (operator == '/' && num2 == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }

            double result = calculate(num1, num2, operator);
            System.out.println("Result: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input. Please enter valid numbers.");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidOperatorException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Calculator session ended.");
        }
    }
}
