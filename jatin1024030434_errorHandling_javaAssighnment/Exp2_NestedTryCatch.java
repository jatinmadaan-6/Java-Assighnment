/*
 * Experiment 2
 * Nested try-catch blocks generating different exceptions
 * in the inner and the outer block.
 */
public class Exp2_NestedTryCatch {

    public static void main(String[] args) {

        try {
            System.out.println("Outer try block started");
            int[] numbers = {1, 2, 3};

            try {
                System.out.println("Inner try block started");
                // Inner exception: ArrayIndexOutOfBoundsException
                System.out.println("Accessing invalid index: " + numbers[10]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner catch: ArrayIndexOutOfBoundsException -> " + e.getMessage());
                // Generating another exception inside the inner catch block
                int x = 5 / 0;
            } finally {
                System.out.println("Inner finally block executed");
            }

            System.out.println("This line will not execute because inner block threw again");

        } catch (ArithmeticException e) {
            // Outer exception: ArithmeticException (raised from inside inner catch)
            System.out.println("Outer catch: ArithmeticException -> " + e.getMessage());
        } finally {
            System.out.println("Outer finally block executed");
        }

        System.out.println("\nProgram continues after nested try-catch handling.");
    }
}
