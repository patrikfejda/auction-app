package com.example.controller;

import model.*;

import java.io.FileNotFoundException;
import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errorsr;

/***
 * Class for serializiong Phone Types when opening app or adding new phone type via admin portal
 */
public class PhoneTypeReadWrite {
    /***
     * Read the serialization file and creates all the Phone Types with given data
     */
    public  static void read() {

        try {
            File myObj = new File("src/main/java/data/PhoneTypes.txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            System.out.println("READING PHONE TYPES!");
            while (data.equals("--")) {
                String name = myReader.nextLine();
                String skuName = myReader.nextLine();
                Integer ram = Integer.parseInt(myReader.nextLine());
                Integer memory = Integer.parseInt(myReader.nextLine());
                String color = myReader.nextLine();
                new PhoneType(name, skuName, ram, memory, color);
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(DB.getPhoneTypes());
    }

    /***
     * Writes all the phone type data in the ser. file
     */
    public static void write() {

        try {
            FileWriter myWriter = new FileWriter("src/main/java/data/PhoneTypes.txt");
            for (PhoneType var:DB.getPhoneTypes()) {
                myWriter.write("--");
                myWriter.write("\n");
                myWriter.write(var.getName());
                myWriter.write("\n");
                myWriter.write(var.getSkuName());
                myWriter.write("\n");
                myWriter.write(String.valueOf(var.getRam()));
                myWriter.write("\n");
                myWriter.write(String.valueOf(var.getMemory()));
                myWriter.write("\n");
                myWriter.write(var.getColor());
                myWriter.write("\n");
            }
            myWriter.write("#\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
