package org.fitlife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class App extends Application {

    private static Connection conn;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FitLife");
        primaryStage.setScene(new Scene(createContent(primaryStage), 800, 600));
        primaryStage.show();
    }

    private Pane createContent(Stage stage) {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        Label lblUser = new Label("Username:");
        TextField txtUser = new TextField();
        Label lblPass = new Label("Password:");
        PasswordField txtPass = new PasswordField();
        PasswordField passwordField = new PasswordField();

        //txtPass.focusedProperty().addListener(new ChangeListener<Boolean>() {
            //@Override
            //public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                //System.out.println("Password field focus changed: " + newValue);
//
            //}
        //});
        Button button = new Button("Login");

        button.setOnAction(e -> {
            try {
                conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/fitlife", txtUser.getText(), txtPass.getText());
                stage.setScene(new Scene(createDashboard(stage), 800, 600));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(lblUser, txtUser, lblPass, txtPass, button);
        vbox.setSpacing(10);
        root.getChildren().add(vbox);
        return root;
    }

    private Pane createDashboard(Stage stage) {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        Label label = new Label("Dashboard");
        Button addClient = new Button("Add Client");
        Button addTrainer = new Button("Add Trainer");
        Button addGround = new Button("Add Ground");
        Button addCourse = new Button("Add Course");

        Button button = new Button("Logout");
        button.setOnAction(e -> {
            stage.setScene(new Scene(createContent(stage), 800, 600));
        });

        addClient.setOnAction(e -> {
            stage.setScene(new Scene(addClient(stage), 800, 600));
        });

        VBox vbox = new VBox(label, addClient, addTrainer, addGround, addCourse, button);
        vbox.setSpacing(10);

        root.getChildren().add(vbox);

        return root;
    }

    private Pane addClient(Stage stage) {
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        Label label = new Label("Add Client");
        Label lblName = new Label("Name:");
        TextField txtName = new TextField();
        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        Label lblPhone = new Label("Phone:");
        TextField txtPhone = new TextField();
        Label lblAddress = new Label("Address:");
        TextField txtAddress = new TextField();
        Label lblDOB = new Label("Date of Birth:");
        TextField txtDOB = new TextField();

        Button addClient = new Button("Add Client");

        addClient.setOnAction(e -> {
            // only with name and last name
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "INSERT INTO client(name, lastname) VALUES (?, ?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, txtName.getText());
                ps.setString(2, txtEmail.getText());
                ps.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Button button = new Button("Logout");
        button.setOnAction(e -> {
            stage.setScene(new Scene(createContent(stage), 800, 600));
            // Go back to login screen
        });

        VBox vbox = new VBox(label, lblName, txtName, lblEmail, txtEmail, lblPhone, txtPhone, lblAddress, txtAddress, lblDOB, txtDOB, addClient, button);
        vbox.setSpacing(10);

        root.getChildren().add(vbox);

        return root;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
