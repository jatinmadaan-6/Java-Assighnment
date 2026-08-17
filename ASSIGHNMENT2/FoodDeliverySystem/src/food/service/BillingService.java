package food.service;

import food.model.Discountable;
import food.model.FoodOrder;

public class BillingService {

    public static double getFinalAmount(FoodOrder order) {
        double discount = 0;
        if (order instanceof Discountable) {
            discount = ((Discountable) order).applyDiscount();
        }
        return order.getAmount() - discount + order.calculateDeliveryCharge();
    }

    public static double getDiscount(FoodOrder order) {
        if (order instanceof Discountable) {
            return ((Discountable) order).applyDiscount();
        }
        return 0;
    }
}
