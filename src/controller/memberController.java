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
import java.util.ArrayList;


/**
 * Class memberController - Deskripsi singkat mengenai kelas ini.
 */
public class memberController {

    private ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
    private ArrayList<Admin> adminList = new ArrayList<>();

    // Menambah mahasiswa
    public void addMahasiswa(String studentId, String name, String address, String phoneNumber, String password) {
        Mahasiswa newMahasiswa = new Mahasiswa(studentId, name, address, phoneNumber, password);
        mahasiswaList.add(newMahasiswa);
    }

    // Menambah admin
    public void addAdmin(String adminId, String name, String address, String phoneNumber) {
        Admin newAdmin = new Admin(adminId, name, address, phoneNumber);
        adminList.add(newAdmin);
    }

    // Menghapus mahasiswa
    public void removeMahasiswa(String studentId) {
        mahasiswaList.removeIf(mahasiswa -> mahasiswa.getStudentId().equals(studentId));
    }

    // Menghapus admin
    public void removeAdmin(String adminId) {
        adminList.removeIf(admin -> admin.getAdminId().equals(adminId));
    }

    // Menampilkan semua mahasiswa
    public ArrayList<Mahasiswa> getAllMahasiswa() {
        return mahasiswaList;
    }

    // Menampilkan semua admin
    public ArrayList<Admin> getAllAdmins() {
        return adminList;
    }

    // Mencari mahasiswa berdasarkan ID
    public Mahasiswa getMahasiswaById(String studentId) {
        for (Mahasiswa mahasiswa : mahasiswaList) {
            if (mahasiswa.getStudentId().equals(studentId)) {
                return mahasiswa;
            }
        }
        return null;
    }

    // Mencari admin berdasarkan ID
    public Admin getAdminById(String adminId) {
        for (Admin admin : adminList) {
            if (admin.getAdminId().equals(adminId)) {
                return admin;
            }
        }
        return null;
    }
}
