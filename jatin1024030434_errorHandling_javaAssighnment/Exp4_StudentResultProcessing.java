import java.util.Scanner;

/*
 * Experiment 4
 * Student result processing program.
 * Marks must be between 0 and 100.
 * Custom InvalidMarksException thrown for invalid marks.
 * Total, percentage, and grade calculated only when all input is valid.
 */
public class Exp4_StudentResultProcessing {

    // Custom exception
    static class InvalidMarksException extends Exception {
        public InvalidMarksException(String message) {
            super(message);
        }
    }

    static void validateMarks(int marks, String subject) throws InvalidMarksException {
        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException(
                "Invalid marks (" + marks + ") for subject '" + subject + "'. Marks must be between 0 and 100.");
        }
    }

    static char calculateGrade(double percentage) {
        if (percentage >= 90) return 'A';
        else if (percentage >= 75) return 'B';
        else if (percentage >= 60) return 'C';
        else if (percentage >= 40) return 'D';
        else return 'F';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] subjects = {"Maths", "Physics", "Chemistry", "English", "Computer Science"};
        int[] marks = new int[subjects.length];
        boolean allValid = true;

        for (int i = 0; i < subjects.length; i++) {
            try {
                System.out.print("Enter marks for " + subjects[i] + " (0-100): ");
                int m = Integer.parseInt(sc.nextLine());
                validateMarks(m, subjects[i]);
                marks[i] = m;
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid integer for " + subjects[i] + ".");
                allValid = false;
            } catch (InvalidMarksException e) {
                System.out.println("Error: " + e.getMessage());
                allValid = false;
            }
        }

        if (allValid) {
            int total = 0;
            for (int m : marks) {
                total += m;
            }
            double percentage = (double) total / subjects.length;
            char grade = calculateGrade(percentage);

            System.out.println("\n----- Result -----");
            System.out.println("Total Marks   : " + total + " / " + (subjects.length * 100));
            System.out.println("Percentage    : " + percentage + "%");
            System.out.println("Grade         : " + grade);
        } else {
            System.out.println("\nResult cannot be calculated due to invalid input(s). Please re-run the program.");
        }

        sc.close();
    }
}
