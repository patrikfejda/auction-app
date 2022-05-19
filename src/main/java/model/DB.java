package model;
import java.util.ArrayList;

/***
 * Class which is used as a database using ArrayLists
 */
public class DB {

    static ArrayList<Phone> phones = new ArrayList<Phone>();
    static ArrayList<PhoneType> phoneTypes = new ArrayList<PhoneType>();
    static ArrayList<Seller> sellers = new ArrayList<Seller>();
    static ArrayList<Buyer> buyers = new ArrayList<Buyer>();
    static ArrayList<Admin> admins = new ArrayList<Admin>();
    static ArrayList<Sale> sales = new ArrayList<Sale>();
    static ArrayList<Transfer> transfers = new ArrayList<Transfer>();
    static User actual_user = (User)null;


    public static ArrayList<PhoneType> getPhoneTypes() {
        return phoneTypes;
    }

    public static ArrayList<Phone> getPhones() {
        return phones;
    }

    public static ArrayList<Seller> getSellers() {
        return sellers;
    }

    public static ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<Sale> getSales() {
        return sales;
    }

    public static ArrayList<Transfer> getTransfers() {
        return transfers;
    }

    public static User getActual_user() {
        return actual_user;
    }

    public static void setActual_user(User actual_user) {
        DB.actual_user = actual_user;
    }

    public static void addPhone(Phone phone) {
        phones.add(phone);
    }

    public static void addPhoneType(PhoneType phoneType) {
        phoneTypes.add(phoneType);
    }

    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public static void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public static void addBuyer(Buyer buyer) {
        buyers.add(buyer);
    }

    public static void addTransfer(Transfer transfer) {
        transfers.add(transfer);
    }

    public static void addSale(Sale sale) {
        sales.add(sale);
    }

}
