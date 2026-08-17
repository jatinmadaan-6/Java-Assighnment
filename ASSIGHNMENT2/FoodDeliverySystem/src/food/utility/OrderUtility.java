package food.utility;

import food.model.FoodOrder;

public class OrderUtility {

    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    public static boolean isValidCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static String generateOrderSummary(FoodOrder order, double discount, double finalAmount) {
        return "Order #" + order.getOrderId() + " | Customer: " + order.getCustomerName()
                + " | Bill: Rs. " + order.getAmount() + " | Discount: Rs. " + discount
                + " | Delivery: Rs. " + order.calculateDeliveryCharge()
                + " | Payable: Rs. " + finalAmount;
    }
}
