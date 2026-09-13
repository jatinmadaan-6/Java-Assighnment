package library.service;

import library.model.LibraryResource;
import library.util.InputValidator;

public class FineCalculator {

    public static double getFine(LibraryResource resource, int overdueDays) {
        if (!InputValidator.isValidOverdueDays(overdueDays)) {
            throw new IllegalArgumentException("Overdue days cannot be negative");
        }
        return resource.calculateFine(overdueDays);
    }

    public static double getTotalFine(LibraryResource[] resources, int[] overdueDays) {
        double total = 0;
        for (int i = 0; i < resources.length; i++) {
            if (resources[i] != null && overdueDays[i] > 0) {
                total += getFine(resources[i], overdueDays[i]);
            }
        }
        return total;
    }
}
