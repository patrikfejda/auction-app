package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.scene.*;
import javafx.stage.Stage;
import model.Buyer;
import model.Seller;
import model.*;
import java.util.ArrayList;
import java.util.jar.Manifest;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/***
 * Controller for Application (window) Add Phone Type
 */

public class AddPhoneTypeController {
    @FXML
    private Label mainLabel;

    @FXML
    private TextField name;
    @FXML
    private TextField skuName;
    @FXML
    private TextField ram;
    @FXML
    private TextField memory;
    @FXML
    private TextField color;

    /***
     * Checks if all the fields are not empty and created a phone type
     * then serializes the phone types
     * then shows the customer portal
     * @param event
     */
    @FXML
    protected void addPhoneTypeButtonClick(ActionEvent event) {
        System.out.println();

        if (name.getText().equals("") || skuName.getText().equals("") || ram.getText().equals("") || memory.getText().equals("") || color.getText().equals("")) {
            System.out.println(">SOME FIELDS ARE EMPTY");
            mainLabel.setText("SOME FIELDS ARE EMPTY!");
            return;
        }

        String name_str = name.getText();
        String skuName_str = skuName.getText();
        Integer ram_str = Integer.parseInt(ram.getText());
        Integer memory_str = Integer.parseInt(memory.getText());
        String color_str = color.getText();




        new PhoneType(name_str, skuName_str, ram_str, memory_str, color_str);
        PhoneTypeReadWrite.write();



        // show Admin portal
        Stage stage = (Stage) mainLabel.getScene().getWindow();
        stage.close();
        AdminZoneApplication.main();


    }


    /***
     * going back to the portal
     */
    @FXML
    protected void back() {
        Stage stage = (Stage) mainLabel.getScene().getWindow();
        stage.close();
        AdminZoneApplication.main();
    }
}