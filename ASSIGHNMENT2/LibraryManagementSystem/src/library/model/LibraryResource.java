package library.model;

public abstract class LibraryResource {

    private int resourceId;
    private String title;
    private String author;

    protected static String libraryName = "Central University Library";
    private static int objectCounter = 0;

    public LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;
        objectCounter++;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static String getLibraryName() {
        return libraryName;
    }

    public static int getTotalResources() {
        return objectCounter;
    }

    public abstract double calculateFine(int overdueDays);
}
