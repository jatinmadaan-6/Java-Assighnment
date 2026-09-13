package LMS.model;

public abstract class LibraryResource {
    int resourceId;
    String title;
    String author;
    static String libraryName = "Central University Library";

    LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;
    }

    public abstract double calculateFine(int overdueDays);

}
