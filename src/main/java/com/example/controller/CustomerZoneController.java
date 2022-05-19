package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Buyer;
import model.Seller;
import model.*;
import java.util.ArrayList;

/***
 * Controller for Customer Zone
 */

public class CustomerZoneController {
    @FXML
    private Label customerPhones;
    @FXML
    private Label walletStatus;


    /***
     * Log out of the app
     */
    @FXML
    protected void logOut() {
        DB.setActual_user((User)null);
        Stage stage = (Stage) customerPhones.getScene().getWindow();
        stage.close();
        LoginApplication.main();
    }

    /***
     * Open Selll Phone App, If user is not Seller shown error
     */
    @FXML
    protected void sellPhone() {
        if (DB.getActual_user() instanceof Buyer) {
            customerPhones.setText("YOU ARE NOT SELLER! YOU CANNOT SELL!");
            System.out.println(">> Not seller - cannot sell!");
            return;
        }
        Stage stage = (Stage) customerPhones.getScene().getWindow();
        stage.close();
        SaleSellApplication.main();
    }

    /***
     * Open Buy Phone App, If user is not Buyer shown error
     */
    @FXML
    protected void buyPhone() {
        if (DB.getActual_user() instanceof Seller) {
            customerPhones.setText("YOU ARE NOT BUYER! YOU CANNOT BUY!");
            System.out.println(">> Not BUYER - cannot buy!");
            return;
        }
        Stage stage = (Stage) customerPhones.getScene().getWindow();
        stage.close();
        SaleBuyApplication.main();
    }

    /***
     * Open App Phone App, If user is not Seller shown error
     */
    @FXML
    protected void addPhone() {
        if (DB.getActual_user() instanceof Buyer) {
            customerPhones.setText("YOU ARE NOT SELLER! YOU CANNOT ADD PHONE!");
            System.out.println(">> Not seller - cannot add phone!");
            return;
        }
        Stage stage = (Stage) customerPhones.getScene().getWindow();
        stage.close();
        AddPhoneApplication.main();
    }

    /***
     * Open Bank Transfer app
     */
    @FXML
    protected void bankTransfer() {
        Stage stage = (Stage) customerPhones.getScene().getWindow();
        stage.close();
        BankTransferApplication.main();
    }

    /***
     * shows list of all your phones (that you CURRENTLY own)
     */
    @FXML
    protected void showMyPhones() {

        customerPhones.getStyleClass().add("with-border");
        String out;
        out = "Your phones:\n";
        System.out.println("Actual user username: "+DB.getActual_user().getUsername());
        if (DB.getActual_user() instanceof Seller) {
            Seller seller = (Seller)DB.getActual_user();
            System.out.println("Seller phones: "+seller.getPhones());
            for (Phone var: seller.getPhones()) {
                out += "- " + var + "\n";
            }
        }
        else {
            Buyer buyer = (Buyer)DB.getActual_user();
            for (Phone var: buyer.getPhones()) {
                out += "- " + var + "\n";
            }
        }

        if (out == "Your phones:\n") {
            out = "YOU HAVE NO PHONES :(";
        }
        customerPhones.setText(out);
    }

    /***
     * Shows amount in your wallet
     */
    @FXML
    protected void showMyWallet() {

        walletStatus.getStyleClass().add("with-border");
        String out = "";
        out += DB.getActual_user().getWallet_amount();
        out += " EUR";
        walletStatus.setText(out);
    }

}