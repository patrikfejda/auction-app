package com.example.controller;

import model.Seller;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import model.*;

/***
 * 'Subject' class for Observer
 */
public class TelegramNotification {
    /***
     * observerCollection
     */
    static ArrayList<User> subscribers = new ArrayList<User>();

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    /***
     * registerObserver()
     * @param user user to be registered
     */
    public static void subscribe(User user) {
        subscribers.add(user);
        System.out.println("ADDING USER TO OBSERVER "+ user);
        System.out.println("OBSERVERS "+ TelegramNotification.subscribers);
    }

    /***
     * unregisterObserver()
     * @param user user to be unregistered
     */
    public static void unsubscribe(User user) {
        subscribers.remove(user);
    }

    /***
     * notifyObservers()
     * @param username username that was logged wrong
     * @throws Exception throws Exception if Telegram issue occurs
     */
    static void notify_listeners(String username) throws Exception {
        System.out.println("WAITING 30 SECOND AND IF USER IS NOT LOGGED IN SEND NOTIFICATION!");
        TimeUnit.SECONDS.sleep(30);
        if (DB.getActual_user() != null && DB.getActual_user().getUsername().equals(username)) {
            System.out.println("USER IS LOGGED IN, DONT SEND NOTIFICATION!");
            return;
        }
        for (User subscriber:TelegramNotification.subscribers) {
            System.out.println("30 SECONDS PASSED - SEND NOTIFICATION!");
            // observer update
            subscriber.notify_wrong_login(username);
        }
    }

    public void close() throws IOException {
        httpClient.close();
    }

    /***
     * Sends the post request to telegram API
     * @param chat_id Identificator of the chat
     * @param message message to be sent
     * @throws Exception throws exception if telegram issue occurs
     */
    public void sendPost(String chat_id, String message) throws Exception {

        HttpPost post = new HttpPost("https://api.telegram.org/bot5077690686:AAEFI7jfRDukAEhVly6ObhKWdzYk4cgA0PU/sendMessage");

        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("chat_id", chat_id));
        urlParameters.add(new BasicNameValuePair("text", message));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }
        catch(Exception ex) {
            throw new TelegramException(ex.getMessage());
        }
    }



}
