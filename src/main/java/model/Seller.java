package model;

import java.util.ArrayList;

/***
 * Model class for seller
 */
public class Seller extends User implements PhoneOwner {
    ArrayList<Phone> phones = new ArrayList<Phone>();
    // IMPLICIT INTERFACE METHODS IMPLEMENTATION
    // WHAT IS IMPLICIT IMPLEMENTATION: https://www.c-sharpcorner.com/UploadFile/8911c4/implicit-and-explicit-interface-examples/
    public void addPhone(Phone phone) {
        phones.add(phone);
    }
    public void removePhone(Phone phone) {

        System.out.println("Phones before delete:"+ this.getPhones());
        phones.remove(phone);
        System.out.println("Phones after delete:"+ this.getPhones());

    }
    public ArrayList<Phone> getPhones() {
        System.out.println("My phones"+ phones);
        return phones;
    }

    public Seller(String username, String password, String telegram_chat_id) {
        super(username, password, telegram_chat_id);
    }

    /***
     * Gets Seller object with a username
     * @param name sellers username
     * @return seller with requested username
     */
    public static Seller getSellerFromName(String name) {
        for (Seller var:DB.getSellers()) {
            if (var.toString().equals(name) ) {
                return var;
            }
        }
        return (Seller) null;
    }

}
