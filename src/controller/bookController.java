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
 * Class bookController - Deskripsi singkat mengenai kelas ini.
 */
public class bookController {
    private ArrayList<Book> books = new ArrayList<>();

    public bookController() {
        addBook("BK001", "Java Programming for Beginners", "Nabil Sahsada Suratno", 2025);
    }

    // Menambah buku yang diajukan oleh penulis
    public void addBook(String bookCode, String title, String authorId, int yearPublished) {
        Book newBook = new Book(bookCode, title, authorId, yearPublished);
        books.add(newBook);
    }

    // Menampilkan semua buku (termasuk yang belum disetujui)
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    // Menampilkan buku yang disetujui
    public ArrayList<Book> getApprovedBooks() {
        ArrayList<Book> approvedBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isApproved()) {
                approvedBooks.add(book);
            }
        }
        return approvedBooks;
    }

    // Menampilkan buku yang belum disetujui
    public ArrayList<Book> getUnapprovedBooks() {
        ArrayList<Book> unapprovedBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isApproved()) {
                unapprovedBooks.add(book);
            }
        }
        return unapprovedBooks;
    }

    // Menyetujuinya buku oleh admin
    public void approveBook(String bookCode) {
        for (Book book : books) {
            if (book.getBookCode().equals(bookCode)) {
                book.setApproved(true);
                break;
            }
        }
    }

    // Menolak buku
    public void rejectBook(String bookCode) {
        for (Book book : books) {
            if (book.getBookCode().equals(bookCode)) {
                book.setApproved(false);
                break;
            }
        }
    }

    // Menambahkan fungsi untuk mengecek total mahasiswa
    public int getTotalBooks() {
        return books.size();
    }

    //Count AvailableBooks
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
