/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package controller;

import model.management.Book;
import java.util.ArrayList;

/**
 * Class bookController - Mengelola operasi CRUD untuk buku.
 */
public class bookController {
    private ArrayList<Book> books = new ArrayList<>();

    public bookController() {
        // Menambahkan buku default
        addBook("BK001", "Java Programming for Beginners", "Nabil Sahsada Suratno", 2025);
    }

    // Fungsi untuk menambah buku baru (Create)
    public boolean addBook(String bookCode, String title, String authorId, int yearPublished) {
        Book newBook = new Book(bookCode, title, authorId, yearPublished);
        books.add(newBook);
        return true;
    }

    // Fungsi untuk menampilkan semua buku (Read)
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    // Fungsi untuk menampilkan buku yang disetujui (Read)
    public ArrayList<Book> getApprovedBooks() {
        ArrayList<Book> approvedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isApproved()) {
                approvedBooks.add(book);
            }
        }
        return approvedBooks;
    }

    // Fungsi untuk menampilkan buku yang belum disetujui (Read)
    public ArrayList<Book> getUnapprovedBooks() {
        ArrayList<Book> unapprovedBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isApproved()) {
                unapprovedBooks.add(book);
            }
        }
        return unapprovedBooks;
    }

    // Fungsi untuk mendapatkan buku berdasarkan bookCode (Read by Code)
    public Book getBookByCode(String bookCode) {
        for (Book book : books) {
            if (book.getBookCode().equals(bookCode)) {
                return book;
            }
        }
        return null;  // Return null jika buku tidak ditemukan
    }

    // Fungsi untuk menyetujui buku oleh admin (Update)
    public Boolean approveBook(String bookCode) {
        for (Book book : books) {
            if (book.getBookCode().equals(bookCode)) {
                book.setApproved(true);  // Mark the book as approved
                return true;
            }
        }
        return false;
    }

    // Fungsi untuk menolak buku (Update)
    public void rejectBook(String bookCode) {
        Book book = getBookByCode(bookCode);
        if (book != null) {
            book.setApproved(false);
        }
    }

    // Fungsi untuk mengupdate informasi buku (Update)
    public boolean updateBook(String bookCode, String newTitle, String newAuthorId, int newYearPublished) {
        Book bookToUpdate = getBookByCode(bookCode);
        if (bookToUpdate != null) {
            bookToUpdate.setTitle(newTitle);
            bookToUpdate.setAuthorId(newAuthorId);
            bookToUpdate.setYearPublished(newYearPublished);
            return true;  // Buku berhasil diperbarui
        }
        return false;  // Buku tidak ditemukan
    }

    // Fungsi untuk menghapus buku berdasarkan bookCode (Delete)
    public boolean removeBook(String bookCode) {
        Book bookToRemove = getBookByCode(bookCode);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            return true;  // Buku berhasil dihapus
        }
        return false;  // Buku tidak ditemukan
    }

    // Fungsi untuk menghitung total jumlah buku (Count)
    public int getTotalBooks() {
        return books.size();
    }

    // Fungsi untuk menghitung jumlah buku yang tersedia (Count)
    public int getAvailableBooks() {
        int availableCount = 0;
        for (Book book : books) {
            if (book.isAvailable()) {  // Memanggil metode isAvailable() dari kelas Book
                availableCount++;
            }
        }
        return availableCount;
    }
}



