/*
 * ===========================================
 * Author   : Nabil Sahsada Suratno
 * Mode     : Java Main Class
 * Nim      : 202410370110357
 * ===========================================
 */

package view.components.pages.mahasiswa;

import controller.bookController;
import controller.memberController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.management.Book;
import model.user.Author;
import model.user.Mahasiswa;
import view.components.Login;
import view.components.helper.SidebarButton;
import view.components.helper.statsCard;

import java.util.List;
import java.util.Optional;

/**
 * Class adminView - Deskripsi singkat mengenai kelas ini.
 */
public class mahasiswaView {
    private SidebarButton sbButton;
    private statsCard statsCard;
    private memberController memberController;
    private bookController bookController;
    private TableView<Mahasiswa> mahasiswaTable;
    private TableView<Book> bookTable;
    private TableView<Author> authorTable;
    private String idMhs;


    public mahasiswaView(bookController bookController, memberController memberController) {
        this.bookController = bookController;
        this.memberController = memberController;
    }

    public Scene createMahasiswaScene(Stage primaryStage, String id) {
        // Main layout menggunakan BorderPane untuk manajemen tata letak yang lebih baik
        BorderPane mainLayout = new BorderPane();
        primaryStage.setTitle("Mahasiswa");
        this.idMhs = id;
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

        Label titleLabel = new Label("Halo Mahhasiswa");
        titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: white;");

        headerBox.getChildren().addAll(logo, titleLabel);
        headerBox.setSpacing(10);

        // Menu items di sidebar
        Label menuTitle = new Label("MAIN MENU");
        menuTitle.setStyle("-fx-font-size: 12; -fx-text-fill: #7f8c8d; -fx-padding: 20 0 5 0;");

        Button manageMahasiswaBtn = sbButton.createSidebarButton("Manage Borrow", "assets/admin/AdminLogo.png");
        Button manageAuthorBtn = sbButton.createSidebarButton("Manage Author", "assets/admin/AdminLogo.png");
        Button manageBooksBtn = sbButton.createSidebarButton("Manage Books", "assets/admin/BookIcon.png");
        Button dashboardBtn = sbButton.createSidebarButton("Dashboard", "assets/admin/DashboardIcon.png");

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

        // Logout button action
        logoutBtn.setOnAction(e -> {
            // Assuming you have a method to create the login scene, switch to the login scene here
            Stage stage = (Stage) logoutBtn.getScene().getWindow();
            Login login = new Login();
            stage.setScene(new Scene(login.createLoginContent(primaryStage), 400, 400));

        });

        sidebar.getChildren().addAll(headerBox, menuTitle, dashboardBtn, manageMahasiswaBtn, manageBooksBtn, manageAuthorBtn, spacer,  logoutBtn);

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

        manageAuthorBtn.setOnAction(e -> {
            mainContent.getChildren().clear();
            mainContent.getChildren().add(createManageAuthorsContent());
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
        String name = memberController.getMahasiswaById(idMhs).getName();
        Label welcomeLabel = new Label("Halo selamat datang kak " +name);
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


    /////////MAHASISWA PAGE\\\\\\\\\\
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
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button refreshButton = new Button("Refresh");

        // Styling buttons
        addButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        editButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        refreshButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");

        // Add button action
        addButton.setOnAction(e -> showAddStudentDialog());

        // Edit button action
        editButton.setOnAction(e -> {
            Mahasiswa selected = mahasiswaTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showEditStudentDialog(selected);
            } else {
                showAlert("Warning", "Pilih mahasiswa terlebih dahulu", Alert.AlertType.WARNING);
            }
        });

        // Delete button action
        deleteButton.setOnAction(e -> {
            Mahasiswa selected = mahasiswaTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showDeleteConfirmationDialog(selected);
            } else {
                showAlert("Warning", "Pilih mahasiswa terlebih dahulu", Alert.AlertType.WARNING);
            }
        });

        refreshButton.setOnAction(e -> refreshMahasiswaTable());

        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, refreshButton);
        manageMahasiswaContent.getChildren().addAll(titleLabel, buttonBox, mahasiswaTable);
        return manageMahasiswaContent;
    }

    //Add
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

    //Edit
    private void showEditStudentDialog(Mahasiswa student) {
        // Create dialog
        Dialog<Mahasiswa> dialog = new Dialog<>();
        dialog.setTitle("Edit Student");
        dialog.setHeaderText("Update student details");

        // Set dialog buttons
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create form
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField(student.getStudentId());
        studentIdField.setPromptText("Student ID");
        studentIdField.setDisable(true); // Disable editing of Student ID
        TextField nameField = new TextField(student.getName());
        nameField.setPromptText("Name");
        TextField addressField = new TextField(student.getAddress());
        addressField.setPromptText("Address");
        TextField phoneNumberField = new TextField(student.getPhoneNumber());
        phoneNumberField.setPromptText("Phone Number");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("New Password (leave blank to keep current)");

        grid.add(new Label("Student ID:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(addressField, 1, 2);
        grid.add(new Label("Phone:"), 0, 3);
        grid.add(phoneNumberField, 1, 3);
        grid.add(new Label("New Password:"), 0, 4);
        grid.add(passwordField, 1, 4);

        // Enable/disable update button depending on whether fields are filled
        Node updateButton = dialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setDisable(true);

        // Validation logic
        ChangeListener<String> fieldListener = (observable, oldValue, newValue) -> {
            boolean allFieldsFilled = !nameField.getText().trim().isEmpty()
                    && !addressField.getText().trim().isEmpty()
                    && !phoneNumberField.getText().trim().isEmpty();
            updateButton.setDisable(!allFieldsFilled);
        };

        nameField.textProperty().addListener(fieldListener);
        addressField.textProperty().addListener(fieldListener);
        phoneNumberField.textProperty().addListener(fieldListener);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the name field by default
        Platform.runLater(nameField::requestFocus);

        // Convert the result to a Mahasiswa object when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return new Mahasiswa(
                        studentIdField.getText(),
                        nameField.getText(),
                        addressField.getText(),
                        phoneNumberField.getText(),
                        passwordField.getText() // Password can be empty
                );
            }
            return null;
        });

        Optional<Mahasiswa> result = dialog.showAndWait();

        result.ifPresent(updatedStudent -> {
            // Call controller to update the student
            boolean success = memberController.updateMahasiswa(
                    updatedStudent.getStudentId(),
                    updatedStudent.getName(),
                    updatedStudent.getAddress(),
                    updatedStudent.getPhoneNumber(),
                    updatedStudent.getPassword()
            );

            if (success) {
                refreshMahasiswaTable();
                showAlert("Success", "Student updated successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to update student. Student ID may not exist.", Alert.AlertType.ERROR);
            }
        });
    }

    //Delete
    private void showDeleteConfirmationDialog(Mahasiswa mahasiswa) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus");
        alert.setHeaderText("Hapus Data Mahasiswa");
        alert.setContentText("Apakah Anda yakin ingin menghapus mahasiswa:\n" +
                "NIM: " + mahasiswa.getStudentId() + "\n" +
                "Nama: " + mahasiswa.getName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = memberController.removeMahasiswa(mahasiswa.getStudentId());

            if (isDeleted) {
                showAlert("Berhasil", "Data mahasiswa berhasil dihapus", Alert.AlertType.INFORMATION);
                refreshMahasiswaTable();
            } else {
                showAlert("Gagal", "Gagal menghapus data mahasiswa", Alert.AlertType.ERROR);
            }
        }
    }

    //Setup Table
    private void setupMahasiswaTable() {
        // Clear existing columns
        mahasiswaTable.getColumns().clear();

        // Create columns matching the Mahasiswa model properties using Property methods
        TableColumn<Mahasiswa, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());  // Use studentIdProperty
        idCol.setPrefWidth(120);

        TableColumn<Mahasiswa, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());  // Use nameProperty
        nameCol.setPrefWidth(150);

        TableColumn<Mahasiswa, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(cellData -> cellData.getValue().addressProperty());  // Use addressProperty
        addressCol.setPrefWidth(200);

        TableColumn<Mahasiswa, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());  // Use phoneNumberProperty
        phoneCol.setPrefWidth(120);

        // Password column - consider masking or omitting in production
        TableColumn<Mahasiswa, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());  // Use passwordProperty
        passwordCol.setPrefWidth(100);
        passwordCol.setVisible(false);  // Hidden by default for security

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

    //Refresh
    private void refreshMahasiswaTable() {
        // Bersihkan selection untuk menghindari konflik
        mahasiswaTable.getSelectionModel().clearSelection();

        // Ambil data terbaru dari controller
        List<Mahasiswa> latestData = memberController.getAllMahasiswa();

        // Update ObservableList
        ObservableList<Mahasiswa> data = FXCollections.observableArrayList(latestData);
        mahasiswaTable.setItems(data);

        // Scroll ke atas tabel setelah refresh
        mahasiswaTable.scrollTo(0);
    }

    //Alert
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    ///////Author Page\\\\\\\
    private VBox createManageAuthorsContent() {
        VBox manageAuthorsContent = new VBox(20);
        manageAuthorsContent.setAlignment(Pos.TOP_CENTER);
        manageAuthorsContent.setStyle("-fx-padding: 20;");

        // Title
        Label titleLabel = new Label("Manage Authors");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Create table
        TableView<Author> authorsTable = new TableView<>();
        setupAuthorsTable(authorsTable);

        // Add buttons for CRUD operations
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button refreshButton = new Button("Refresh");

        // Styling buttons
        addButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        editButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        refreshButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");

        // Add button action
        addButton.setOnAction(e -> showAddAuthorDialog(authorsTable));

        // Edit button action
        editButton.setOnAction(e -> {
            Author selected = authorsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showEditAuthorDialog(authorsTable, selected);
            } else {
                showAlert("Warning", "Please select an author to edit.", Alert.AlertType.WARNING);
            }
        });

        // Delete button action
        deleteButton.setOnAction(e -> {
            Author selected = authorsTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showDeleteConfirmationDialog(authorsTable, selected);
            } else {
                showAlert("Warning", "Please select an author to delete.", Alert.AlertType.WARNING);
            }
        });

        refreshButton.setOnAction(e -> refreshAuthorsTable(authorsTable));

        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, refreshButton);
        manageAuthorsContent.getChildren().addAll(titleLabel, buttonBox, authorsTable);
        return manageAuthorsContent;
    }

    private void setupAuthorsTable(TableView<Author> authorsTable) {
        // Clear existing columns
        authorsTable.getColumns().clear();

        // Create columns matching the Author model properties
        TableColumn<Author, String> idCol = new TableColumn<>("Author ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().authorIdProperty());
        idCol.setPrefWidth(120);

        TableColumn<Author, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nameCol.setPrefWidth(150);

        TableColumn<Author, String> bioCol = new TableColumn<>("Biography");
        bioCol.setCellValueFactory(cellData -> cellData.getValue().biographyProperty());
        bioCol.setPrefWidth(200);

        // Create a column for books written, display books as a comma-separated string
        TableColumn<Author, String> booksCol = new TableColumn<>("Books Written");
        booksCol.setCellValueFactory(cellData -> {
            ObservableList<String> books = cellData.getValue().getBooksWritten();
            return new SimpleStringProperty(String.join(", ", books));  // Join books into a single string
        });
        booksCol.setPrefWidth(200);

        // Password column - consider masking or omitting in production
        TableColumn<Author, String> passwordCol = new TableColumn<>("Password");
        passwordCol.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());  // Use passwordProperty
        passwordCol.setPrefWidth(100);
        passwordCol.setVisible(false);

        // Add columns to table
        authorsTable.getColumns().addAll(idCol, nameCol, bioCol, booksCol, passwordCol);

        // Set column resize policy
        authorsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Load data
        refreshAuthorsTable(authorsTable);
    }


    private void refreshAuthorsTable(TableView<Author> authorsTable) {
        // Clear selection to avoid conflicts
        authorsTable.getSelectionModel().clearSelection();

        // Get the latest data from the controller
        List<Author> latestData = memberController.getAllAuthors();

        // Update ObservableList
        ObservableList<Author> data = FXCollections.observableArrayList(latestData);
        authorsTable.setItems(data);

        // Scroll to the top of the table after refresh
        authorsTable.scrollTo(0);
    }
    private void showAddAuthorDialog(TableView<Author> authorsTable) {
        Dialog<Author> dialog = new Dialog<>();
        dialog.setTitle("Add New Author");
        dialog.setHeaderText("Enter author details");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField authorIdField = new TextField();
        authorIdField.setPromptText("Author ID");
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField bioField = new TextField();
        bioField.setPromptText("Biography");
        TextField booksField = new TextField();
        booksField.setPromptText("Books Written (comma separated)");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");


        grid.add(new Label("Author ID:"), 0, 0);
        grid.add(authorIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Biography:"), 0, 2);
        grid.add(bioField, 1, 2);
        grid.add(new Label("Books Written:"), 0, 3);
        grid.add(booksField, 1, 3);
        grid.add(new Label("Password:"), 0, 4);
        grid.add(passwordField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the ID field by default
        Platform.runLater(authorIdField::requestFocus);

        // Convert the result to an Author object when the add button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Author(
                        authorIdField.getText(),
                        nameField.getText(),
                        bioField.getText(),
                        passwordField.getText()
                );
            }
            return null;
        });

        Optional<Author> result = dialog.showAndWait();

        result.ifPresent(author -> {
            // Call controller to add the author
            boolean success = memberController.addAuthor(author.getAuthorId(), author.getName(), author.getBiography(), author.getPassword());
            if (success) {
                refreshAuthorsTable(authorsTable);
                showAlert("Success", "Author added successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to add the author. Author ID may already exist.", Alert.AlertType.ERROR);
            }
        });
    }

    private void showEditAuthorDialog(TableView<Author> authorsTable, Author author) {
        Dialog<Author> dialog = new Dialog<>();
        dialog.setTitle("Edit Author");
        dialog.setHeaderText("Update author details");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField authorIdField = new TextField(author.getAuthorId());
        authorIdField.setDisable(true); // Disable editing of Author ID
        TextField nameField = new TextField(author.getName());
        TextField bioField = new TextField(author.getBiography());
        TextField booksField = new TextField(String.join(", ", author.getBooksWritten()));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("New Password (leave blank to keep current)");

        grid.add(new Label("Author ID:"), 0, 0);
        grid.add(authorIdField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Biography:"), 0, 2);
        grid.add(bioField, 1, 2);
        grid.add(new Label("Books Written:"), 0, 3);
        grid.add(booksField, 1, 3);
        grid.add(new Label("New Password:"), 0, 4);
        grid.add(passwordField, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Request focus on the name field by default
        Platform.runLater(nameField::requestFocus);

        // Convert the result to an Author object when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return new Author(
                        author.getAuthorId(), // Keep the same author ID
                        nameField.getText(),
                        bioField.getText(),
                        passwordField.getText()
                );
            }
            return null;
        });

        Optional<Author> result = dialog.showAndWait();

        result.ifPresent(updatedAuthor -> {
            // Call controller to update the author
            boolean success = memberController.updateAuthor(updatedAuthor.getAuthorId(), updatedAuthor.getName(), updatedAuthor.getBiography(), updatedAuthor.getPassword());
            if (success) {
                refreshAuthorsTable(authorsTable);
                showAlert("Success", "Author updated successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to update the author.", Alert.AlertType.ERROR);
            }
        });
    }
    private void showDeleteConfirmationDialog(TableView<Author> authorsTable, Author author) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Author");
        alert.setContentText("Are you sure you want to delete this author?\n\n" +
                "ID: " + author.getAuthorId() + "\n" +
                "Name: " + author.getName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = memberController.removeAuthor(author.getAuthorId());
            if (success) {
                refreshAuthorsTable(authorsTable);
                showAlert("Success", "Author deleted successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to delete the author.", Alert.AlertType.ERROR);
            }
        }
    }

    /////////BOOK PAGE\\\\\\\\\\
    private VBox createManageBooksContent() {
        VBox manageBooksContent = new VBox(20);
        manageBooksContent.setAlignment(Pos.TOP_CENTER);
        manageBooksContent.setStyle("-fx-padding: 20;");

        // Title
        Label titleLabel = new Label("Manage Books");
        titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Create table
        TableView<Book> booksTable = new TableView<>();
        setupBooksTable(booksTable);

        // Add buttons for CRUD operations
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button approveButton = new Button("Approve");
        Button refreshButton = new Button("Refresh");

        // Styling buttons
        addButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        editButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;");
        deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");
        approveButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white;");
        refreshButton.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");

        // Add button action
        addButton.setOnAction(e -> showAddBookDialog(booksTable));

        // Edit button action
        editButton.setOnAction(e -> {
            Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                showEditBookDialog(booksTable, selectedBook);
            } else {
                showAlert("Warning", "Please select a book to edit.", Alert.AlertType.WARNING);
            }
        });

        // Delete button action
        deleteButton.setOnAction(e -> {
            Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                showDeleteConfirmationDialog(booksTable, selectedBook);
            } else {
                showAlert("Warning", "Please select a book to delete.", Alert.AlertType.WARNING);
            }
        });

        // Approve button action
        approveButton.setOnAction(e -> {
            Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
            if (selectedBook != null && !selectedBook.isApproved()) {
                Boolean success = bookController.approveBook(selectedBook.getBookCode());
                if (success) {
                    // Refresh the table after approval
                    refreshBooksTable(booksTable);
                    showAlert("Success", "Book approved successfully!", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Error", "Failed to approve the book.", Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Warning", "Please select an unapproved book to approve.", Alert.AlertType.WARNING);
            }
        });


        refreshButton.setOnAction(e -> refreshBooksTable(booksTable));

        buttonBox.getChildren().addAll(addButton, editButton, deleteButton, approveButton, refreshButton);
        manageBooksContent.getChildren().addAll(titleLabel, buttonBox, booksTable);
        return manageBooksContent;
    }

    private void setupBooksTable(TableView<Book> booksTable) {
        // Create columns
        TableColumn<Book, String> codeCol = new TableColumn<>("Book Code");
        codeCol.setCellValueFactory(cellData -> cellData.getValue().bookCodeProperty());
        codeCol.setPrefWidth(120);

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        titleCol.setPrefWidth(200);

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorIdProperty());
        authorCol.setPrefWidth(150);

        TableColumn<Book, Integer> yearCol = new TableColumn<>("Year");
        yearCol.setCellValueFactory(cellData -> cellData.getValue().yearPublishedProperty().asObject());
        yearCol.setPrefWidth(80);

        TableColumn<Book, Boolean> approvedCol = new TableColumn<>("Approved");
        approvedCol.setCellValueFactory(cellData -> cellData.getValue().isApprovedProperty());
        approvedCol.setCellFactory(col -> new TableCell<Book, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item != null && item ? "Yes" : "No");
                }
            }
        });
        approvedCol.setPrefWidth(80);

        TableColumn<Book, Boolean> availableCol = new TableColumn<>("Available");
        availableCol.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty());
        availableCol.setCellFactory(col -> new TableCell<Book, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item != null && item ? "Yes" : "No");
                }
            }
        });
        availableCol.setPrefWidth(80);

        // Add columns to the TableView
        booksTable.getColumns().addAll(codeCol, titleCol, authorCol, yearCol, approvedCol, availableCol);

        // Allow resizing columns automatically to fit the available space
        booksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Refresh the table with updated data
        refreshBooksTable(booksTable);
    }

    private void refreshBooksTable(TableView<Book> booksTable) {
        // Get the updated list of books from the bookController
        List<Book> books = bookController.getAllBooks();

        // Convert the list to ObservableList
        ObservableList<Book> bookList = FXCollections.observableArrayList(books);

        // Set the updated ObservableList to the TableView
        booksTable.setItems(bookList);

        // Optional: Scroll to the top after refresh
        booksTable.scrollTo(0);
    }

    private void showAddBookDialog(TableView<Book> booksTable) {
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Add New Book");
        dialog.setHeaderText("Enter book details");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField bookCodeField = new TextField();
        bookCodeField.setPromptText("BK001");
        TextField titleField = new TextField();
        titleField.setPromptText("Book Title");
        TextField authorIdField = new TextField();
        authorIdField.setPromptText("AUTH001");
        TextField yearPublishedField = new TextField();
        yearPublishedField.setPromptText("2023");

        grid.add(new Label("Book Code:"), 0, 0);
        grid.add(bookCodeField, 1, 0);
        grid.add(new Label("Title:"), 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(new Label("Author ID:"), 0, 2);
        grid.add(authorIdField, 1, 2);
        grid.add(new Label("Year Published:"), 0, 3);
        grid.add(yearPublishedField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(bookCodeField::requestFocus);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    return new Book(
                            bookCodeField.getText(),
                            titleField.getText(),
                            authorIdField.getText(),
                            Integer.parseInt(yearPublishedField.getText())
                    );
                } catch (NumberFormatException e) {
                    showAlert("Error", "Please enter a valid year", Alert.AlertType.ERROR);
                    return null;
                }
            }
            return null;
        });

        Optional<Book> result = dialog.showAndWait();

        result.ifPresent(book -> {
            // Panggil addBook dan tangani hasilnya
            boolean success = bookController.addBook(book.getBookCode(), book.getTitle(), book.getAuthorId(), book.getYearPublished());  // Menangani hasil dari addBook
            if (success) {
                refreshBooksTable(booksTable);  // Refresh tabel jika buku berhasil ditambahkan
                showAlert("Success", "Book added successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to add the book. Book code may already exist.", Alert.AlertType.ERROR);
            }
        });
    }

    private void showEditBookDialog(TableView<Book> booksTable, Book book) {
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Edit Book");
        dialog.setHeaderText("Update book details");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField bookCodeField = new TextField(book.getBookCode());
        bookCodeField.setDisable(true);
        TextField titleField = new TextField(book.getTitle());
        TextField authorIdField = new TextField(book.getAuthorId());
        TextField yearPublishedField = new TextField(String.valueOf(book.getYearPublished()));
        CheckBox isApprovedCheckBox = new CheckBox("Approved");
        isApprovedCheckBox.setSelected(book.isApproved());

        grid.add(new Label("Book Code:"), 0, 0);
        grid.add(bookCodeField, 1, 0);
        grid.add(new Label("Title:"), 0, 1);
        grid.add(titleField, 1, 1);
        grid.add(new Label("Author ID:"), 0, 2);
        grid.add(authorIdField, 1, 2);
        grid.add(new Label("Year Published:"), 0, 3);
        grid.add(yearPublishedField, 1, 3);
        grid.add(isApprovedCheckBox, 0, 4, 2, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                try {
                    // Buat objek Book dengan data yang sudah diupdate
                    Book updatedBook = new Book(
                            book.getBookCode(),  // bookCode tetap sama karena kita mengedit buku yang sama
                            titleField.getText(),
                            authorIdField.getText(),
                            Integer.parseInt(yearPublishedField.getText())
                    );
                    updatedBook.setApproved(isApprovedCheckBox.isSelected());
                    return updatedBook;
                } catch (NumberFormatException e) {
                    showAlert("Error", "Please enter a valid year", Alert.AlertType.ERROR);
                    return null;
                }
            }
            return null;
        });

        Optional<Book> result = dialog.showAndWait();

        result.ifPresent(updatedBook -> {
            // Panggil updateBook dengan bookCode dan data yang diubah
            boolean success = bookController.updateBook(updatedBook.getBookCode(), updatedBook.getTitle(), updatedBook.getAuthorId(), updatedBook.getYearPublished());
            if (success) {
                refreshBooksTable(booksTable);
                showAlert("Success", "Book updated successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to update the book.", Alert.AlertType.ERROR);
            }
        });
    }

    private void showDeleteConfirmationDialog(TableView<Book> booksTable, Book book) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Book");
        alert.setContentText("Are you sure you want to delete this book?\n\n" +
                "Code: " + book.getBookCode() + "\n" +
                "Title: " + book.getTitle() + "\n" +
                "Author: " + book.getAuthorId());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean success = bookController.removeBook(book.getBookCode());
            if (success) {
                refreshBooksTable(booksTable);
                showAlert("Success", "Book deleted successfully!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Failed to delete the book.", Alert.AlertType.ERROR);
            }
        }
    }

    private VBox createSettingsContent() {
        VBox settingsContent = new VBox();
        // Add components for settings here
        Label label = new Label("Settings Content");
        settingsContent.getChildren().add(label);
        return settingsContent;
    }
}

