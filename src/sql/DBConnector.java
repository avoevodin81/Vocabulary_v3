package sql;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Test on 09.05.2016.
 */
public class DBConnector {

    private String host;
    private String root;
    private String password;
    private String nameDB;
    private String url;
    private Connection mysqlConnect;

    private Properties properties = new Properties();

    public DBConnector() {
        initProperties();
        initURL();
        init();
    }

    public Connection getMysqlConnect() {
        return mysqlConnect;
    }


    private void initURL() {
        url = "jdbc:mysql://" + host + "/" + nameDB;
    }

    public void initProperties() {
        try(FileInputStream fis = new FileInputStream("src/property/DBProp.properties")){
            properties.load(fis);
            host = properties.getProperty("db.host");
            root = properties.getProperty("db.root");
            password = properties.getProperty("db.password");
            nameDB = properties.getProperty("db.database");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
        }
        properties.setProperty("user", root);
        properties.setProperty("password", password);
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("useUnicode", "true");
        properties.setProperty("useSSL", "true");
    }

    public void init() {
        if (mysqlConnect == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                mysqlConnect = DriverManager.getConnection(url, properties);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
            }
        }
    }

    public void exitDB() {
        try {
            mysqlConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
        }
    }

    public ResultSet query(String query) {
        ResultSet result = null;
        try {
            Statement stmt = mysqlConnect.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
        }
        return result;
    }


    public void updateQuery(String query) {
        try {
            Statement stmt = mysqlConnect.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong! " + e.toString());
        }
    }
}
