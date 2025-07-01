/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */
package view.components;

import controller.bookController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import controller.loginController;
import controller.memberController;
import model.management.LoginResponse;
import model.user.Mahasiswa;
import model.user.Author;
import view.LibaryView;
import view.components.pages.admin.*;
import view.components.pages.mahasiswa.*;
import view.components.pages.author.*;

import java.util.Optional;



public class Login {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerMahasiswaButton;
    private Button registerAuthorButton;
    private loginController loginController;
    private memberController memberController;
    private bookController bookController;
    private TableView<Mahasiswa> mahasiswaTable;
    private TableView<Author> authorTable;
    private adminView adminScene;
    private mahasiswaView mahasiswaScene;
    private authorView authorScene;

    public Parent createLoginContent(Stage primaryStage) {
        // Initialize UI components
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        registerMahasiswaButton = new Button("Register Mahasiswa");
        registerAuthorButton = new Button("Register Author");

        // Use the controllers from MainApp
        loginController = LibaryView.loginController; // Assuming you have a static loginController in LibaryView
        memberController = LibaryView.memberController; // Assuming you have a static memberController in LibaryView
        bookController = LibaryView.bookController; // Assuming you have a static bookController in MainApp
        adminScene = new adminView(bookController, memberController); // Pass controllers to adminView
        mahasiswaScene = new mahasiswaView(bookController, memberController);
        authorScene = new authorView(bookController, memberController);
        // Set styles
        usernameField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        passwordField.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");
        loginButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        registerMahasiswaButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #2196F3; -fx-text-fill: white;");
        registerAuthorButton.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-background-color: #2196F3; -fx-text-fill: white;");

        // Create layout
        VBox loginLayout = new VBox(10);
        loginLayout.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0; -fx-alignment: center;");
        loginLayout.getChildren().addAll(
                new Label("Username"),
                usernameField,
                new Label("Password"),
                passwordField,
                loginButton,
                new Label("Don't have an account?"),
                registerMahasiswaButton,
                registerAuthorButton
        );

        // Login button action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            LoginResponse loginResult = LibaryView.loginController.login(username, password);
            String role = loginResult.getRole();
            if (role.equals("Admin")) {
                primaryStage.setScene(adminScene.createAdminScene(primaryStage));
            } else if (role.equals("Mahasiswa")) {
                primaryStage.setScene(mahasiswaScene.createMahasiswaScene(primaryStage, loginResult.getId()));
            } else if (role.equals("Author")) {
                primaryStage.setScene(authorScene.createAuthorScene(primaryStage, loginResult.getId()));
            } else {
                showAlert("Error", "Invalid Username or Password.", Alert.AlertType.ERROR);
            }
        });

        // Register Mahasiswa button action
        registerMahasiswaButton.setOnAction(e -> showRegisterMahasiswaDialog());

        // Register Author button action
        registerAuthorButton.setOnAction(e -> showRegisterAuthorDialog());

        return loginLayout; // Return the layout instead of creating a new Scene here
    }


    public void setMemberController(memberController controller) {
        this.memberController = controller;
    }

    public void setBookController(bookController controller) {
        this.bookController = controller;
    }

    private void showRegisterMahasiswaDialog() {
        Dialog<Mahasiswa> dialog = new Dialog<>();
        dialog.setTitle("Register Mahasiswa");
        dialog.setHeaderText("Enter Mahasiswa Details");

        ButtonType registerButtonType = new ButtonType("Register", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField();
        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField phoneField = new TextField();
        PasswordField passwordField = new PasswordField();

        grid.add(new Label("Student ID:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(addressField, 1, 2);
        grid.add(new Label("Phone:"), 0, 3);
        grid.add(phoneField, 1, 3);
        grid.add(new Label("Password:"), 0, 4);
        grid.add(passwordField, 1, 4);

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(studentIdField::requestFocus);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == registerButtonType) {
                return new Mahasiswa(
                        studentIdField.getText(),
                        nameField.getText(),
                        addressField.getText(),
                        phoneField.getText(),
                        passwordField.getText()
                );
            }
            return null;
        });

        Optional<Mahasiswa> result = dialog.showAndWait();
        result.ifPresent(mahasiswa -> {
            boolean success = memberController.addMahasiswa(
                    mahasiswa.getStudentId(),
                    mahasiswa.getName(),
                    mahasiswa.getAddress(),
                    mahasiswa.getPhoneNumber(),
                    mahasiswa.getPassword()
            );

            if (success) {
                showAlert("Success", "Mahasiswa registered successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to register Mahasiswa", Alert.AlertType.ERROR);
            }
        });
    }

    private void showRegisterAuthorDialog() {
        Dialog<Author> dialog = new Dialog<>();
        dialog.setTitle("Register Author");
        dialog.setHeaderText("Enter Author Details");

        ButtonType registerButtonType = new ButtonType("Register", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField authorIdField = new TextField();
        TextField nameField = new TextField();
        TextArea bioArea = new TextArea();
        bioArea.setPrefRowCount(3);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        grid.add(new Label("Author ID:"), 0, 0);
        grid.add(authorIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Biography:"), 0, 2);
        grid.add(bioArea, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordField, 1, 3);

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(authorIdField::requestFocus);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == registerButtonType) {
                return new Author(
                        authorIdField.getText(),
                        nameField.getText(),
                        bioArea.getText(),
                        passwordField.getText()

                );
            }
            return null;
        });

        Optional<Author> result = dialog.showAndWait();
        result.ifPresent(author -> {
            boolean success = memberController.addAuthor(
                    author.getAuthorId(),
                    author.getName(),
                    author.getBiography(),
                    author.getPassword()
            );

            if (success) {
                showAlert("Success", "Author registered successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to register Author", Alert.AlertType.ERROR);
            }
        });
    }


    private Scene createMahasiswaScene(Stage primaryStage) {
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

    private Scene createAuthorScene(Stage primaryStage) {
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

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
