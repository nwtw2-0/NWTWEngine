package NWTW.Engine.GeoIP;
import NWTW.Engine.NWTWEngine;
import cn.nukkit.Server;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;

import java.io.*;
import java.net.InetAddress;
public class GeoIP {
    private final File database;
    private DatabaseReader reader;
    public GeoIP(String path){
        database = new File(path,"GeoLite2-City.mmdb");
        if(!database.exists()) {
            NWTWEngine.getPlugin().getLogger().alert("請安裝GeoIP的db數據庫 位置: https://www.dropbox.com/s/a7mh6anpsqard4h/GeoLite2-City.mmdb?dl=0");
            Server.getInstance().getPluginManager().disablePlugin(NWTWEngine.getPlugin());
            return;
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
    public String getCountry(String ip){
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CityResponse response = reader.city(ipAddress);
            return response.getCountry().getName();
        } catch (IOException | GeoIp2Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getCity(String ip){
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
