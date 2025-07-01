/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package model.management;


/**
 * Class LoginResponse - Deskripsi singkat mengenai kelas ini.
 */
public class LoginResponse {

    private String id;
    private String role;

    public LoginResponse(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "LoginResponse{id='" + id + "', role='" + role + "'}";
    }
}
