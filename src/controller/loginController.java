/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package controller;


/**
 * Class loginController - Deskripsi singkat mengenai kelas ini.
 */
public class loginController {

    // Method untuk login yang memeriksa kredensial
    public boolean loginAdmin(String username, String password) {
        // Logika login bisa diperluas dengan pengecekan database atau list pengguna
        if (username.equals("admin") && password.equals("admin123")) {
            return true;  // Login berhasil
        }
        return false;  // Gagal login
    }
}
