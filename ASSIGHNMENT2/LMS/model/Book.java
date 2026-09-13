package LMS.model;

public class Book extends LibraryResource implements Printable {
    private String genre;
    private int publicationYear;

    public Book(int resourceId, String title, String author, String genre, int publicationYear) {
        super(resourceId, title, author);
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    @Override
    public double calculateFine(int overdueDays) {
        double finePerDay = 0.50; // Example fine rate
        return overdueDays * finePerDay;
    }

    @Override
    public void printDetails() {
        System.out.println("Book ID: " + resourceId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Publication Year: " + publicationYear);
    }
    
}
