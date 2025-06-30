/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package model.management;


/**
 * Class book - Deskripsi singkat mengenai kelas ini.
 */
public class Book {

    // Unique code for the book (e.g., ISBN, Library ID, etc.)
    private String bookCode;

    // Title of the book
    private String title;

    // Author ID for the book
    private String authorId;

    // Year in which the book was published
    private int yearPublished;

    // Indicates whether the book is approved by admin (for approval logic)
    private boolean isApproved;

    // Indicates whether the book is available for borrowing
    private boolean isAvailable;

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
        this.bookCode = bookCode;
        this.title = title;
        this.authorId = authorId;
        this.yearPublished = yearPublished;
        this.isApproved = false;  // By default, the book is not approved
        this.isAvailable = true;  // By default, the book is available for borrowing
    }

    // Getters and Setters

    /**
     * @return the unique book code
     */
    public String getBookCode() {
        return bookCode;
    }

    /**
     * Sets the unique book code.
     *
     * @param bookCode the unique book code to set
     */
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    /**
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the title of the book to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author ID
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * Sets the author ID.
     *
     * @param authorId the author ID to set
     */
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    /**
     * @return the year the book was published
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Sets the year the book was published.
     *
     * @param yearPublished the year to set
     */
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    /**
     * @return whether the book has been approved by an admin
     */
    public boolean isApproved() {
        return isApproved;
    }

    /**
     * Sets the approval status of the book.
     *
     * @param approved true if the book is approved, false otherwise
     */
    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    /**
     * @return whether the book is available for borrowing
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the book.
     *
     * @param available true if the book is available for borrowing, false otherwise
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Returns a string representation of the book with its details.
     *
     * @return a string describing the book
     */
    @Override
    public String toString() {
        return "Book Code: " + bookCode + ", Title: " + title + ", Author ID: " + authorId + ", Year: " + yearPublished + ", Approved: " + isApproved + ", Available: " + isAvailable;
    }
}

