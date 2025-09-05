package Model;

import javax.swing.*;
import java.util.ArrayList;

public class User {

    private String name;

    private String surname;

    private String dni;

    private String email;

    private String phoneNumber;

    private String username;

    private String password;

    private ArrayList<Account> arrayAccount;

    public User(String name, String surname, String dni, String email, String phoneNumber, String username, String password)
    {
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }


    public User(ArrayList<Account> arrayAccount)
    {
        this.arrayAccount = arrayAccount;
    }

    public User()
    {

    }

    /*
    public boolean acreditarDinero(String cbu, Double monto)
    {
        Boolean bool;
        for(Account account : arrayAccount)
        {
            if(account.getCbu().equals(cbu))
            {
                bool = verificarDinero(cbu, monto);
                if(bool)
                {
                    account.setBalance(account.getBalance() - monto);
                    account.setCredit(account.getCredit() + monto);
                    return true;
                } else
                {
                    return false;
                }
            }
        }
        return false;
    }

     */

    public boolean quitarDinero(String cbu, double monto, String destino)
    {
        boolean k;
        for(Account account : arrayAccount)
        {
            if(account.getCbu().equals(cbu))
            {
                System.out.println("Entramos al for!");
                k = account.quitarDinero(monto);
                if(k)
                {
                    quitarDinero2(monto, destino);
                    return true;
                } else
                {
                    return false;
                }
            }

        }
        System.out.println("Llamando quitar dinero!!!");
        return false;
    }

    public void quitarDinero2(double monto, String destino)
    {
        for(Account account : arrayAccount)
        {
            if(account.getId().equals(destino) || account.getCbu().equals(destino) || account.getAlias().equals(destino))
            {
                System.out.println("Entramos al if 90!");
                account.agregarDinero(monto);
            }
        }
    }

    public boolean verificarDinero(String cbu, Double monto)
    {
        for(Account account : arrayAccount)
        {
            if(account.getCbu().equals(cbu))
            {
                if(account.getBalance() < monto)
                {
                    return false;
                } else
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void addAccount(Account account)
    {
        arrayAccount.add(account);
    }

    public void setArrayAccount(ArrayList<Account> arrayAccount)
    {
        this.arrayAccount = arrayAccount;
    }

    public ArrayList<Account> getArrayAccount()
    {
        return arrayAccount;
    }

    public ArrayList<CurrentAccount> getCurrentsAccounts()
    {
        ArrayList<CurrentAccount> arrayAux = new ArrayList<>();
        for(Account account : arrayAccount)
        {
            if (account instanceof CurrentAccount)
            {
                arrayAux.add((CurrentAccount) account);
            }
        }
        return arrayAux;
    }

    public ArrayList<SavingsAccount> getSavingsAccount()
    {
        System.out.println("Llamando getSavingsAccount!!!");
        ArrayList<SavingsAccount> arrayAux = new ArrayList<>();
        for(Account account : arrayAccount)
        {
            if (account instanceof SavingsAccount)
            {
                arrayAux.add((SavingsAccount) account);
                System.out.println("Agregamos un savings account!!!");
            }
        }

        return arrayAux;
    }

    public CurrentAccount retirarDineroCA(ArrayList<Account> array, String cbu, Double monto)
    {
        for(Account account : array)
        {
            if (account instanceof CurrentAccount && account.getCbu().equals(cbu))
            {
                CurrentAccount temp = (CurrentAccount) account;
                if (temp.getBalance() < monto)
                {
                    JOptionPane.showMessageDialog(null, "You do not have enough money");
                    return null;
                } else
                {
                    temp.retirarDinero(monto);
                    return temp;
                }

            }
        }
        return null;
    }

    /*
    public CurrentAccount retirarDineroCA(ArrayList<Account> array, String cbu, Double monto)
    {
        for(Account account : array)
        {
            if (account instanceof CurrentAccount && account.getCbu().equals(cbu))
            {
                CurrentAccount temp = (CurrentAccount) account;
                if (temp.getBalance() < monto)
                {
                    JOptionPane.showMessageDialog(null, "You do not have enough money");
                    return null;
                } else
                {
                    temp.setBalance(temp.getBalance() - monto);
                    temp.setDebit(temp.getDebit() + monto);
                    return temp;
                }

            }
        }
        return null;
    }

     */

    public SavingsAccount retirarDineroSA(ArrayList<SavingsAccount> array, String cbu, Double monto)
    {
        for(Account account : array)
        {
            if (account instanceof SavingsAccount && account.getCbu().equals(cbu)) {
                SavingsAccount temp = (SavingsAccount) account;
                if (temp.getBalance() < monto) {
                    JOptionPane.showMessageDialog(null, "You do not have enough money");
                    return null;
                } else {
                    temp.retirarDinero(monto);
                    return temp;
                }
            }
        }
        return null;
    }

    public CurrentAccount getCurrentAccount(String cbu)
    {
        CurrentAccount temp = null;
        for(Account account : arrayAccount)
        {
            if(account.getCbu().equals(cbu))
            {
                temp = (CurrentAccount) account;
                return temp;
            }
        }
        return null;
    }

    public SavingsAccount getSavingAccount(String cbu)
    {
        SavingsAccount temp = null;
        for(Account account : arrayAccount)
        {
            if(account.getCbu().equals(cbu))
            {
                temp = (SavingsAccount) account;
                return temp;
            }
        }
        return null;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "arrayAccount=" + arrayAccount +
                '}';
    }
}
