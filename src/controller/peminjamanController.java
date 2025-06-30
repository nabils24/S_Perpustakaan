/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package controller;
import model.management.Peminjaman;
import model.management.Book;
import java.util.ArrayList;
import java.util.Date;


/**
 * Class peminjamanController - Deskripsi singkat mengenai kelas ini.
 */
public class peminjamanController {
    private ArrayList<Peminjaman> peminjamanList = new ArrayList<>();
    private ArrayList<Book> bookList = new ArrayList<>();  // Menyimpan semua buku

    // Menambah peminjaman
    public boolean addPeminjaman(String peminjamanId, String studentId, String bookCode, Date borrowDate, Date returnDate) {
        // Cek apakah mahasiswa sudah meminjam 3 buku
        int borrowedBooks = countBorrowedBooks(studentId);
        if (borrowedBooks >= 3) {
            System.out.println("Mahasiswa sudah meminjam 3 buku, tidak dapat meminjam lagi.");
            return false;
        }

        // Cek apakah buku tersedia dan disetujui oleh admin
        Book book = getBookByCode(bookCode);
        if (book != null && book.isAvailable() && book.isApproved()) {
            // Menambah peminjaman
            Peminjaman newPeminjaman = new Peminjaman(peminjamanId, studentId, bookCode, borrowDate, returnDate);
            peminjamanList.add(newPeminjaman);
            book.setAvailable(false);  // Set buku menjadi tidak tersedia
            System.out.println("Buku berhasil dipinjam.");
            return true;
        } else {
            System.out.println("Buku tidak tersedia atau belum disetujui.");
            return false;
        }
    }

    // Menghitung jumlah buku yang dipinjam oleh mahasiswa
    public int countBorrowedBooks(String studentId) {
        int count = 0;
        for (Peminjaman peminjaman : peminjamanList) {
            if (peminjaman.getStudentId().equals(studentId)) {
                count++;
            }
        }
        return count;
    }

    // Mendapatkan buku berdasarkan kode buku
    public Book getBookByCode(String bookCode) {
        for (Book book : bookList) {
            if (book.getBookCode().equals(bookCode)) {
                return book;
            }
        }
        return null;
    }

    // Menampilkan semua peminjaman
    public ArrayList<Peminjaman> getAllPeminjaman() {
        return peminjamanList;
    }

    // Menampilkan daftar buku yang tersedia
    public ArrayList<Book> getAvailableBooks() {
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    // Menambahkan buku ke daftar buku
    public void addBook(Book book) {
        bookList.add(book);
    }
}
