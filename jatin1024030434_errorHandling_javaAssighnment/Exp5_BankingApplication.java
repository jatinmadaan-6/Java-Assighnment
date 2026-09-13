import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Experiment 5
 * Simple banking application: deposit, withdraw
 * Custom exceptions: InsufficientBalanceException, InvalidAmountException,
 * AccountNotFoundException
 * Demonstrates exception propagation from one method to another.
 */
public class Exp5_BankingApplication {

    // ---------- Custom Exceptions ----------
    static class InsufficientBalanceException extends Exception {
        public InsufficientBalanceException(String message) { super(message); }
    }

    static class InvalidAmountException extends Exception {
        public InvalidAmountException(String message) { super(message); }
    }

    static class AccountNotFoundException extends Exception {
        public AccountNotFoundException(String message) { super(message); }
    }

    // ---------- Bank Account ----------
    static class Account {
        String accountNumber;
        double balance;

        Account(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }
    }

    // ---------- Bank ----------
    static class Bank {
        Map<String, Account> accounts = new HashMap<>();

        void addAccount(String accNo, double initialBalance) {
            accounts.put(accNo, new Account(accNo, initialBalance));
        }

        // Helper: propagates AccountNotFoundException upward
        Account getAccount(String accNo) throws AccountNotFoundException {
            Account acc = accounts.get(accNo);
            if (acc == null) {
                throw new AccountNotFoundException("No account found with number: " + accNo);
            }
            return acc;
        }

        void deposit(String accNo, double amount) throws AccountNotFoundException, InvalidAmountException {
            if (amount <= 0) {
                throw new InvalidAmountException("Deposit amount must be positive. Given: " + amount);
            }
            Account acc = getAccount(accNo); // propagation happens here
            acc.balance += amount;
        }

        void withdraw(String accNo, double amount)
                throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {
            if (amount <= 0) {
                throw new InvalidAmountException("Withdrawal amount must be positive. Given: " + amount);
            }
            Account acc = getAccount(accNo); // propagation happens here
            if (amount > acc.balance) {
                throw new InsufficientBalanceException(
                    "Insufficient balance. Available: " + acc.balance + ", Requested: " + amount);
            }
            acc.balance -= amount;
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount("ACC001", 5000.0);

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1": {
                        System.out.print("Enter account number: ");
                        String accNo = sc.nextLine();
                        System.out.print("Enter deposit amount: ");
                        double amt = Double.parseDouble(sc.nextLine());
                        bank.deposit(accNo, amt);
                        System.out.println("Deposit successful. New balance: " + bank.getAccount(accNo).balance);
                        break;
                    }
                    case "2": {
                        System.out.print("Enter account number: ");
                        String accNo = sc.nextLine();
                        System.out.print("Enter withdrawal amount: ");
                        double amt = Double.parseDouble(sc.nextLine());
                        bank.withdraw(accNo, amt);
                        System.out.println("Withdrawal successful. New balance: " + bank.getAccount(accNo).balance);
                        break;
                    }
                    case "3": {
                        System.out.print("Enter account number: ");
                        String accNo = sc.nextLine();
                        Account acc = bank.getAccount(accNo);
                        System.out.println("Balance for " + accNo + ": " + acc.balance);
                        break;
                    }
                    case "4":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid menu choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric amount.");
            } catch (InvalidAmountException | InsufficientBalanceException | AccountNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("(Transaction attempt processed)");
            }
        }

        System.out.println("Thank you for banking with us.");
        sc.close();
    }
}
