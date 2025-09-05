package Model;


public class SavingsAccount extends Account {

    public SavingsAccount(String id, String cbu, String alias, double balance, double debit, double credit)
    {
        super(id, cbu, alias, balance, debit, credit);
    }

    public SavingsAccount()
    {
        super();
    }

    @Override
    public void retirarDinero(double monto) {
        setBalance(getBalance() - monto);
        setCredit(getCredit() + monto);
    }

    @Override
    public boolean quitarDinero(double monto) {

        if (getBalance() < monto)
        {
            return false;
        } else
        {
            setBalance(getBalance() - monto);
            setCredit(getCredit() + monto);
            return true;
        }


    }

    @Override
    public void agregarDinero(double monto) {
        setBalance(getBalance() + monto);
        setDebit(getDebit() + monto);
    }
}
