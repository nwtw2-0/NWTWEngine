package NWTW.Engine.Storage;

import NWTW.Engine.NWTWEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite implements IStorage{
    protected String path;
    private String filename;
    protected File file;
    private Connection connection;
    public SQLite(String path,String filename){
        this.path = path;
        this.filename = filename;
    }
    @Override
    public void init() {
        file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        file = new File(path,filename);

        try {
            if(!file.exists())
                Files.createFile(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void load() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+file.toString());
            NWTWEngine.getPlugin().getLogger().alert(file.getName()+"讀取成功");
        } catch (ClassNotFoundException e) {
            NWTWEngine.getPlugin().getLogger().alert("找不到SQLite類別");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
