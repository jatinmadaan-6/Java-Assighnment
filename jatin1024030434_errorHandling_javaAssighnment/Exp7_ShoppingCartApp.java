import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Experiment 7
 * Shopping/Cart application using a complete exception hierarchy:
 *
 * ApplicationException
 *  |-- ProductException
 *  |     |-- ProductNotFoundException
 *  |     |-- OutOfStockException
 *  |-- PaymentException
 *  |     |-- InvalidPaymentException
 *  |     |-- InsufficientFundsException
 *  |-- OrderException
 *        |-- EmptyCartException
 *
 * Implements add/remove, search, and payment operations.
 */
public class Exp7_ShoppingCartApp {

    // ---------------- Exception Hierarchy ----------------
    static class ApplicationException extends Exception {
        public ApplicationException(String message) { super(message); }
    }

    static class ProductException extends ApplicationException {
        public ProductException(String message) { super(message); }
    }

    static class ProductNotFoundException extends ProductException {
        public ProductNotFoundException(String message) { super(message); }
    }

    static class OutOfStockException extends ProductException {
        public OutOfStockException(String message) { super(message); }
    }

    static class PaymentException extends ApplicationException {
        public PaymentException(String message) { super(message); }
    }

    static class InvalidPaymentException extends PaymentException {
        public InvalidPaymentException(String message) { super(message); }
    }

    static class InsufficientFundsException extends PaymentException {
        public InsufficientFundsException(String message) { super(message); }
    }

    static class OrderException extends ApplicationException {
        public OrderException(String message) { super(message); }
    }

    static class EmptyCartException extends OrderException {
        public EmptyCartException(String message) { super(message); }
    }

    // ---------------- Product ----------------
    static class Product {
        String name;
        double price;
        int stock;

        Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }

    // ---------------- Store / Cart ----------------
    static class Store {
        Map<String, Product> catalog = new HashMap<>();

        void addProduct(String name, double price, int stock) {
            catalog.put(name.toLowerCase(), new Product(name, price, stock));
        }

        Product search(String name) throws ProductNotFoundException {
            Product p = catalog.get(name.toLowerCase());
            if (p == null) {
                throw new ProductNotFoundException("Product '" + name + "' not found in catalog.");
            }
            return p;
        }
    }

    static class Cart {
        List<Product> items = new ArrayList<>();

        void addProduct(Store store, String name, int quantity) throws ProductNotFoundException, OutOfStockException {
            Product product = store.search(name); // may throw ProductNotFoundException
            if (product.stock < quantity) {
                throw new OutOfStockException(
                    "Not enough stock for '" + name + "'. Available: " + product.stock + ", Requested: " + quantity);
            }
            for (int i = 0; i < quantity; i++) {
                items.add(product);
            }
            product.stock -= quantity;
        }

        void removeProduct(String name) throws ProductNotFoundException {
            Product toRemove = null;
            for (Product p : items) {
                if (p.name.equalsIgnoreCase(name)) {
                    toRemove = p;
                    break;
                }
            }
            if (toRemove == null) {
                throw new ProductNotFoundException("Product '" + name + "' is not in the cart.");
            }
            items.remove(toRemove);
            toRemove.stock += 1;
        }

        double getTotal() {
            double total = 0;
            for (Product p : items) {
                total += p.price;
            }
            return total;
        }

        void checkout(double amountPaid) throws EmptyCartException, InvalidPaymentException, InsufficientFundsException {
            if (items.isEmpty()) {
                throw new EmptyCartException("Cannot checkout: cart is empty.");
            }
            if (amountPaid <= 0) {
                throw new InvalidPaymentException("Payment amount must be positive.");
            }
            double total = getTotal();
            if (amountPaid < total) {
                throw new InsufficientFundsException(
                    "Insufficient funds. Total due: " + total + ", Amount paid: " + amountPaid);
            }
            double change = amountPaid - total;
            System.out.println("Payment successful! Total: " + total + " | Change: " + change);
            items.clear();
        }
    }

    // ---------------- Main / Menu ----------------
    public static void main(String[] args) {
        Store store = new Store();
        store.addProduct("Laptop", 55000, 3);
        store.addProduct("Mouse", 500, 10);
        store.addProduct("Keyboard", 1200, 0); // out of stock initially

        Cart cart = new Cart();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Shopping Cart Menu ---");
            System.out.println("1. Search Product");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart Total");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1": {
                        System.out.print("Enter product name to search: ");
                        String name = sc.nextLine();
                        Product p = store.search(name);
                        System.out.println("Found: " + p.name + " | Price: " + p.price + " | Stock: " + p.stock);
                        break;
                    }
                    case "2": {
                        System.out.print("Enter product name to add: ");
                        String name = sc.nextLine();
                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(sc.nextLine());
                        cart.addProduct(store, name, qty);
                        System.out.println(qty + " x '" + name + "' added to cart.");
                        break;
                    }
                    case "3": {
                        System.out.print("Enter product name to remove: ");
                        String name = sc.nextLine();
                        cart.removeProduct(name);
                        System.out.println("'" + name + "' removed from cart.");
                        break;
                    }
                    case "4":
                        System.out.println("Cart total: " + cart.getTotal());
                        break;
                    case "5": {
                        System.out.print("Enter amount to pay: ");
                        double amount = Double.parseDouble(sc.nextLine());
                        cart.checkout(amount);
                        break;
                    }
                    case "6":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid menu choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            } catch (ProductNotFoundException | OutOfStockException e) {
                System.out.println("Product Error: " + e.getMessage());
            } catch (InvalidPaymentException | InsufficientFundsException e) {
                System.out.println("Payment Error: " + e.getMessage());
            } catch (EmptyCartException e) {
                System.out.println("Order Error: " + e.getMessage());
            } catch (ApplicationException e) {
                // Catches any other exception in the hierarchy not caught above
                System.out.println("Application Error: " + e.getMessage());
            } finally {
                System.out.println("(Operation attempted)");
            }
        }

        System.out.println("Thank you for shopping with us!");
        sc.close();
    }
}
