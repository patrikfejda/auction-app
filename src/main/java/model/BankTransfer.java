package model;

/***
 * Bank transfer allows withdraw or add money to wallet
 */
public class BankTransfer extends Transfer {

    String IBAN;
    String variable_symbol;

    /***
     * Creating Bank trasfer which adds/withdraws money from wallet
     * @param amount the amount which is added (>0) or withdrawn (<0)
     * @param user the user whose wallet amount is changing
     * @param IBAN the users IBAN
     * @param variable_symbol the Variable symbol of transaction
     */
    public BankTransfer(Integer amount, User user, String IBAN, String variable_symbol) {
        super(amount, user);
        this.IBAN = IBAN;
        this.variable_symbol = variable_symbol;
    }

    @Override
    public String toString() {
        return "BankTransfer{" +
                IBAN + '\'' +
                ", VS='" + variable_symbol + '\'' +
                ", date=" + date +
                " " + time +
                ", " + amount +
                "eur, user" + user +
                ", sucess=" + sucess +
                '}';
    }
}
