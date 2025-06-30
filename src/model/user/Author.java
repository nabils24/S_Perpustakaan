/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package model.user;
import java.util.ArrayList;

/**
 * Class Author - Deskripsi singkat mengenai kelas ini.
 */
public class Author {
    private String authorId;
    private String name;
    private String biography;
    private ArrayList<String> booksWritten;  // Daftar buku yang ditulis oleh penulis

    // Konstruktor
    public Author(String authorId, String name, String biography) {
        this.authorId = authorId;
        this.name = name;
        this.biography = biography;
        this.booksWritten = new ArrayList<>();
    }

    // Getters and Setters
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ArrayList<String> getBooksWritten() {
        return booksWritten;
    }

    public void setBooksWritten(ArrayList<String> booksWritten) {
        this.booksWritten = booksWritten;
    }

    // Menambah buku yang ditulis oleh penulis
    public void addBook(String bookTitle) {
        this.booksWritten.add(bookTitle);  // Menambah buku yang ditulis penulis
    }

    // Menampilkan informasi penulis
    public String toString() {
        return "Author ID: " + authorId + ", Name: " + name + ", Biography: " + biography + ", Books Written: " + booksWritten;
    }

}
