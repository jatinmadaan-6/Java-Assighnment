package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.service.BillingService;
import food.utility.OrderUtility;

public class FoodDeliveryMain {

    public static void main(String[] args) {

        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(1, "Aman Sharma", 450.0);
        orders[1] = new PremiumOrder(2, "Riya Kapoor", 1200.0);
        orders[2] = new RegularOrder(3, "Karan Mehta", 300.0);
        orders[3] = new PremiumOrder(4, "Simran Kaur", 850.0);
        orders[4] = new RegularOrder(5, "Vikram Singh", 600.0);
        orders[5] = new PremiumOrder(6, "Neha Verma", 950.0);

        for (FoodOrder order : orders) {
            if (!OrderUtility.isValidCustomerName(order.getCustomerName())
                    || !OrderUtility.isValidAmount(order.getAmount())) {
                System.out.println("Invalid order data for order #" + order.getOrderId());
                continue;
            }

            double discount = BillingService.getDiscount(order);
            double finalAmount = BillingService.getFinalAmount(order);

            System.out.println("Restaurant       : " + FoodOrder.restaurantName);
            System.out.println("Order ID         : " + order.getOrderId());
            System.out.println("Customer Name    : " + order.getCustomerName());
            System.out.println("Bill Amount      : Rs. " + order.getAmount());
            System.out.println("Discount         : Rs. " + discount);
            System.out.println("Delivery Charge  : Rs. " + order.calculateDeliveryCharge());
            System.out.println("Final Payable    : Rs. " + finalAmount);
            System.out.println(OrderUtility.generateOrderSummary(order, discount, finalAmount));
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Orders Placed : " + FoodOrder.getTotalOrders());
    }
}
