package NWTW.Engine;

import NWTW.Engine.GeoIP.GeoIP;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import cn.nukkit.plugin.PluginBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NWTWEngine extends PluginBase {
    private static NWTWEngine plugin;
    private GeoIP geoIP;

    @Override
    public void onLoad() {
        plugin = this;
        super.onLoad();
    }

    @Override
    public void onEnable() {
        PlaceHolderAPI placeHolderAPI = new PlaceHolderAPI();
        placeHolderAPI.registerDefaultPlaceHolder();
        geoIP = new GeoIP(getDataFolder().toString());
        getLogger().info(getName()+"已經開啟");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        getLogger().info(getName()+"已經關閉");
        super.onDisable();
    }

    public static NWTWEngine getPlugin() {
        return plugin;
    }
    public static String getStringDate(String str) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(str);
        return formatter.format(currentTime);
    }
}
