/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.components.Login;
import controller.*;

public class LibaryView extends Application {
    public static memberController memberController;
    public static bookController bookController;
    public static loginController loginController;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management System");
        memberController = new memberController();
        bookController = new bookController();
        loginController = new loginController();

        // Buat login scene pertama kali
        Login login = new Login();
        primaryStage.setScene(new Scene(login.createLoginContent(primaryStage), 400, 400));
        primaryStage.setTitle("Library System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
