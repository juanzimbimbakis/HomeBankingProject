package Model;


import java.util.ArrayList;

public abstract class Account {

    private String id;

    private String cbu;

    private String alias;

    private double balance; /*
                                Débito: Sale dinero de cuenta.
                                Crédito: Entra dinero a cuenta.
                            */

    private double debit;

    private double credit;

    private ArrayList<Card> arrayListcard;

    private ArrayList<Transfer> arrayTransfer;



    public Account(String id, String cbu, String alias, double balance, double debit, double credit) {
        this.id = id;
        this.cbu = cbu;
        this.alias = alias;
        this.balance = balance;
        this.debit = debit;
        this.credit = credit;
    }

    public void addCard(Card card)
    {
        arrayListcard.add(card);
    }

    public void addTransfer(Transfer transfer)
    {
        arrayTransfer.add(transfer);
    }

    public ArrayList<Transfer> getArrayTransfer() {
        return arrayTransfer;
    }

    public void setArrayTransfer(ArrayList<Transfer> arrayTransfer) {
        this.arrayTransfer = arrayTransfer;
    }

    public abstract boolean quitarDinero(double monto);

    public abstract void agregarDinero(double monto);

    public abstract void retirarDinero(double monto);


    public Account()
    {

    }

    /*
    public void addCard(Card card)
    {
        this.card = card;
    }

     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }


    /*
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

     */
}
