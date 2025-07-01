/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package view.components.pages.admin;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

//pendukung component
import view.components.helper.*;

//pendukung controller
import controller.*;

//pendukung model
import model.user.Mahasiswa;

import java.util.Optional;

/**
 * Class adminView - Deskripsi singkat mengenai kelas ini.
 */
public class adminView {
    private SidebarButton sbButton;
    private statsCard statsCard;
    private memberController memberController;
    private bookController bookController;
    private TableView<Mahasiswa> mahasiswaTable;

    public Scene createAdminScene(Stage primaryStage) {
        // Main layout menggunakan BorderPane untuk manajemen tata letak yang lebih baik
        BorderPane mainLayout = new BorderPane();
        memberController = new memberController();
        bookController = new bookController();
        mainLayout.setStyle("-fx-background-color: #f5f7fa;");

        // Sidebar - menggunakan VBox dengan lebar yang ditentukan
        VBox sidebar = new VBox(15);
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20;");
        sidebar.setPrefWidth(250); // Lebar sidebar yang cukup

        // Logo/Header sidebar
        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER_LEFT);

        ImageView logo = new ImageView(new Image("assets/admin/AdminLogo.png"));
        logo.setFitWidth(40);
        logo.setFitHeight(40);

        Label titleLabel = new Label("Admin Panel");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: white;");

        headerBox.getChildren().addAll(logo, titleLabel);
        headerBox.setSpacing(10);

        // Menu items di sidebar
        Label menuTitle = new Label("MAIN MENU");
        menuTitle.setStyle("-fx-font-size: 12; -fx-text-fill: #7f8c8d; -fx-padding: 20 0 5 0;");

        Button manageMahasiswaBtn = sbButton.createSidebarButton("Manage Mahasiswa", "assets/admin/AdminLogo.png");
        Button manageBooksBtn = sbButton.createSidebarButton("Manage Books", "assets/admin/BookIcon.png");
        Button dashboardBtn = sbButton.createSidebarButton("Dashboard", "assets/admin/DashboardIcon.png");
        Button settingsBtn = sbButton.createSidebarButton("Settings", "assets/admin/Settingscon.png");

        // Area kosong untuk mendorong item logout ke bawah
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Logout button
        Button logoutBtn = new Button("Logout");
        logoutBtn.setGraphic(new ImageView(new Image("assets/admin/LogoutIcon.png", 20, 20, true, true)));
        logoutBtn.setContentDisplay(ContentDisplay.LEFT);
        logoutBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT;");
        logoutBtn.setOnMouseEntered(e -> logoutBtn.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT;"));
        logoutBtn.setOnMouseExited(e -> logoutBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT;"));

        sidebar.getChildren().addAll(headerBox, menuTitle, dashboardBtn, manageMahasiswaBtn, manageBooksBtn, spacer, settingsBtn, logoutBtn);

        // Main content area
        StackPane mainContent = new StackPane();
        mainContent.setStyle("-fx-padding: 30;");

        // Dashboard content contoh
        VBox dashboardContent = createDashboardContent();
        mainContent.getChildren().add(dashboardContent);

        // Button actions
        dashboardBtn.setOnAction(e -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(createDashboardContent());
        });

        manageMahasiswaBtn.setOnAction(e -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(createManageMahasiswaContent());
        });

