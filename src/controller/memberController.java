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

    public memberController() {
        // Menambahkan admin default
        addAdmin("admin123", "Admin", "Admin Address", "1234567890", "admin123");
        addMahasiswa("mahasiswa123", "Mahasiswa1", "MHS Address", "1234567890", "mhs123");
    }

    public void addMahasiswa(String studentId, String name, String address, String phoneNumber, String password) {
        Mahasiswa newMahasiswa = new Mahasiswa(studentId, name, address, phoneNumber, password);
        mahasiswaList.add(newMahasiswa);
    }

    public void addAdmin(String adminId, String name, String address, String phoneNumber, String password) {
        Admin newAdmin = new Admin(adminId, name, address, phoneNumber, password);
        adminList.add(newAdmin);
    }

    public void removeMahasiswa(String studentId) {
        mahasiswaList.removeIf(mahasiswa -> mahasiswa.getStudentId().equals(studentId));
    }

    public void removeAdmin(String adminId) {
        adminList.removeIf(admin -> admin.getAdminId().equals(adminId));
    }

    public ArrayList<Mahasiswa> getAllMahasiswa() {
        return mahasiswaList;
    }

    public ArrayList<Admin> getAllAdmins() {
        return adminList;
    }

    public Mahasiswa getMahasiswaById(String studentId) {
        for (Mahasiswa mahasiswa : mahasiswaList) {
            if (mahasiswa.getStudentId().equals(studentId)) {
                return mahasiswa;
            }
        }
        return null;
    }

    public Admin getAdminById(String adminId) {
        for (Admin admin : adminList) {
            if (admin.getAdminId().equals(adminId)) {
                return admin;
            }
        }
        return null;
    }

    // Menambahkan fungsi untuk mengecek total mahasiswa
    public int getTotalMahasiswa() {
        return mahasiswaList.size();
    }

    // Menambahkan fungsi untuk mengecek total admin
    public int getTotalAdmins() {
        return adminList.size();
    }
}
