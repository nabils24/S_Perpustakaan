/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package view.components;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.LibaryView;
import controller.loginController;

public class Login {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private loginController login;

    public Login(Stage primaryStage) {
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");

        VBox loginLayout = new VBox(10);
        loginLayout.getChildren().addAll(
                new Label("Username"), usernameField,
                new Label("Password"), passwordField,
                loginButton
        );

        // Login button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Authentication logic
            if (login.loginAdmin(username, password)) {
                // Redirect to Admin Page
                primaryStage.setScene(createAdminScene(primaryStage));
            } else if (username.equals("mahasiswa") && password.equals("mahasiswa")) {
                // Redirect to Mahasiswa Page
                primaryStage.setScene(createMahasiswaScene(primaryStage));
            } else if (username.equals("author") && password.equals("author")) {
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
        primaryStage.show();
    }

    public Scene createAdminScene(Stage primaryStage) {
        // Admin Page content
        VBox adminLayout = new VBox(20);
        adminLayout.getChildren().add(new Label("Welcome Admin"));

        // Admin-specific features here
        Button manageMahasiswaButton = new Button("Manage Mahasiswa");
        Button manageBooksButton = new Button("Manage Books");

        adminLayout.getChildren().addAll(manageMahasiswaButton, manageBooksButton);

        return new Scene(adminLayout, 400, 300);
    }

    public Scene createMahasiswaScene(Stage primaryStage) {
        // Mahasiswa Page content
        VBox mahasiswaLayout = new VBox(20);
        mahasiswaLayout.getChildren().add(new Label("Welcome Mahasiswa"));

        // Mahasiswa-specific features here
        Button borrowBookButton = new Button("Borrow Books");
        Button viewBooksButton = new Button("View Books");

        mahasiswaLayout.getChildren().addAll(borrowBookButton, viewBooksButton);

        return new Scene(mahasiswaLayout, 400, 300);
    }

    public Scene createAuthorScene(Stage primaryStage) {
        // Author Page content
        VBox authorLayout = new VBox(20);
        authorLayout.getChildren().add(new Label("Welcome Author"));

        // Author-specific features here
        Button addBookButton = new Button("Add New Book");
        Button viewBooksButton = new Button("View Your Books");

        authorLayout.getChildren().addAll(addBookButton, viewBooksButton);

        return new Scene(authorLayout, 400, 300);
    }
}
