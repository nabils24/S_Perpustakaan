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
 * Class libaryController - Deskripsi singkat mengenai kelas ini.
 */
public class libController {
    private ArrayList<Book> books = new ArrayList<>();
    private memberController memberController;
    private peminjamanController peminjamanController; // Menghubungkan ke PeminjamanController

    public libController() {
        this.memberController = new memberController();  // Menghubungkan dengan MemberController
        this.peminjamanController = new peminjamanController();
    }

    // Mengakses MemberController untuk pengelolaan mahasiswa dan admin
    public memberController getMemberController() {
        return memberController;
    }

    public peminjamanController getPeminjamanController() {
        return peminjamanController;
    }

}
