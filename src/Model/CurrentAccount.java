package Model;

public class CurrentAccount extends Account{

    public CurrentAccount(String id, String cbu, String alias, double balance, double debit, double credit)
    {
        super(id, cbu, alias, balance, debit, credit);
    }

    public CurrentAccount()
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

        if(getBalance() - monto >= -50000)
        {
            setBalance(getBalance() - monto);
            setCredit(getCredit() + monto);
            return true;
        } else
        {
            return false;
        }

    }

    @Override
    public void agregarDinero(double monto) {
        setBalance(getBalance() + monto);
        setDebit(getDebit() + monto);
    }
}
