package com.example.controller;

import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
/***
 * Class for running the application in own window from FXML Admin Zone Application
 */
public class AdminZoneApplication {
    /***
     * Shown the window of the application
     * @throws IOException if fxml file cannot be loaded
     */
    public static void show() throws IOException {
//        StackPane secondaryLayout = new StackPane();

        FXMLLoader fxmlLoader = new FXMLLoader(CustomerZoneApplication.class.getResource("admin-zone-view.fxml"));
        Scene secondScene = new Scene(fxmlLoader.load(), 920, 920);
        secondScene.getStylesheets().add("main.css");



        Stage newWindow = new Stage();
        newWindow.setTitle("Admin Zone, customer: "+DB.getActual_user().getUsername());
        newWindow.setScene(secondScene);

        newWindow.setX(400);
        newWindow.setY(50);

        newWindow.show();



    }


    public static void main() {
        try {
            AdminZoneApplication.show();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }
}