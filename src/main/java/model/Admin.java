package model;

import com.example.controller.TelegramNotification;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.time.LocalDate;
import java.time.LocalTime;

/***
 * Model class for Admin user
 */
public class Admin extends User {

    public Admin(String username, String password, String telegram_chat_id) {
        super(username, password, telegram_chat_id);
    }

    public Admin(String username, String password) {
        super(username, password, "");
    }

    /***
     * Sends telegram notification to admin for every wrong login attempt
     * @param username the username which was trying to login with wrong password
     * @throws Exception the exception if telegram API doesnt work or internet issues
     */
    public void notify_wrong_login(String username) throws Exception {
        System.out.println("SENDING ADMIN NOTIFICATION ABOUT WRONG LOGIN!");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        TelegramNotification obj = new TelegramNotification();
        if(!this.getTelegram_chat_id().equals("")) {
            try {
                System.out.println("Testing 2 - Send Http POST request");
                obj.sendPost(this.getTelegram_chat_id(), "ADMIN NOTIFICATION - WRONG LOGIN!!!\n"+
                        "Usernmame: "+username+"\nTime: "+LocalDate.now() + " " + LocalTime.now());
            }
            finally {
                obj.close();
            }
        }


    }
}
