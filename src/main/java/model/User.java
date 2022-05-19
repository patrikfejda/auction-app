package model;

import com.example.controller.TelegramNotification;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.ArrayList;

/***
 * Abstract class for User (Seller, Admin, Buyer)
 */
public abstract class User {
    private String username;
    private String password;
    private String telegram_chat_id;
    private Integer wallet_amount = 0;

    /***
     * Notifying the user on Telegram that someone is trying to log in to his account with wrong password
     * @param username username of the wrong login, which is validated whether its our username
     * @throws Exception the exception if telegram API doesnt work or internet issues
     */
    public void notify_wrong_login(String username) throws Exception {
        if (this.username.equals(username) && !this.telegram_chat_id.equals("")) {
            System.out.println("SENDING NOTIFICATION, BECAUSE SOMEONE TRIED TO LOH IN AS YOU!");
            CloseableHttpClient httpClient = HttpClients.createDefault();
            TelegramNotification obj = new TelegramNotification();

            try {
                System.out.println("Testing 2 - Send Http POST request");
                obj.sendPost(this.telegram_chat_id, "USER NOTIFICATION!!!\n\n" +
                        "Someone is trying to log to your account with username " + username +
                        " !\nIf it's not you, please contact our support!");
            } finally {
                obj.close();
            }
        }
    }

    public String getTelegram_chat_id() {
        return telegram_chat_id;
    }

    /***
     * Validate if the username provided equals to the objects username
     * @param username username to be compared
     * @return if the username equals to the users username
     */
    public boolean validate_username(String username) {
        if (this.username.equals(username)) {
            return true;
        }
        return false;
    }

    /***
     * Validates username and password of the object
     * @param username username to be validated
     * @param password password to be validated
     * @return true if username and password match to the users
     */
    public boolean validate_password(String username, String password) {
        if (this.username.equals(username)) {
            if (this.password.equals(password)) {
                return true;
            }
        }
        return false;
    }


    public User(String username, String password, String telegram_chat_id) {
        this.username = username;
        this.password = password;
        this.telegram_chat_id = telegram_chat_id;
        TelegramNotification.subscribe(this);
        System.out.println("New user created with username " + username + " and password " + password + ".");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return this.getUsername();
    }

    public Boolean isSeller() {
        for (Seller s : DB.getSellers()) {
            if (this.username.equals(s.getUsername())) {
                return true;
            }
        }
        return false;
    }

    public Integer getWallet_amount() {
        return wallet_amount;
    }

    /***
     * Adds/ withdraws the amount from wallet
     * @param wallet_amount amount to be added(>0)/withdrawn(<0)
     * @return if the operation was sucessful
     */
    public boolean addAmount_to_Wallet_amount(Integer wallet_amount) {
        Integer w;
        w = this.wallet_amount + wallet_amount;
        if (w < 0) {
            return false;
        }
        this.wallet_amount += wallet_amount;
        return true;
    }
}
