package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Buyer;
import model.Seller;
import model.*;
import java.util.ArrayList;


/***
 * Controller for Admin Zone
 */
public class AdminZoneController {
    @FXML
    private Label allTransfers;
    @FXML
    ChoiceBox users = new ChoiceBox();
    @FXML
    private Button loadUsers;
    @FXML
    private TextField sum;
    @FXML
    private Button addwithdrawmoney;
    @FXML
    private Label addwithdrawinfo;
    @FXML
    private Label sumLabel;

    /***
     * Log out of the app
     */
    @FXML
    protected void logOut() {
        DB.setActual_user((User)null);
        Stage stage = (Stage) allTransfers.getScene().getWindow();
        stage.close();
        LoginApplication.main();
    }

    /***
     * Close portal, Show App add phone type
     */
    @FXML
    protected void addPhoneType() {
        Stage stage = (Stage) allTransfers.getScene().getWindow();
        stage.close();
        AddPhoneTypeApplication.main();
    }

    /***
     * Loads Seller and Buyers to dropdown menu
     */
    @FXML
    protected void loadUsers() {

        for (Seller s: DB.getSellers()) {
            users.getItems().add(s);
        }
        for (Buyer b: DB.getBuyers()) {
            users.getItems().add(b);
        }
        users.setVisible(true);
        loadUsers.setVisible(true);
        sum.setVisible(true);
        addwithdrawmoney.setVisible(true);
        sumLabel.setVisible(true);
    }

    /***
     * Add amount to selected users wallet
     */
    @FXML
    protected void addwithdrawmoney() {
        if (users.getValue() == null) {
            addwithdrawinfo.setText("SOME FIELDS ARE EMPTY");
            return;
        }
        Integer sum_int = Integer.parseInt(sum.getText());

        User user = null;

        Seller seller = Seller.getSellerFromName(users.getValue().toString());
        Buyer buyer = Buyer.getBuyerFromName(users.getValue().toString());

        if (seller != null) {
            user = (User)seller;
        }
        if (buyer != null) {
            user = (User)buyer;
        }

        CashTransfer trans = new CashTransfer(sum_int, user, (Admin)DB.getActual_user());

        if (trans.getSucess()) {
            sumLabel.setText("SUCESS! Current status: "+user.getWallet_amount());
        }
        else {
            sumLabel.setText("ERROR! UNABLE TO WITHDRAW: "+ sum.getText()+" when user has in wallet "+user.getWallet_amount());
        }

    }

    /***
     * Shows all transfers ever created
     */
    @FXML
    protected void showAllTransfers() {

        allTransfers.getStyleClass().add("with-border");
        String out;
        out = "ALL TRANSFERS:\n";

        for (Transfer var: DB.getTransfers()) {
            out += "- " + var + "\n";
        }
        if (out == "ALL TRANSFERS:\n") {
            out = "NO TRANSFERS :(";
        }
        allTransfers.setText(out);
    }


}