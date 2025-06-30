/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package model.management;

import java.util.ArrayList;

/**
 * Class libary - Deskripsi singkat mengenai kelas ini.
 */
public class Libary {
    private ArrayList<Book> books = new ArrayList<>();

    public void addbook(Book book) {
        books.add(book);
    }

    public void removebook(String bookCode) {
        books.removeIf(book -> book.getBookCode().equals(bookCode));
    }

    public Book getbook(String bookCode) {
        for (Book book : books) {
            if (book.getBookCode().equals(bookCode)) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> getAllBooks() {
        return books;
    }
}
