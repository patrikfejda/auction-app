package model;

/***
 * Class for storing data about a Sale
 */
public class Sale {
    Seller seller;
    Phone phone;
    Integer starting_price;
    Integer step_price;
    Integer autosell_price;
    Integer actual_price;
    Buyer buyer;
    Boolean sold;


    public Sale(Seller seller, Phone phone, Integer starting_price, Integer step_price, Integer autosell_price) {
        this.seller = seller;
        this.phone = phone;
        this.starting_price = starting_price;
        this.step_price = step_price;
        this.autosell_price = autosell_price;
        this.actual_price = 0;
        this.sold = false;
        DB.sales.add(this);
    }

    public Integer getActual_price() {
        return actual_price;
    }

    public Boolean getSold() {
        return sold;
    }

    /***
     * Adding money to the sale. Always adding the step price. Autosell
     * @param buyer who is adding money
     * @return if autosell was sucessful
     */
    public Boolean addMoney(Buyer buyer) {
        if (this.sold) {
            System.out.println(">> PHONE / SALE IS ALREADY SOLD !!!");
            return false;
        }
        this.actual_price += this.step_price;
        this.buyer = buyer;
        // autosell
        if (this.actual_price >= this.autosell_price) {
            System.out.println("> AUTOSELLING!");
            this.actual_price = this.autosell_price;
            if (this.sell(buyer)) {
                System.out.println("> AUTOSELLING OK!");
            }
            else {
                System.out.println("> NOT ENOUGH MONEY!");
                return false;
            }
        }
        return true;
    }

    /***
     * Selling phone from Seller to Buyer
     * If the buyer doesnt have enough money, the function doesnt sell the phone
     * Deleting phone from sellers phones and adding it to the buyers
     * @param buyer who is buying
     * @return if the sell was sucessfull
     */
    public Boolean sell(Buyer buyer) {
        if (this.sold) {
            System.out.println(">> PHONE / SALE IS ALREADY SOLD !!!");
            return false;
        }
        if (!buyer.addAmount_to_Wallet_amount(-this.getActual_price())) {
            System.out.println(">> Buyer HAS NOT ENOUGH MONEY !!!");
            return false;
        }
        this.seller.addAmount_to_Wallet_amount(this.getActual_price());
        this.sold = true;
        System.out.println("Seller phones: "+this.seller.getPhones());
        this.seller.removePhone(this.phone);
        System.out.println("Seller phones: "+this.seller.getPhones());
        this.phone.setUser(buyer);
        this.buyer.addPhone(this.phone);
        return true;
    }

    /***
     * Starting the auction with the starting price
     * @param buyer who is buying
     */
    public void addFirstMoney(Buyer buyer) {
        if (this.sold) {
            System.out.println(">> PHONE / SALE IS ALREADY SOLD !!!");
            return;
        }
        this.actual_price = this.starting_price;
        this.buyer = buyer;
    }

    public Integer getAutosell_price() {
        return autosell_price;
    }

    public Seller getSeller() {
        return seller;
    }

    public Phone getPhone() {
        return phone;
    }

    public Integer getStarting_price() {
        return starting_price;
    }

    public Integer getStep_price() {
        return step_price;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setSeller(Seller seller) {
        if (this.sold) {
            System.out.println(">> PHONE / SALE IS ALREADY SOLD !!!");
            return;
        }
        this.seller = seller;
    }

    public void setPhone(Phone phone) {
        if (this.sold) {
            System.out.println(">> PHONE / SALE IS ALREADY SOLD !!!");
            return;
        }
        this.phone = phone;
    }

    public void setStarting_price(Integer starting_price) {
        if (this.sold) {
            System.out.println(">> PHONE / SALE IS ALREADY SOLD !!!");
            return;
        }
        this.starting_price = starting_price;
    }

    public void setStep_price(Integer step_price) {
        if (this.sold) {
            System.out.println(">> PHONE / SALE IS ALREADY SOLD !!!");
            return;
        }
        this.step_price = step_price;
    }

    public void setBuyer(Buyer buyer) {
        if (this.sold) {
            System.out.println(">> PHONE / SALE IS ALREADY SOLD !!!");
            return;
        }
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return phone + " starts at: " + starting_price + "e, step: " + step_price + "e, autosell: " + autosell_price + "e";
    }

    /***
     * Get the Sale object from name
     * @param name name of the sale
     * @return sale object with the name requested
     */
    public static Sale getSaleFromName(String name) {
        for (Sale var:DB.sales) {
            if (var.toString().equals(name) ) {
                return var;
            }
        }
        return (Sale)null;
    }
}
