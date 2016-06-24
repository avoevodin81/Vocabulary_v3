package managers;

import interfaces.Insertable;
import interfaces.Selectable;
import items.Item;
import items.User;
import sql.DBConnector;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Test on 13.06.2016.
 */
public class NewUserManager implements Insertable, Selectable {

    private User user;
    private DBConnector connector;
    private final String nameTable = "user";

    public NewUserManager(String email, String password) {
        user = new User(email, password);
    }

    @Override
    public void closeDB(DBConnector connector) {
        connector.exitDB();
    }

    @Override
    public void insert(Item item) {
        user = (User) item;
        connector = new DBConnector();
        connector.updateQuery(String.format("INSERT INTO %s (email, password) values ('%s', '%s')",nameTable, user.getEmail(), user.getPassword()));
        closeDB(connector);
    }

    public static boolean isEmail(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isNewEmail() {
        ArrayList<Item> items = select(nameTable);
        for (Item item : items) {
            User u = (User) item;
            if (u.getEmail().equals(user.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public boolean isCorrectPassword(){
        ArrayList<Item> items = select(nameTable);
        for (Item item : items) {
            User u = (User) item;
            if (u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Item> select(String nameDB) {
        ArrayList<Item> items = new ArrayList<>();
        connector = new DBConnector();
        ResultSet res = connector.query("SELECT * FROM " + nameDB);
        try {
            while (res.next()) {
                items.add(new User(res.getString("email"), res.getString("password")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
        } finally {
            closeDB(connector);
        }
        return items;
    }

    public User getUser() {
        return user;
    }
}
