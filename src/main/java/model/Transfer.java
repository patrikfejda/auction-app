package model;

import java.time.LocalDate;
import java.time.LocalTime;

/***
 * Abstract class for tranfers (Bank + Cash)
 * Tranfer changes the user wallet amount
 * Validates if the user has enough money for the withdrawal
 */
public abstract class Transfer {

    LocalDate date;
    LocalTime time;
    Integer amount;
    User user;
    Boolean sucess;

    public Transfer(Integer amount, User user) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.amount = amount;
        this.user = user;
        if (user.addAmount_to_Wallet_amount(amount)) {
            this.sucess = true;
        }
        else {
            this.sucess = false;
        }

        DB.addTransfer(this);
    }

    public Boolean getSucess() {
        return sucess;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "date=" + date +
                ", time=" + time +
                ", amount=" + amount +
                ", user=" + user +
                ", sucess=" + sucess +
                '}';
    }
}
