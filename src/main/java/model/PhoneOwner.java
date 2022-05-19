package model;

import java.util.ArrayList;

/***
 * Abstract class which allows object to own a Phone (add, remove, get)
 */
public interface PhoneOwner {

    public void addPhone(Phone phone);

    public void removePhone(Phone phone);

    public ArrayList<Phone> getPhones();
}
