package model;

/***
 * Cash transfer allows withdraw or add money to wallet
 * It is done manually by admin
 */
public class CashTransfer extends Transfer {


    Admin made_by;

    /***
     *
     * @param amount the amount which is added (>0) or withdrawn (<0)
     * @param user the user whose wallet amount is changing
     * @param made_by admin who made the transaction
     */
    public CashTransfer(Integer amount, User user, Admin made_by) {
        super(amount, user);
        this.made_by = made_by;
    }


    @Override
    public String toString() {
        return "CASH{" +
                date +
                " " + time +
                ", " + amount +
                "eur, user=" + user +
                ", made_by=" + made_by +
                ", sucess=" + sucess +
                '}';
    }
}
