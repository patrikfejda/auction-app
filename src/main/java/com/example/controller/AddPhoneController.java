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
 * Controller for Application (window) Add Phone
 */
public class AddPhoneController {
    @FXML
    private Label mainLabel;
    @FXML
    private Button loadPhoneTypes;


    @FXML
    private TextField imei;
    @FXML
    private TextField state;
//    @FXML
//    private TextField type;
    @FXML
    ChoiceBox type = new ChoiceBox();


    /***
     * Loads phone types into the dropdown button
     * @param event
     */
    @FXML
    protected void loadPhoneTypes(ActionEvent event) {
        for (PhoneType t:DB.getPhoneTypes()) {
            type.getItems().add(t);
        }
        loadPhoneTypes.setVisible(false);
        type.setVisible(true);
    }


    /***
     * Checks if all the fields are not empty and created a phone
     * then shows the customer portal
     * @param event
     */
    @FXML
    protected void addPhoneButtonClick(ActionEvent event) {
        System.out.println();

        PhoneType type_obj = (PhoneType)null;
        String imei_str = imei.getText();
        String state_str = state.getText();

        if (type.getValue() != null) {
            type_obj = PhoneType.getPhoneTypeFromName(type.getValue().toString());
        }

        if (imei_str.equals("") || state_str.equals("") || type_obj == (PhoneType)null) {
            System.out.println(">SOME FIELDS ARE EMPTY");
            mainLabel.setText("SOME FIELDS ARE EMPTY!");
            return;
        }

        type_obj.CreatePhone(imei_str, DB.getActual_user(), state_str);



        // show Customer portal
        Stage stage = (Stage) mainLabel.getScene().getWindow();
        stage.close();
        CustomerZoneApplication.main();


    }


    /***
     * going back to the portal
     */
    @FXML
    protected void back() {
        Stage stage = (Stage) mainLabel.getScene().getWindow();
        stage.close();
        CustomerZoneApplication.main();
    }




}