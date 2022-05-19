package model;

import java.util.ArrayList;

/***
 * Model class for Buyer user
 */
public class Buyer extends User implements PhoneOwner {
    ArrayList<Phone> phones = new ArrayList<Phone>();
    private String ico;
    private String dic;
    private String icdph;

    // IMPLICIT INTERFACE METHODS IMPLEMENTATION
    // WHAT IS IMPLICIT IMPLEMENTATION: https://www.c-sharpcorner.com/UploadFile/8911c4/implicit-and-explicit-interface-examples/
    public void addPhone(Phone phone) {
        phones.add(phone);
    }
    public void removePhone(Phone phone) {
        phones.remove(phone);
    }
    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public Buyer(String username, String password, String telegram_chat_id, String ico, String dic, String icdph) {
        super(username, password, telegram_chat_id);
        this.ico = ico;
        this.dic = dic;
        this.icdph = icdph;
    }

    public Buyer(String username, String password, String ico, String dic, String icdph) {
        super(username, password, "");
        this.ico = ico;
        this.dic = dic;
        this.icdph = icdph;
    }

    /***
     *
     * @param name username of a Buyer
     * @return a Buyer object which has the username
     */
    public static Buyer getBuyerFromName(String name) {
        for (Buyer var:DB.getBuyers()) {
            if (var.toString().equals(name) ) {
                return var;
            }
        }
        return (Buyer)null;
    }


}
