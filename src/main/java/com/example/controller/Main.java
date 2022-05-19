package com.example.controller;

import model.*;
import java.util.ArrayList;


///
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
///

/***
 * Main class for initial run (and demo data)
 */
public class Main {

    public static void main(String[] args) {

        // phone types
//        new PhoneType("Iphone 13 mini", "Iphone 13 mini 128GB black", 4, 128, "black");
//        new PhoneType("Iphone 13", "iPhone 13 128GB black", 4, 128, "black");
//        new PhoneType("Iphone 13 Pro", "iPhone 13 Pro 128GB black", 4, 128, "black");
//        new PhoneType("Iphone 13 Pro Max", "iPhone 13 128GB black", 4, 128, "black");

//         demo users

        Seller seller = new Seller("s", "s", "5077485145");
        DB.addSeller(seller);

        Phone p = new Phone(new PhoneType("ASDA", "dasdsad", 123, 123, "dsadas"), seller, "12312", "VWEY GUT");
        seller.addPhone(p);
        Sale s = new Sale(seller, p, 100, 40, 200);

        Buyer buyer = new Buyer("b", "b", "5077485145", "", "", "");
        DB.addBuyer(buyer);

        Admin admin = new Admin("a", "a", "5077485145");
        DB.addAdmin(admin);

        new BankTransfer(1000, buyer, "das", "das");


        PhoneTypeReadWrite.read();

        GUI.main();
    }
}