        manageBooksBtn.setOnAction(e -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(createManageBooksContent());
        });

        settingsBtn.setOnAction(e -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(createSettingsContent());
        });

        // Set layout
        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(mainContent);

        // Atur ukuran window yang lebih besar
        Scene scene = new Scene(mainLayout, 1200, 700); // Lebar 1200, Tinggi 700
        scene.getStylesheets().add(getClass().getResource("/assets/admin/css/admin.css").toExternalForm());

        return scene;
    }

    private VBox createDashboardContent() {
        VBox dashboardContent = new VBox(20);
        dashboardContent.setAlignment(Pos.TOP_CENTER);

        Label welcomeLabel = new Label("Welcome to Admin Dashboard");
        welcomeLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        GridPane statsGrid = new GridPane();
        statsGrid.setHgap(20);
        statsGrid.setVgap(20);
        statsGrid.setAlignment(Pos.CENTER);

        // Contoh statistik cards
        statsGrid.add(statsCard.stats("Total Mahasiswa", memberController.getTotalMahasiswa(), "#3498db"), 0, 0);
        statsGrid.add(statsCard.stats("Total Books", bookController.getTotalBooks(), "#2ecc71"), 1, 0);
        statsGrid.add(statsCard.stats("Active Loans", 0, "#e74c3c"), 0, 1);
        statsGrid.add(statsCard.stats("Available Books", bookController.getAvailableBooks(), "#f39c12"), 1, 1);

        dashboardContent.getChildren().addAll(welcomeLabel, statsGrid);
        return dashboardContent;
    }

    private VBox createManageMahasiswaContent() {
        VBox manageMahasiswaContent = new VBox(20);
        manageMahasiswaContent.setAlignment(Pos.TOP_CENTER);
        manageMahasiswaContent.setStyle("-fx-padding: 20;");

        // Title
        Label titleLabel = new Label("Manage Students");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Create table
        mahasiswaTable = new TableView<>();
        setupMahasiswaTable();

        // Add buttons for CRUD operations
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        Button refreshButton = new Button("Refresh");
        refreshButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");

        // Add button action
        addButton.setOnAction(e -> showAddStudentDialog());

        refreshButton.setOnAction(e -> refreshMahasiswaTable());

        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, refreshButton);

        manageMahasiswaContent.getChildren().addAll(titleLabel, buttonBox, mahasiswaTable);
        return manageMahasiswaContent;
    }

    private void showAddStudentDialog() {
        // Create dialog
        Dialog<Mahasiswa> dialog = new Dialog<>();
        dialog.setTitle("Add New Student");
        dialog.setHeaderText("Enter student details");

        // Set dialog buttons
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField();
        studentIdField.setPromptText("Student ID");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        grid.add(new Label("Student ID:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(addressField, 1, 2);
        grid.add(new Label("Phone:"), 0, 3);
        grid.add(phoneNumberField, 1, 3);
        grid.add(new Label("Password:"), 0, 4);
        grid.add(passwordField, 1, 4);

        // Enable/disable add button depending on whether fields are filled
        Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
        addButton.setDisable(true);

        // Validation logic
        ChangeListener<String> fieldListener = (observable, oldValue, newValue) -> {
            boolean allFieldsFilled = !studentIdField.getText().trim().isEmpty()
                    && !nameField.getText().trim().isEmpty()
                    && !addressField.getText().trim().isEmpty()
                    && !phoneNumberField.getText().trim().isEmpty()
                    && !passwordField.getText().trim().isEmpty();
            addButton.setDisable(!allFieldsFilled);
        };

        studentIdField.textProperty().addListener(fieldListener);
        nameField.textProperty().addListener(fieldListener);
        addressField.textProperty().addListener(fieldListener);
        phoneNumberField.textProperty().addListener(fieldListener);
        passwordField.textProperty().addListener(fieldListener);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the ID field by default
        Platform.runLater(studentIdField::requestFocus);

        // Convert the result to a Mahasiswa object when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Mahasiswa(
                        studentIdField.getText(),
                        nameField.getText(),
                        addressField.getText(),
                        phoneNumberField.getText(),
                        passwordField.getText()
                );
            }
            return null;
        });

        Optional<Mahasiswa> result = dialog.showAndWait();

        result.ifPresent(student -> {
            // Call controller to add the student
            boolean success = memberController.addMahasiswa(
                    student.getStudentId(),
                    student.getName(),
                    student.getAddress(),
                    student.getPhoneNumber(),
                    student.getPassword()
            );

            if (success) {
                refreshMahasiswaTable();
                showAlert("Success", "Student added successfully!", Alert.AlertType.INFORMATION);
            }
        });
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void setupMahasiswaTable() {
        // Clear existing columns
        mahasiswaTable.getColumns().clear();

        // Create columns matching the Mahasiswa model properties
        TableColumn<Mahasiswa, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        idCol.setPrefWidth(120);

        TableColumn<Mahasiswa, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(150);

        TableColumn<Mahasiswa, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setPrefWidth(200);

        TableColumn<Mahasiswa, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneCol.setPrefWidth(120);

        // Password column - consider masking or omitting in production
        TableColumn<Mahasiswa, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordCol.setPrefWidth(100);
        passwordCol.setVisible(false); // Hidden by default for security

        // Add columns to table
        mahasiswaTable.getColumns().addAll(idCol, nameCol, addressCol, phoneCol, passwordCol);

        // Set column resize policy
        mahasiswaTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Add context menu to show/hide password column
        ContextMenu contextMenu = new ContextMenu();
        MenuItem togglePassword = new MenuItem("Toggle Password Visibility");
        togglePassword.setOnAction(e -> passwordCol.setVisible(!passwordCol.isVisible()));
        contextMenu.getItems().add(togglePassword);
        mahasiswaTable.setContextMenu(contextMenu);

        // Load data
        refreshMahasiswaTable();
    }

    private void refreshMahasiswaTable() {
        ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList(
                memberController.getAllMahasiswa()
        );
        mahasiswaTable.setItems(mahasiswaList);
    }

        private VBox createManageBooksContent() {
        VBox manageBooksContent = new VBox();
        // Add components for managing books here
        Label label = new Label("Manage Books Content");
        manageBooksContent.getChildren().add(label);
        return manageBooksContent;
    }

    private VBox createSettingsContent() {
        VBox settingsContent = new VBox();
        // Add components for settings here
        Label label = new Label("Settings Content");
        settingsContent.getChildren().add(label);
        return settingsContent;
    }
}

