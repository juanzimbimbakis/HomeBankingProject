package Model;

public class Card {

    private String cardNumber;

    private double balance;

    private double expense;

    public Card(String cardNumber, double balance, double expense)
    {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.expense = expense;
    }

    public Card()
    {

    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }
}
