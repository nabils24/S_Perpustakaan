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
import model.user.Author;
import view.LibaryView;
import model.management.LoginResponse;  // Import LoginResponse

/**
 * Class loginController - Deskripsi singkat mengenai kelas ini.
 */
public class loginController {

    private memberController memberController;

    public loginController() {
        this.memberController = new memberController();
    }

    /**
     * Fungsi untuk login, mengembalikan LoginResponse yang berisi ID dan Role.
     *
     * @param username
     * @param password
     * @return LoginResponse berisi ID dan Role pengguna, atau null jika login gagal.
     */
    public LoginResponse login(String username, String password) {
        // Cek Admin
        Admin admin = LibaryView.memberController.getAdminById(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return new LoginResponse(admin.getAdminId(), "Admin");
        }

        // Cek Mahasiswa
        Mahasiswa mahasiswa = LibaryView.memberController.getMahasiswaById(username);
        if (mahasiswa != null && mahasiswa.getPassword().equals(password)) {
            return new LoginResponse(mahasiswa.getStudentId(), "Mahasiswa");
        }

        // Cek Author
        Author author = LibaryView.memberController.getAuthorById(username);
        if (author != null && author.getPassword().equals(password)) {
            return new LoginResponse(author.getAuthorId(), "Author");
        }

        // Jika tidak ada yang cocok, return null
        return null;  // Login gagal
    }
}
