import java.util.Scanner;

/*
 * Experiment 6
 * Login system with exception-based validation.
 * Custom exceptions: InvalidUsernameException, InvalidPasswordException,
 * AccountLockedException
 * Locks account after fixed number of failed attempts.
 */
public class Exp6_LoginSystem {

    static final String VALID_USERNAME = "admin";
    static final String VALID_PASSWORD = "Pass@123";
    static final int MAX_ATTEMPTS = 3;

    // ---------- Custom Exceptions ----------
    static class InvalidUsernameException extends Exception {
        public InvalidUsernameException(String message) { super(message); }
    }

    static class InvalidPasswordException extends Exception {
        public InvalidPasswordException(String message) { super(message); }
    }

    static class AccountLockedException extends Exception {
        public AccountLockedException(String message) { super(message); }
    }

    static void validateLogin(String username, String password, int attemptsSoFar)
            throws InvalidUsernameException, InvalidPasswordException, AccountLockedException {

        if (attemptsSoFar >= MAX_ATTEMPTS) {
            throw new AccountLockedException("Account locked due to too many failed attempts.");
        }
        if (!username.equals(VALID_USERNAME)) {
            throw new InvalidUsernameException("Username '" + username + "' does not exist.");
        }
        if (!password.equals(VALID_PASSWORD)) {
            throw new InvalidPasswordException("Incorrect password for user '" + username + "'.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int failedAttempts = 0;
        boolean loggedIn = false;
        boolean accountLocked = false;

        while (!loggedIn && !accountLocked) {
            try {
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();

                validateLogin(username, password, failedAttempts);

                loggedIn = true;
                System.out.println("Login successful! Welcome, " + username + ".");

            } catch (InvalidUsernameException e) {
                failedAttempts++;
                System.out.println("Login failed: " + e.getMessage());
                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - failedAttempts));
            } catch (InvalidPasswordException e) {
                failedAttempts++;
                System.out.println("Login failed: " + e.getMessage());
                System.out.println("Attempts remaining: " + (MAX_ATTEMPTS - failedAttempts));
            } catch (AccountLockedException e) {
                accountLocked = true;
                System.out.println("Login failed: " + e.getMessage());
            } finally {
                if (accountLocked) {
                    System.out.println("Please contact support to unlock your account.");
                } else if (!loggedIn) {
                    System.out.println("Please try again.\n");
                }
            }
        }

        sc.close();
    }
}
