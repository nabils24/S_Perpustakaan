/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package view.components.helper;


import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Class createSidebarButton - Deskripsi singkat mengenai kelas ini.
 */
public class SidebarButton {

    // Constructor
    public static Button createSidebarButton(String text, String iconPath) {
        // Inisialisasi tombol
        Button btn = new Button(text);
        btn.setGraphic(new ImageView(new Image(iconPath, 20, 20, true, true)));
        btn.setContentDisplay(ContentDisplay.LEFT);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT; -fx-min-width: 200;");

        // Efek hover saat mouse masuk dan keluar
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT; -fx-min-width: 200;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT; -fx-min-width: 200;"));

        return btn;
    }
}
