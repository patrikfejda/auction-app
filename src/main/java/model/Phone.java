package model;

/***
 * Class for storing Phone data
 */
public class Phone {

    private PhoneType phoneType;
    private User user;
    private String imei;
    private String state; // <0;100>
    private Boolean in_sale; // <0;100>

    public Phone(PhoneType phoneType, User user, String imei, String state) {
        this.phoneType = phoneType;
        this.user = user;
        this.imei = imei;
        this.state = state;
        this.in_sale = false;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "( " + phoneType + ", IMEI='" + imei +  " , state=" + state + " )";
    }

    public Boolean getIn_sale() {
        return in_sale;
    }

    public void setIn_sale(Boolean in_sale) {
        this.in_sale = in_sale;
    }

    /***
     *
     * @param name name of the phone
     * @param user user whose phone we are looking for
     * @return users phone wth same name as requested
     */
    public static Phone getPhoneFromName(String name, User user) {
        if (user instanceof Seller){
            Seller s = (Seller)user;
            for (Phone var:s.getPhones()) {
                if (var.toString().equals(name) ) {
                    return var;
                }
            }
        }
        else {
            Buyer b = (Buyer)user;
            for (Phone var:b.getPhones()) {
                if (var.toString().equals(name) ) {
                    return var;
                }
            }
        }
        return (Phone)null;
    }
}
