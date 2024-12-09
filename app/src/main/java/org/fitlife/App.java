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
                System.out.println("Connected to database");
                //stage.setScene(new Scene(createDashboard(stage), 800, 600));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(lblUser, txtUser, lblPass, txtPass, button);
        vbox.setSpacing(10);
        root.getChildren().add(vbox);
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
