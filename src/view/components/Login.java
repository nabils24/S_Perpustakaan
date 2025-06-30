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

public class Login {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private loginController loginController;
    private memberController memberController;

    public Login(Stage primaryStage) {
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");

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
                primaryStage.setScene(createAdminScene(primaryStage));
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

    public Scene createAdminScene(Stage primaryStage) {
        // Main layout menggunakan BorderPane untuk manajemen tata letak yang lebih baik
        BorderPane mainLayout = new BorderPane();
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

        Button manageMahasiswaBtn = createSidebarButton("Manage Mahasiswa", "assets/admin/AdminLogo.png");
        Button manageBooksBtn = createSidebarButton("Manage Books", "assets/admin/BookIcon.png");
        Button dashboardBtn = createSidebarButton("Dashboard", "assets/admin/DashboardIcon.png");
        Button settingsBtn = createSidebarButton("Settings", "assets/admin/Settingscon.png");

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
        VBox dashboardContent = new VBox(20);
        dashboardContent.setAlignment(Pos.TOP_CENTER);

        Label welcomeLabel = new Label("Welcome to Admin Dashboard");
        welcomeLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        GridPane statsGrid = new GridPane();
        statsGrid.setHgap(20);
        statsGrid.setVgap(20);
        statsGrid.setAlignment(Pos.CENTER);

        // Contoh statistik cards
        statsGrid.add(createStatCard("Total Mahasiswa", memberController.getTotalMahasiswa(), "#3498db"), 0, 0);
        statsGrid.add(createStatCard("Total Books", 5342, "#2ecc71"), 1, 0);
        statsGrid.add(createStatCard("Active Loans", 342, "#e74c3c"), 0, 1);
        statsGrid.add(createStatCard("Available Books", 2531, "#f39c12"), 1, 1);

        dashboardContent.getChildren().addAll(welcomeLabel, statsGrid);
        mainContent.getChildren().add(dashboardContent);

        // Set layout
        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(mainContent);

        // Atur ukuran window yang lebih besar
        Scene scene = new Scene(mainLayout, 1200, 700); // Lebar 1200, Tinggi 700
        scene.getStylesheets().add(getClass().getResource("/assets/admin/css/admin.css").toExternalForm());

        return scene;
    }

    // Helper method untuk membuat tombol sidebar
    private Button createSidebarButton(String text, String iconPath) {
        Button btn = new Button(text);
        btn.setGraphic(new ImageView(new Image(iconPath, 20, 20, true, true)));
        btn.setContentDisplay(ContentDisplay.LEFT);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT; -fx-min-width: 200;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #34495e; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT; -fx-min-width: 200;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: #ecf0f1; -fx-font-size: 14; -fx-padding: 10 15; -fx-alignment: CENTER_LEFT; -fx-min-width: 200;"));
        return btn;
    }

    // Helper method untuk membuat stat card
    private VBox createStatCard(String title, int value, String color) {
        VBox card = new VBox(5);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-padding: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 2); -fx-min-width: 200; -fx-min-height: 120;");
        card.setAlignment(Pos.CENTER);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #7f8c8d;");

        Label valueLabel = new Label(String.valueOf(value));
        valueLabel.setStyle("-fx-font-size: 28; -fx-font-weight: bold; -fx-text-fill: " + color + ";");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
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

