/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package model.user;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Author {
    private StringProperty authorId;
    private StringProperty name;
    private StringProperty biography;
    private ListProperty<String> booksWritten;  // Observable list for books written by the author

    // Constructor
    public Author(String authorId, String name, String biography) {
        this.authorId = new SimpleStringProperty(authorId);
        this.name = new SimpleStringProperty(name);
        this.biography = new SimpleStringProperty(biography);
        this.booksWritten = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
    }

    // Getters and Setters for StringProperties
    public StringProperty authorIdProperty() {
        return authorId;
    }

    public String getAuthorId() {
        return authorId.get();
    }

    public void setAuthorId(String authorId) {
        this.authorId.set(authorId);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty biographyProperty() {
        return biography;
    }

    public String getBiography() {
        return biography.get();
    }

    public void setBiography(String biography) {
        this.biography.set(biography);
    }

    // Getter and Setter for ListProperty (books written)
    public ListProperty<String> booksWrittenProperty() {
        return booksWritten;
    }

    public ObservableList<String> getBooksWritten() {
        return booksWritten.get();
    }

    public void setBooksWritten(ObservableList<String> booksWritten) {
        this.booksWritten.set(booksWritten);
    }

    // Method to add a book to the booksWritten list
    public void addBook(String bookTitle) {
        this.booksWritten.add(bookTitle);  // Adding the book to the list
    }

    // Method to remove a book from the booksWritten list
    public void removeBook(String bookTitle) {
        this.booksWritten.remove(bookTitle);  // Removing the book from the list
    }

    // Method to convert author to string representation
    @Override
    public String toString() {
        return "Author ID: " + getAuthorId() + ", Name: " + getName() + ", Biography: " + getBiography() + ", Books Written: " + getBooksWritten();
    }
}

