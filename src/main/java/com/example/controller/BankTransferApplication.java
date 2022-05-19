package com.example.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BankTransfer;
import model.Buyer;
import model.DB;
import model.Sale;

import java.io.IOException;

/***
 * Class for running the application in own window Bank Transfer Application
 * Manually created view and controller dirrectly in this class
 */
public class BankTransferApplication {

    static Label info = new Label("\n\n\n\n\nBANK TRANSFER");
    static Label mainLabel = new Label();
    static Label transferInfo = new Label();
    static Label sumLabel = new Label("SUM (+ ADD MONEY / - WITHDRAW MONEY)");
    static TextField sum = new TextField();
    static Label var_symLabel = new Label("VAR SYMBOL");
    static TextField var_sym = new TextField();
    static Label ibanLabel = new Label("IBAN");
    static TextField iban = new TextField();
    static Button transfer = new Button("TRANSFER");
    static Button back = new Button("BACK");

    public static void show() throws IOException {


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
        /***
         * make the bank transfers with provided values
         * shows if the transfers was successful or not
         */
        transfer.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        if (sum.getText().equals("") || var_sym.getText().equals("") || iban.getText().equals("")) {
                            transferInfo.setText("SOME FIELDS ARE EMPTY");
                            return;
                        }
                        Integer sum_int = Integer.parseInt(sum.getText());
                        String var_sym_str = var_sym.getText();
                        String iban_str = iban.getText();

                        BankTransfer trans = new BankTransfer(sum_int, DB.getActual_user(), iban_str, var_sym_str);

                        if (trans.getSucess()) {
                            sumLabel.setText("SUCESS! Current status: "+DB.getActual_user().getWallet_amount());
                        }
                        else {
                            sumLabel.setText("ERROR! UNABLE TO WITHDRAW: "+ sum.getText()+" when user has in wallet "+DB.getActual_user().getWallet_amount());
                        }
                    }
                }
        );


        VBox mainBox = new VBox(info);
        mainBox.getChildren().add(mainLabel);
        mainBox.getChildren().add(transferInfo);
        mainBox.getChildren().add(sumLabel);
        mainBox.getChildren().add(sum);
        mainBox.getChildren().add(var_symLabel);
        mainBox.getChildren().add(var_sym);
        mainBox.getChildren().add(ibanLabel);
        mainBox.getChildren().add(iban);
        mainBox.getChildren().add(transfer);
        mainBox.getChildren().add(back);


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
            BankTransferApplication.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}