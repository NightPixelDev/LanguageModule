package dev.lupluv.langmodule.mysql;

import dev.lupluv.langmodule.utils.Strings;

import java.sql.*;

public class MySQL {

    String host;
    String port;
    String database;
    String username;
    String password;
    Connection con;

    public MySQL(String host, String port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getCon() {
        return this.con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void connect() {
        if (!this.isConnected()) {
            System.out.println(Strings.getInstance().mysqlPrefix + "Connecting to the Database.");
            System.out.println(Strings.getInstance().mysqlPrefix + "Host: '" + this.host + "'");
            System.out.println(Strings.getInstance().mysqlPrefix + "Port: '" + this.port + "'");
            System.out.println(Strings.getInstance().mysqlPrefix + "Database: '" + this.database + "'");
            System.out.println(Strings.getInstance().mysqlPrefix + "Username: '" + this.username + "'");
            System.out.println(Strings.getInstance().mysqlPrefix + "Password: '" + this.password + "'");

            try {
                this.con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true&characterEncoding=utf8&useUnicode=true", this.username, this.password);
                System.out.println(Strings.getInstance().mysqlPrefix + "Successfully connected to the Database. ❤");
            } catch (SQLException var2) {
                System.out.println(Strings.getInstance().mysqlPrefix + "An error occurred while closing the Database Connection.");
                var2.printStackTrace();
            }
        }

    }

    public void disconnect() {
        if (this.isConnected()) {
            System.out.println(Strings.getInstance().mysqlPrefix + "Closing the Database Connection.");

            try {
                this.con.close();
                System.out.println(Strings.getInstance().mysqlPrefix + "Successfully closed Database Connection. ✘");
            } catch (SQLException var2) {
                System.out.println(Strings.getInstance().mysqlPrefix + "An error occurred while closing the Database Connection.");
                var2.printStackTrace();
            }
        }

    }

    public boolean isConnected() {
        return this.con != null;
    }

    public void update(String qry) {
        try {
            PreparedStatement ps = this.con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException var3) {
            System.out.println(Strings.getInstance().mysqlPrefix + "An error occurred while updating MySQL table.");
            var3.printStackTrace();
        }

    }

    public ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = this.con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException var3) {
            System.out.println(Strings.getInstance().mysqlPrefix + "An error occurred while reading MySQL table.");
            var3.printStackTrace();
            return null;
        }
    }

}
