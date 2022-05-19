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
 * Controller for Application Sale Sell
 */
public class SaleSellController {
    @FXML
    private Label mainLabel;

    @FXML
    private Button loadMySales;
    @FXML
    ChoiceBox mySales = new ChoiceBox();




    @FXML
    private Button loadMyPhones;
    @FXML
    ChoiceBox myPhones = new ChoiceBox();
    @FXML
    private Label starting_price_label;
    @FXML
    private Label step_price_label;
    @FXML
    private Label autosell_price_label;
    @FXML
    private TextField starting_price;
    @FXML
    private TextField step_price;
    @FXML
    private TextField autosell_price;
    @FXML
    private Button putPhoneForSale;
    @FXML
    private Label saleInfo;
    @FXML
    private Button getSaleInfo;
    @FXML
    private Button sellPhoneForActualPrice;

    /***
     * load the user sales into the dropdown
     * @param event
     */
    @FXML
    protected void loadMySales(ActionEvent event) {
        for (Sale t:DB.getSales()) {
            if (t.getSeller() == DB.getActual_user()) {
                mySales.getItems().add(t);
            }
        }
        loadMySales.setVisible(false);
        mySales.setVisible(true);
    }

    /***
     * load user phones into dropdown
     * @param event
     */
    @FXML
    protected void loadMyPhones(ActionEvent event) {
        Seller seller = (Seller)DB.getActual_user();
        for (Phone t:seller.getPhones()) {
            if (t.getIn_sale() == false) {
                myPhones.getItems().add(t);
                t.setIn_sale(true);
            }
        }
        loadMyPhones.setVisible(false);
        myPhones.setVisible(true);
        starting_price.setVisible(true);
        starting_price_label.setVisible(true);
        step_price.setVisible(true);
        step_price_label.setVisible(true);
        autosell_price.setVisible(true);
        autosell_price_label.setVisible(true);
    }

    /***
     * Cerate the sale with sellected phone and values
     * @param event
     */
    @FXML
    protected void putPhoneForSale(ActionEvent event) {
        Integer starting_price_int = Integer.parseInt(starting_price.getText());
        Integer step_price_int = Integer.parseInt(step_price.getText());
        Integer autosell_price_int = Integer.parseInt(autosell_price.getText());

        if (myPhones.getValue() == null) {
            mainLabel.setText("SOME FIELDS ARE EMPTY");
            return;
        }

        Phone phone = Phone.getPhoneFromName(myPhones.getValue().toString(), DB.getActual_user());

        new Sale((Seller)DB.getActual_user(), phone, starting_price_int, step_price_int, autosell_price_int);
        back();
    }

    /***
     * Shows all the info about the sale
     * @param event
     */
    @FXML
    protected void getSaleInfo(ActionEvent event) {
        if (mySales.getValue() == null) {
            mainLabel.setText("SOME FIELDS ARE EMPTY");
            return;
        }
        Sale sale = Sale.getSaleFromName(mySales.getValue().toString());

        String out = "";
        out += "\nIS SOLD: " + sale.getSold();
        out += "\nSELLER: " + sale.getSeller();
        out += "\nBUYER / WINNER: " + sale.getBuyer();
        out += "\nPHONE: " + sale.getPhone();
        out += "\nSTART: " + sale.getStarting_price();
        out += "\nSTEP: " + sale.getStep_price();
        out += "\nAUTOSELL: " + sale.getAutosell_price();
        out += "\nACTUAL: " + sale.getActual_price();
        saleInfo.setText(out);
        saleInfo.getStyleClass().add("with-border");
    }

    /***
     * Sells the phone to the buyer for actual price
     * @param event
     */
    @FXML
    protected void sellPhoneForActualPrice(ActionEvent event) {
        if (mySales.getValue() == null) {
            mainLabel.setText("SOME FIELDS ARE EMPTY");
            return;
        }
        Sale sale = Sale.getSaleFromName(mySales.getValue().toString());
        if (sale.getBuyer() == null) {
            saleInfo.setText("NO BUYER!");
            return;
        }
        if (sale.getSold() == true) {
            saleInfo.setText("ALREADY SOLD!");
            return;
        }
        if(sale.sell(sale.getBuyer())) {
            String out = "";
            out += "\nPHONE SOLD TO "+sale.getBuyer();
            saleInfo.setText(out);
            saleInfo.getStyleClass().add("with-border");
        }
        else {
            String out = "";
            out += "\nBUYER HAS NOT ENOUGH MONEY "+sale.getBuyer();
            saleInfo.setText(out);
            saleInfo.getStyleClass().add("with-border");
        }


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