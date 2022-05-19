package model;

/***
 * Class for storing data about a phone type
 */
public class PhoneType {


    private String name;
    private String skuName;
    private Integer ram;
    private Integer memory;
    private String color;

    public PhoneType(String name, String skuName, Integer ram, Integer memory, String color) {
        this.name = name;
        this.skuName = skuName;
        this.ram = ram;
        this.memory = memory;
        this.color = color;
        DB.phoneTypes.add(this);
    }

    /***
     * Create phone of this phone type and adds it to the DB
     * @param imei IMEI of the phone
     * @param user owner
     * @param state condition of the device
     * @return created phone
     */
    public Phone CreatePhone(String imei, User user, String state) {
        Phone phone = new Phone(this, user, imei, state);
        DB.phones.add(phone);
        if (user instanceof Seller) {
            Seller seller = (Seller)user;
            seller.addPhone(phone);
        }
        else {
            Buyer buyer = (Buyer)user;
            buyer.addPhone(phone);
        }
        return phone;
    }

    /***
     *
     * @param name name of the Phone Type
     * @return The phone type with requested name
     */
    public static PhoneType getPhoneTypeFromName(String name) {
         for (PhoneType var:DB.getPhoneTypes()) {
             if (var.toString() == name) {
                 return var;
             }
         }
         return (PhoneType)null;
    }

    public String getName() {
        return name;
    }

    public String getSkuName() {
        return skuName;
    }

    public Integer getRam() {
        return ram;
    }

    public Integer getMemory() {
        return memory;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return skuName;
    }
}
