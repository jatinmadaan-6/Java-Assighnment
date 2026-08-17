package library.main;

import library.model.Book;
import library.model.DigitalResource;
import library.model.LibraryResource;
import library.model.Printable;
import library.service.FineCalculator;
import library.util.InputValidator;

public class LibraryMain {

    public static void main(String[] args) {

        LibraryResource[] resources = new LibraryResource[5];
        int[] overdueDays = {3, 0, 7, 2, 5};

        resources[0] = new Book(101, "Data Structures in Java", "R. Lafore", "Pearson");
        resources[1] = new Book(102, "Operating System Concepts", "Silberschatz", "Wiley");
        resources[2] = new DigitalResource(201, "Machine Learning Basics", "A. Ng", "PDF");
        resources[3] = new DigitalResource(202, "Cloud Computing Notes", "T. Erl", "EPUB");
        resources[4] = new Book(103, "Computer Networks", "A. Tanenbaum", "PHI");

        for (int i = 0; i < resources.length; i++) {
            if (!InputValidator.isValidResourceId(resources[i].getResourceId())) {
                System.out.println("Invalid resource ID for index " + i);
                continue;
            }

            Printable printable = (Printable) resources[i];
            printable.printDetails();

            if (InputValidator.isValidOverdueDays(overdueDays[i])) {
                double fine = resources[i].calculateFine(overdueDays[i]);
                System.out.println("Overdue Days    : " + overdueDays[i]);
                System.out.println("Fine Amount     : Rs. " + fine);
            } else {
                System.out.println("Invalid overdue days entered");
            }

            System.out.println("-----------------------------------");
        }

        double totalFine = FineCalculator.getTotalFine(resources, overdueDays);
        System.out.println("Total Resources Created : " + LibraryResource.getTotalResources());
        System.out.println("Total Fine Collected    : Rs. " + totalFine);
    }
}
