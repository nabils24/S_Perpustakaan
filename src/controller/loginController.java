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


/**
 * Class loginController - Deskripsi singkat mengenai kelas ini.
 */
public class loginController {

    private memberController memberController;

    public loginController() {
        this.memberController = new memberController();
    }

    public String login(String username, String password) {
        Admin admin = memberController.getAdminById(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return "Admin";
        }

        Mahasiswa mahasiswa = memberController.getMahasiswaById(username);
        if (mahasiswa != null && mahasiswa.getPassword().equals(password)) {
            return "Mahasiswa";
        }

        return "Invalid";
    }
}
