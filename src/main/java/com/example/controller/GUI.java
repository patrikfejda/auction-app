package com.example.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/***
 * Class for initial loading of GUI and Login App
 */
public class GUI extends Application {
    @Override
    /***
     * Shown the window of the application
     * @throws IOException if fxml file cannot be loaded
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 920, 920);
        scene.getStylesheets().add("main.css");
        stage.setTitle("iPhone E-auction system");
        stage.setScene(scene);
        stage.show();
    }

    public static void main() {

        launch();
    }
}