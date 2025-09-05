import DAO.DAOBank;
import DAO.DAOException;
import DAO.DAOUser;
import GUI.PanelManager;
import Model.Bank;
import Model.User;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        /*
        Bank bank1 = new Bank("Chase Bank", "Mario Bravo 1050", "juanzimbi", "password");

        DAOBank daoBank = new DAOBank();

        try{
            daoBank.save(bank1);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
        }



        User user1 = new User("Juan Ignacio", "Zimbimbakis", "44700211", "colozim47@gmail.com", "1130654711", "juanzimbimbakis", "arthur");

        DAOUser daoUser = new DAOUser();

        try{
            daoUser.save(user1);
        } catch (DAOException e)
        {
            System.out.println(e.getMessage());
        }


         */


        PanelManager manager = new PanelManager();



    }


}