package com.example.controller;

import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import model.Buyer;
import model.Seller;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.channels.AlreadyBoundException;


/***
 * Controller for Login Application
 */

public class LoginController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ToggleButton is_buyer;
    @FXML
    private ToggleButton is_admin;

    @FXML
    private TextField telegram_chat_id;

    @FXML
    private TextField ico;
    @FXML
    private TextField dic;
    @FXML
    private TextField icdph;

    @FXML
    private Label ico_label;
    @FXML
    private Label dic_label;
    @FXML
    private Label icdph_label;

    /***
     * Validates the user credentials and logs him in the system
     * If username if wrong, shows error and calls the Telegram Notification system with dalay (own thread)
     * @param event
     * @throws Exception throws exception if Telegram notif system fails
     */
    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws Exception {
        System.out.println();
        Buyer buyer = (Buyer) null;
        Seller seller = (Seller) null;
        Admin admin = (Admin) null;
        String username_str = username.getText();
        String password_str = password.getText();

        System.out.println(">USER TRYING TO LOGIN " + username_str + " " + password_str);
        System.out.println(">CHECKING IF USER IS FOUND IN BUYERS");
        for (Buyer var : DB.getBuyers()) {
            if (var.validate_password(username_str, password_str) == true) {
                buyer = var;
                DB.setActual_user(buyer);
                System.out.println(">BUYER - LOGIN OK!");
            }
        }

        if (buyer == null) {
            System.out.println(">USER IS NOT BUYER");
            System.out.println(">CHECKING IF USER IS SELLER");
            for (Seller var : DB.getSellers()) {
                if (var.validate_password(username_str, password_str) == true) {
                    seller = var;
                    DB.setActual_user(seller);
                    System.out.println(">SELLER - LOGIN OK!");
                }
            }

            if (seller == null) {
                System.out.println(">USER IS NOT SELLER");
                System.out.println(">CHECKING IF USER IS ADMIN");
                for (Admin var : DB.getAdmins()) {
                    if (var.validate_password(username_str, password_str) == true) {
                        admin = var;
                        DB.setActual_user(admin);
                        System.out.println(">ADMIN - LOGIN OK!");
                    }
                }

            }


        }
        if (buyer != null || seller != null) {
            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
            CustomerZoneApplication.main();
        }
        else if (admin != null) {
            // show Admin portal
            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
            AdminZoneApplication.main();
        }
        else {
            welcomeText.setText("USERNAME OR PASSWORD IS WRONG!");
            System.out.println(">USERNAME OR PASSWORD IS WRONG!");

            // send notification
            new Thread(() -> {
                try {
                    TelegramNotification.notify_listeners(username_str);
                }
                catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }).start();
//            TelegramNotification.notify_listeners(username_str);
        }

    }

    /***
     * Registers new user of sellected type
     * If username already exists, shown an error
     * @param event
     */
    @FXML
    protected void onRegisterButtonClick(ActionEvent event) {
        System.out.println();
        Buyer buyer = (Buyer)null;
        Seller seller = (Seller)null;
        Admin admin = (Admin)null;
        boolean already_exists = false;
        String username_str = username.getText();
        String password_str = password.getText();
        String telegram_chat_id_str = telegram_chat_id.getText();
        String ico_str = ico.getText();
        String dic_str = dic.getText();
        String icdph_str = icdph.getText();

        if (username_str.equals("") || password_str.equals("")) {
            System.out.println(">REGISTER - USERNAME OR PASSWORD ARE EMPTY");
            welcomeText.setText("USERNAME OR PASSWORD ARE EMPTY!");
            return;
        }

        if (telegram_chat_id_str.length() != 0 && telegram_chat_id_str.length() != 10) {
            System.out.println(">TELEGRAM CHAT ID NOT VALID!");
            welcomeText.setText("TELEGRAM CHAT ID NOT VALID");
            return;
        }

        System.out.println(">CHECKING IF USERNAME ALREADY EXISTS: "+username_str);

        for  (Buyer var : DB.getBuyers())
        {
            if (var.validate_username(username_str) == true) {
                already_exists = true;
                System.out.println(">BUYER - USERNAME ALREADY EXISTS!");
                welcomeText.setText("USERNAME ALREADY EXISTS!");
            }
        }
        for  (Seller var : DB.getSellers())
        {
            if (var.validate_username(username_str) == true) {
                already_exists = true;
                System.out.println(">SELLER - USERNAME ALREADY EXISTS!");
                welcomeText.setText("USERNAME ALREADY EXISTS!");
            }
        }
        for  (Admin var : DB.getAdmins())
        {
            if (var.validate_username(username_str) == true) {
                already_exists = true;
                System.out.println(">ADMIN - USERNAME ALREADY EXISTS!");
                welcomeText.setText("USERNAME ALREADY EXISTS!");
            }
        }

        if (already_exists == false) {

            if (is_buyer.isSelected() == true) {
                System.out.println(">BUYER TRYING TO REGISTER "+ username_str + " " + password_str);
                buyer = new Buyer(username_str, password_str, telegram_chat_id_str, ico_str, dic_str, icdph_str);
                DB.setActual_user(buyer);
                DB.addBuyer(buyer);
            }
            else if (is_admin.isSelected() == true) {
                System.out.println(">ADMIN TRYING TO REGISTER "+ username_str + " " + password_str);
                admin = new Admin(username_str, password_str, telegram_chat_id_str);
                DB.setActual_user(admin);
                DB.addAdmin(admin);

                // show Admin portal
                Stage stage = (Stage) username.getScene().getWindow();
                stage.close();
                AdminZoneApplication.main();

                return;
            }
            else {
                System.out.println(">SELLER TRYING TO REGISTER "+ username_str + " " + password_str);

                if (seller == null) {
                    seller = new Seller(username_str, password_str, telegram_chat_id_str);
                    DB.setActual_user(seller);
                    DB.addSeller(seller);
                }
            }

            // show Customer portal
            Stage stage = (Stage) username.getScene().getWindow();
            stage.close();
            CustomerZoneApplication.main();
//            WalletApplication.main();

        }
    }

    /***
     * Shown fields for ICO, DIC, IC DPH
     * @param event
     */
    @FXML
    protected void onShowMoreFieldsClick(ActionEvent event) {

        ico.setVisible(true);
        dic.setVisible(true);
        icdph.setVisible(true);

        ico_label.setVisible(true);
        dic_label.setVisible(true);
        icdph_label.setVisible(true);
    }

}