package NWTW.Engine.Storage;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDB implements IStorage{
    private String host,db;
    private int port;
    private DB database;
    private MongoClient client;
    public MongoDB(String host, int port, String db) {
        this.host = host;
        this.port = port;
        this.db = db;
    }

    @Override
    public void init() {
        client = new MongoClient(host, port);

    }

    @Override
    public void load() {
        database = client.getDB(db);
    }

    @Override
    public void save() {
        database = null;
        client.close();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public DB getDatabase() {
        return database;
    }

    public void setDatabase(DB database) {
        this.database = database;
    }
}
