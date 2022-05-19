package com.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DB;
import model.*;
import model.Sale;

import java.io.IOException;
/***
 * Class for running the application in own window Sale Buy App
 * Manually created view and controller dirrectly in this class
 */
public class SaleBuyApplication {

    static Label info = new Label("\n\n\n\n\nBUY PHONE");
    static Label mainLabel = new Label();
    static ChoiceBox allSales = new ChoiceBox();
    static Button getInfoButton = new Button("GET INFO");
    static Label saleInfo = new Label();
    static Button back = new Button("BACK");
    static Button addMoney = new Button("START / ADD MONEY");


    public static void show() throws IOException {
        /***
         * Shown info about sellected Auction
         */
        getInfoButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        System.out.println("GET INFO!");
                        if (allSales.getValue() == null) {
                            mainLabel.setText("SOME FIELDS ARE EMPTY");
                            return;
                        }

                        Sale sale = Sale.getSaleFromName(allSales.getValue().toString());

                        if (sale == null) {
                            mainLabel.setText("error");
                            return;
                        }
                        String out = "";
                        out += "\nIS SOLD: " + sale.getSold();
                        out += "\nSELLER: " + sale.getSeller();
                        out += "\nBUYER / WINNER: " + sale.getBuyer();
                        out += "\nPHONE: " + sale.getPhone();
                        out += "\nSTART: " + sale.getStarting_price();
                        out += "\nSTEP: " + sale.getStep_price();
                        out += "\nAUTOSELL: " + sale.getAutosell_price();
                        out += "\nACTUAL: " + sale.getActual_price();
                        saleInfo.getStyleClass().add("with-border");
                        saleInfo.setText(out);
                    }
                }
        );
        /***
         * Adds money to the auction / starts the auction
         * Function can also autosell the device if the autosell price is reached
         */
        addMoney.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        System.out.println("ADD MONEY!");
                        if (allSales.getValue() == null) {
                            mainLabel.setText("SOME FIELDS ARE EMPTY");
                            return;
                        }

                        Sale sale = Sale.getSaleFromName(allSales.getValue().toString());

                        if (sale == null) {
                            mainLabel.setText("error");
                            return;
                        }

                        if (sale.getSold() == true) {
                            saleInfo.getStyleClass().add("with-border");
                            saleInfo.setText("PHONE ALREADY SOLD!");
                            return;
                        }

                        if (sale.getActual_price() == 0) {
                            sale.addFirstMoney((Buyer)DB.getActual_user());
                        }
                        else {
                            if (!sale.addMoney((Buyer)DB.getActual_user())) {
                                saleInfo.getStyleClass().add("with-border");
                                saleInfo.setText("SALE INFO FAILED. THE PHONE MAY BE SOLD OR DONT HAVE ENOUGH MONEY!");
                                return;
                            }
                        }

                        String out = "";
                        out += "\nIS SOLD: " + sale.getSold();
                        out += "\nSELLER: " + sale.getSeller();
                        out += "\nBUYER / WINNER: " + sale.getBuyer();
                        out += "\nPHONE: " + sale.getPhone();
                        out += "\nSTART: " + sale.getStarting_price();
                        out += "\nSTEP: " + sale.getStep_price();
                        out += "\nAUTOSELL: " + sale.getAutosell_price();
                        out += "\nACTUAL: " + sale.getActual_price();
                        saleInfo.getStyleClass().add("with-border");
                        saleInfo.setText(out);
                    }
                }
        );

        /***
         * going back to the portal
         */
        back.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        Stage stage = (Stage) mainLabel.getScene().getWindow();
                        stage.close();
                        CustomerZoneApplication.main();
                    }
                }
        );

        for (Sale t:DB.getSales()) {
            allSales.getItems().remove(t);
        }
        for (Sale t:DB.getSales()) {
            allSales.getItems().add(t);
        }


        VBox mainBox = new VBox(info);
        mainBox.getChildren().add(allSales);
        mainBox.getChildren().add(mainLabel);
        mainBox.getChildren().add(getInfoButton);
        mainBox.getChildren().add(saleInfo);
        mainBox.getChildren().add(back);
        mainBox.getChildren().add(addMoney);


        Pane p = new Pane();
        Scene secondScene = new Scene(p, 920, 920);
        secondScene.getStylesheets().add("main.css");

        p.getChildren().add(mainBox);


        Stage newWindow = new Stage();
        newWindow.setTitle("BUY PHONE, user: "+ DB.getActual_user().getUsername());
        newWindow.setScene(secondScene);

        newWindow.setX(400);
        newWindow.setY(50);

        newWindow.show();


    }


    public static void main() {
        try {
            SaleBuyApplication.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    //    static void getInfo() {
//        System.out.println("GET INFO!");
//        if (allSales.getValue() == null) {
//            mainLabel.setText("SOME FIELDS ARE EMPTY");
//            return;
//        }
//
//        Sale sale = Sale.getSaleFromName(allSales.getValue().toString());
//
//        if (sale == null) {
//            mainLabel.setText("error");
//            return;
//        }
//
//
//    }
}