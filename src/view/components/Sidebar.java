/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package view.components;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {
    private Button adminButton;
    private Button mahasiswaButton;
    private Button authorButton;

    public Sidebar() {
        adminButton = new Button("Admin");
        mahasiswaButton = new Button("Mahasiswa");
        authorButton = new Button("Author");

        // Atur tata letak sidebar
        this.setSpacing(20);
        this.setAlignment(Pos.TOP_CENTER);

        // Tambahkan tombol ke sidebar
        this.getChildren().addAll(adminButton, mahasiswaButton, authorButton);
    }

    public Button getAdminButton() {
        return adminButton;
    }

    public Button getMahasiswaButton() {
        return mahasiswaButton;
    }

    public Button getAuthorButton() {
        return authorButton;
    }
}
