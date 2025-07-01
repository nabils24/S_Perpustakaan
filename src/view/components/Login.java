/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */
package view.components;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import controller.loginController;
import controller.memberController;

//Import Pages
import view.components.pages.admin.*;

//Import Component Pendukung
import view.components.helper.*;

public class Login {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private loginController loginController;
    private memberController memberController;
    private SidebarButton sbButton;
    private statsCard statsCard;
    private adminView adminScene;


    public Login(Stage primaryStage) {
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        adminScene = new adminView();

        loginController = new loginController(); // Initialize the login controller
        memberController = new memberController();

        // Set styles
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        loginButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");

        VBox loginLayout = new VBox(10);
        loginLayout.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0; -fx-alignment: center;");
        loginLayout.getChildren().addAll(
                new Label("Username"),
                usernameField,
                new Label("Password"),
                passwordField,
                loginButton
        );

        // Login button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Authentication logic
            String loginResult = loginController.login(username, password);

            if (loginResult.equals("Admin")) {
                // Redirect to Admin Page
                primaryStage.setScene(adminScene.createAdminScene(primaryStage));
            } else if (loginResult.equals("Mahasiswa")) {
                // Redirect to Mahasiswa Page
                primaryStage.setScene(createMahasiswaScene(primaryStage));
            } else if (loginResult.equals("Author")) {
                // Redirect to Author Page
                primaryStage.setScene(createAuthorScene(primaryStage));
            } else {
                // Show error if login fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Username or Password.");
                alert.show();
            }
        });

        Scene loginScene = new Scene(loginLayout, 400, 300);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }




    public Scene createMahasiswaScene(Stage primaryStage) {
        VBox mahasiswaLayout = new VBox(20);
        mahasiswaLayout.setStyle("-fx-padding: 20; -fx-background-color: #e0e0e0; -fx-alignment: center;");
        mahasiswaLayout.getChildren().add(new Label("Welcome Mahasiswa"));

        Button borrowBookButton = new Button("Borrow Books");
        Button viewBooksButton = new Button("View Books");

        borrowBookButton.setStyle("-fx-padding: 10px; -fx-background-color: #2196F3; -fx-text-fill: white;");
        viewBooksButton.setStyle("-fx-padding: 10px; -fx-background-color: #2196F3; -fx-text-fill: white;");

        mahasiswaLayout.getChildren().addAll(borrowBookButton, viewBooksButton);

        return new Scene(mahasiswaLayout, 400, 300);
    }

    public Scene createAuthorScene(Stage primaryStage) {
        VBox authorLayout = new VBox(20);
        authorLayout.setStyle("-fx-padding: 20; -fx-background-color: #e0e0e0; -fx-alignment: center;");
        authorLayout.getChildren().add(new Label("Welcome Author"));

        Button addBookButton = new Button("Add New Book");
        Button viewBooksButton = new Button("View Your Books");

        addBookButton.setStyle("-fx-padding: 10px; -fx-background-color: #2196F3; -fx-text-fill: white;");
        viewBooksButton.setStyle("-fx-padding: 10px; -fx-background-color: #2196F3; -fx-text-fill: white;");

        authorLayout.getChildren().addAll(addBookButton, viewBooksButton);

        return new Scene(authorLayout, 400, 300);
    }
}

