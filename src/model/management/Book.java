package model.management;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Class book - Deskripsi singkat mengenai kelas ini.
 */
public class Book {

    // Unique code for the book (e.g., ISBN, Library ID, etc.)
    private StringProperty bookCode;

    // Title of the book
    private StringProperty title;

    // Author ID for the book
    private StringProperty authorId;

    // Year in which the book was published
    private IntegerProperty yearPublished;

    // Indicates whether the book is approved by admin (for approval logic)
    private BooleanProperty isApproved;

    // Indicates whether the book is available for borrowing
    private BooleanProperty isAvailable;

    /**
     * Constructor to create a new Book object with specified details.
     * By default, the book is not approved and is available for borrowing.
     *
     * @param bookCode     The unique identifier for the book
     * @param title        The title of the book
     * @param authorId     The ID of the author who wrote the book
     * @param yearPublished The year the book was published
     */
    public Book(String bookCode, String title, String authorId, int yearPublished) {
        this.bookCode = new SimpleStringProperty(bookCode);
        this.title = new SimpleStringProperty(title);
        this.authorId = new SimpleStringProperty(authorId);
        this.yearPublished = new SimpleIntegerProperty(yearPublished);
        this.isApproved = new SimpleBooleanProperty(false);  // By default, the book is not approved
        this.isAvailable = new SimpleBooleanProperty(true);  // By default, the book is available for borrowing
    }

    // Getters and Setters

    public String getBookCode() {
        return bookCode.get();
    }

    public void setBookCode(String bookCode) {
        this.bookCode.set(bookCode);
    }

    public StringProperty bookCodeProperty() {
        return bookCode;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getAuthorId() {
        return authorId.get();
    }

    public void setAuthorId(String authorId) {
        this.authorId.set(authorId);
    }

    public StringProperty authorIdProperty() {
        return authorId;
    }

    public int getYearPublished() {
        return yearPublished.get();
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished.set(yearPublished);
    }

    public IntegerProperty yearPublishedProperty() {
        return yearPublished;
    }

    public boolean isApproved() {
        return isApproved.get();
    }

    public void setApproved(boolean approved) {
        isApproved.set(approved);
    }

    public BooleanProperty isApprovedProperty() {
        return isApproved;
    }

    public boolean isAvailable() {
        return isAvailable.get();
    }

    public void setAvailable(boolean available) {
        isAvailable.set(available);
    }

    public BooleanProperty isAvailableProperty() {
        return isAvailable;
    }

    /**
     * Returns a string representation of the book with its details.
     *
     * @return a string describing the book
     */
    @Override
    public String toString() {
        return "Book Code: " + bookCode.get() + ", Title: " + title.get() + ", Author ID: " + authorId.get() +
                ", Year: " + yearPublished.get() + ", Approved: " + isApproved.get() + ", Available: " + isAvailable.get();
    }
}
