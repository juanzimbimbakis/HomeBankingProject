package Model;

import java.util.ArrayList;

public class Bank {

    private String name;

    private String address;

    private String username;

    private String password;

    private ArrayList<User> arrayUser;


    public Bank(String name, String address, String username, String password)
    {
        this.name = name;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public Bank()
    {

    }

    public Bank(ArrayList<User> arrayUser)
    {
        this.arrayUser = arrayUser;
    }

    public User getUserOfArrayListOfUsers(String word)
    {
        User userAux = null;
        for(User user : arrayUser)
        {
            if (user.getDni().equals(word))
            {
                userAux = user;
            }
        }
        return userAux;
    }

    public ArrayList<User> updateMailOfUserFromArray(String word, String word2)
    {
        for(User user : arrayUser)
        {
            if(user.getDni().equals(word))
            {
                user.setEmail(word2);
            }
        }
        return arrayUser;
    }

    public ArrayList<User> updatePhoneNumberOfUserFromArray(String word, String word2)
    {
        for(User user : arrayUser)
        {
            if(user.getDni().equals(word))
            {
                user.setPhoneNumber(word2);
            }
        }
        return arrayUser;
    }

    public ArrayList<User> updateUsernameOfUserFromArray(String word, String word2)
    {
        for(User user : arrayUser)
        {
            if(user.getDni().equals(word))
            {
                user.setUsername(word2);
            }
        }
        return arrayUser;
    }

    public ArrayList<User> updatePasswordOfUserFromArray(String word, String word2)
    {
        for(User user : arrayUser)
        {
            if(user.getDni().equals(word))
            {
                user.setPassword(word2);
            }
        }
        return arrayUser;
    }

    public void addUser(User user)
    {
        arrayUser.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public ArrayList<User> getArrayUser() {
        return arrayUser;
    }

    public void setArrayUser(ArrayList<User> arrayUser) {
        this.arrayUser = arrayUser;
    }

    @Override
    public String toString() {
        return "Model.Bank{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
