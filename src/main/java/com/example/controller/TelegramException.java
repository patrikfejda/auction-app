package com.example.controller;


import model.DB;
import model.PhoneType;

import java.io.*;
import java.io.FileWriter;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner; // Import the Scanner class to read text files

/***
 * Class for creatuing own exception for logging problem with telegram notifications
 */
public class TelegramException extends Exception {
    /***
     * constructor for creating the custom exception
     * logs the bad attempts to the log file
     * @param message
     */
    public TelegramException(String message)
    {
        super(message);
        System.out.println(">> EXCEPTION: "+message);

        try {
            FileWriter fw = null;
            BufferedWriter bw = null;
            PrintWriter out = null;
            fw = new FileWriter("src/main/java/data/telegram_error_logs.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(LocalDate.now() + " " + LocalTime.now()+" "+message);
            out.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }




    }
}
