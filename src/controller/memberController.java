/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package controller;

import model.user.Mahasiswa;
import model.user.Admin;
import model.user.Author;  // Pastikan Anda memiliki kelas Author
import java.util.ArrayList;

public class memberController {

    private ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
    private ArrayList<Admin> adminList = new ArrayList<>();
    private ArrayList<Author> authorList = new ArrayList<>();  // List untuk Author

    public memberController() {
        // Menambahkan data default
        addAdmin("admin123", "Admin", "Admin Address", "1234567890", "admin123");
        addMahasiswa("mhs123", "Mahasiswa1", "MHS Address", "1234567890", "mhs123");
        addAuthor("author123", "Author1", "This is the biography of Author1.", "author123");
    }

    // Fungsi untuk menambahkan Mahasiswa baru (Create)
    public boolean addMahasiswa(String studentId, String name, String address, String phoneNumber, String password) {
        Mahasiswa newMahasiswa = new Mahasiswa(studentId, name, address, phoneNumber, password);
        mahasiswaList.add(newMahasiswa);
        return true;
    }

    // Fungsi untuk menambahkan Admin baru (Create)
    public void addAdmin(String adminId, String name, String address, String phoneNumber, String password) {
        Admin newAdmin = new Admin(adminId, name, address, phoneNumber, password);
        adminList.add(newAdmin);
    }

    // Fungsi untuk menambahkan Author baru (Create)
    public boolean addAuthor(String authorId, String name, String biography, String password) {
        Author newAuthor = new Author(authorId, name, biography, password);
        authorList.add(newAuthor);
        return true;
    }

    // Fungsi untuk mendapatkan Mahasiswa berdasarkan studentId (Read)
    public Mahasiswa getMahasiswaById(String studentId) {
        for (Mahasiswa mahasiswa : mahasiswaList) {
            if (mahasiswa.getStudentId().equals(studentId)) {
                return mahasiswa;
            }
        }
        return null;
    }

    // Fungsi untuk mendapatkan Admin berdasarkan adminId (Read)
    public Admin getAdminById(String adminId) {
        for (Admin admin : adminList) {
            if (admin.getAdminId().equals(adminId)) {
                return admin;
            }
        }
        return null;
    }

    // Fungsi untuk mendapatkan Author berdasarkan authorId (Read)
    public Author getAuthorById(String authorId) {
        for (Author author : authorList) {
            if (author.getAuthorId().equals(authorId)) {
                return author;
            }
        }
        return null;
    }

    // Fungsi untuk mengupdate data Mahasiswa (Update)
    public boolean updateMahasiswa(String studentId, String newName, String newAddress, String newPhoneNumber, String newPassword) {
        Mahasiswa mahasiswaToUpdate = getMahasiswaById(studentId);
        if (mahasiswaToUpdate != null) {
            mahasiswaToUpdate.setName(newName);
            mahasiswaToUpdate.setAddress(newAddress);
            mahasiswaToUpdate.setPhoneNumber(newPhoneNumber);
            mahasiswaToUpdate.setPassword(newPassword);
            return true;
        }
        return false;
    }

    // Fungsi untuk mengupdate data Admin (Update)
    public boolean updateAdmin(String adminId, String newName, String newAddress, String newPhoneNumber, String newPassword) {
        Admin adminToUpdate = getAdminById(adminId);
        if (adminToUpdate != null) {
            adminToUpdate.setName(newName);
            adminToUpdate.setAddress(newAddress);
            adminToUpdate.setPhoneNumber(newPhoneNumber);
            adminToUpdate.setPassword(newPassword);
            return true;
        }
        return false;
    }

    // Fungsi untuk mengupdate data Author (Update)
    public boolean updateAuthor(String authorId, String newName, String newBiography, String password) {
        Author authorToUpdate = getAuthorById(authorId);
        if (authorToUpdate != null) {
            authorToUpdate.setName(newName);
            authorToUpdate.setBiography(newBiography);
            authorToUpdate.setPassword(password);
            return true;
        }
        return false;
    }

    // Fungsi untuk menghapus Mahasiswa berdasarkan studentId (Delete)
    public boolean removeMahasiswa(String studentId) {
        Mahasiswa mahasiswaToRemove = getMahasiswaById(studentId);
        if (mahasiswaToRemove != null) {
            mahasiswaList.remove(mahasiswaToRemove);
            return true;
        }
        return false;
    }

    // Fungsi untuk menghapus Admin berdasarkan adminId (Delete)
    public boolean removeAdmin(String adminId) {
        Admin adminToRemove = getAdminById(adminId);
        if (adminToRemove != null) {
            adminList.remove(adminToRemove);
            return true;
        }
        return false;
    }

    // Fungsi untuk menghapus Author berdasarkan authorId (Delete)
    public boolean removeAuthor(String authorId) {
        Author authorToRemove = getAuthorById(authorId);
        if (authorToRemove != null) {
            authorList.remove(authorToRemove);
            return true;
        }
        return false;
    }

    // Fungsi untuk mendapatkan semua Mahasiswa (Read)
    public ArrayList<Mahasiswa> getAllMahasiswa() {
        return mahasiswaList;
    }

    // Fungsi untuk mendapatkan semua Admin (Read)
    public ArrayList<Admin> getAllAdmins() {
        return adminList;
    }

    // Fungsi untuk mendapatkan semua Author (Read)
    public ArrayList<Author> getAllAuthors() {
        return authorList;
    }

    // Fungsi untuk menghitung total Mahasiswa (Count)
    public int getTotalMahasiswa() {
        return mahasiswaList.size();
    }

    // Fungsi untuk menghitung total Admin (Count)
    public int getTotalAdmins() {
        return adminList.size();
    }

    // Fungsi untuk menghitung total Author (Count)
    public int getTotalAuthors() {
        return authorList.size();
    }
}

