/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package model.management;
import java.util.Date;

/**
 * Class peminjaman - Deskripsi singkat mengenai kelas ini.
 */
public class Peminjaman {
    private String peminjamanId;
    private String studentId;
    private String bookCode;
    private Date borrowDate;
    private Date returnDate;

    // Konstruktor
    public Peminjaman(String peminjamanId, String studentId, String bookCode, Date borrowDate, Date returnDate) {
        this.peminjamanId = peminjamanId;
        this.studentId = studentId;
        this.bookCode = bookCode;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public String getPeminjamanId() {
        return peminjamanId;
    }

    public void setPeminjamanId(String peminjamanId) {
        this.peminjamanId = peminjamanId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Peminjaman ID: " + peminjamanId + ", Student ID: " + studentId + ", Book Code: " + bookCode +
                ", Borrowed: " + borrowDate + ", Return: " + returnDate;
    }
}
