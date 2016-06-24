import frame.NewUserFrame;
import managers.NewUserManager;
import managers.SendEmail;
import sql.DBConnector;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Created by Test on 13.06.2016.
 */
public class Test {
    public static void main(String[] args) {
//        String tableName = "word";
//        DBConnector con = new DBConnector();
//        ResultSet res = con.query("SELECT * FROM " + tableName);
//        try {
//            while (res.next()) {
//                int id = res.getInt("id");
//                String eng = res.getString(2);
//                String rus = res.getString(3);
//                boolean isNew = res.getBoolean(4);
//                int userID = res.getInt(5);
//
//                System.out.println(id + " - " + eng + " - " + rus + " - " + isNew + " - " + userID);
//                //System.out.println(eng);
//                //System.out.println(res.getString(1));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
//        }

        NewUserFrame newUserFrame = new NewUserFrame();

        //System.out.println(new Random().nextInt(10000));

//        SendEmail sendEmail = new SendEmail("a.voevodin@keymarket.com.ua");
//        sendEmail.send(sendEmail.getCheckMessage());

        //System.out.println(NewUserManager.isEmail("avoevodin81@gmail.com"));
    }
}
