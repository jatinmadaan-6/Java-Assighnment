package library.model;

public class Book extends LibraryResource implements Printable {

    private static final double FINE_PER_DAY = 5.0;

    private String publisher;

    public Book(int resourceId, String title, String author, String publisher) {
        super(resourceId, title, author);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * FINE_PER_DAY;
    }

    @Override
    public void printDetails() {
        System.out.println("Resource Type   : Book");
        System.out.println("Resource ID     : " + getResourceId());
        System.out.println("Title           : " + getTitle());
        System.out.println("Author          : " + getAuthor());
        System.out.println("Publisher       : " + publisher);
        System.out.println("Library         : " + getLibraryName());
    }
}
