package NWTW.Engine.Storage;

import NWTW.Engine.NWTWEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql implements IStorage{
    private String host,user,password,database;
    private int port;
    private Connection connection;
    public MySql(String host, String user, String password, String database, int port) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
        this.port = port;
    }

    @Override
    public void init() {

    }

    @Override
    public void load() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database+"?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",user,password.replace("null",""));
            NWTWEngine.getPlugin().getLogger().alert("MYSQL連線成功");
        } catch (ClassNotFoundException e) {
            NWTWEngine.getPlugin().getLogger().alert("找不到Mysql類別");
        } catch (SQLException e) {
            NWTWEngine.getPlugin().getLogger().alert(e.getMessage());
        }
    }

    @Override
    public void save() {
        try {
            connection.close();
        } catch (SQLException e) {
            NWTWEngine.getPlugin().getLogger().alert(e.getMessage());
        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Connection getConnection() {
        return connection;
    }
}
