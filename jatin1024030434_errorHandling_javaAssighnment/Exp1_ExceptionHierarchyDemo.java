import java.util.Scanner;

/*
 * Experiment 1
 * Demonstrates common built-in exceptions:
 * ArithmeticException, NullPointerException,
 * ArrayIndexOutOfBoundsException, NumberFormatException
 */
public class Exp1_ExceptionHierarchyDemo {

    public static void main(String[] args) {

        // 1. ArithmeticException
        try {
            int a = 10, b = 0;
            int result = a / b;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught: " + e.getMessage());
        }

        // 2. NullPointerException
        try {
            String str = null;
            System.out.println("Length: " + str.length());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + e.getMessage());
        }

        // 3. ArrayIndexOutOfBoundsException
        try {
            int[] arr = {10, 20, 30};
            System.out.println("Element: " + arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught: " + e.getMessage());
        }

        // 4. NumberFormatException
        try {
            String numStr = "abc123";
            int num = Integer.parseInt(numStr);
            System.out.println("Parsed number: " + num);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException caught: " + e.getMessage());
        }

        System.out.println("\nAll exceptions handled. Program continues normally.");
    }
}
