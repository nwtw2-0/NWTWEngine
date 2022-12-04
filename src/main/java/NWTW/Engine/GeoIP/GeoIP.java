package NWTW.Engine.GeoIP;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;

public class GeoIP {
    private File database;
    private static DatabaseReader reader;
    public GeoIP(String path){
        database = new File(path);
        if(!database.exists())
            database.mkdirs();
        database = new File(path,"GeoLite2-City.mmdb");
        if(!database.exists()) {
            try {
                Files.createFile(database.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            reader = new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getDatabase() {
        return database;
    }
    public static String getCountry(String ip){
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = reader.city(ipAddress);
            return response.getCountry().getName();
        } catch (IOException | GeoIp2Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String getCity(String ip){
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = reader.city(ipAddress);
            return response.getCity().getName();
        } catch (IOException | GeoIp2Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DatabaseReader getReader() {
        return reader;
    }
}
