package NWTW.Engine;

import NWTW.Engine.BossBar.BossBarManager;
import NWTW.Engine.CustomItem.CustomItemManager;
import NWTW.Engine.CustomSkin.SkinManager;
import NWTW.Engine.GeoIP.GeoIP;
import NWTW.Engine.Inventory.FakeInventoryListener;
import NWTW.Engine.Inventory.InventoryManager;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import NWTW.Engine.ScoreBoard.ScoreboardManager;
import NWTW.Engine.Translate.TranslateManager;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.AsyncTask;
import com.google.gson.Gson;

import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NWTWEngine extends PluginBase {
    private static NWTWEngine plugin;
    private ScoreboardManager scoreboardManager;
    private InventoryManager inventoryManager;
    private BossBarManager bossBarManager;
    private SkinManager skinManager;
    private GeoIP ipManager;
    private CustomItemManager customItemManager;
    private Gson gson;
    private TranslateManager translateManager;
    private Proxy proxy = Proxy.NO_PROXY;
    @Override
    public void onLoad() {
        plugin = this;
        super.onLoad();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        if (!getDataFolder().exists()) getDataFolder().mkdirs();
        PlaceHolderAPI placeHolderAPI = new PlaceHolderAPI();
        placeHolderAPI.registerDefaultPlaceHolder();
        ipManager =  new GeoIP(getDataFolder().toString());
        scoreboardManager = new ScoreboardManager();
        inventoryManager = new InventoryManager();
        bossBarManager = new BossBarManager();
        customItemManager = new CustomItemManager();
        gson = new Gson();
        skinManager = new SkinManager(getDataFolder().toPath().resolve("Skins"));
        if (getConfig().getBoolean("proxy.enable",false)){
            proxy = new Proxy(
                    Proxy.Type.HTTP,
                    new java.net.InetSocketAddress(
                            this.getConfig().getString("proxy.host"),
                            this.getConfig().getInt("proxy.port")
                    )
            );
            this.getLogger().info("Using a web proxy: " + this.proxy.toString());
        }
        translateManager = new TranslateManager();
        this.getServer().getScheduler().scheduleAsyncTask(this, new AsyncTask() {
            @Override
            public void onRun() {
                NWTWEngine.getPlugin().getLogger().info("Testing the translation function...");
                NWTWEngine.getPlugin().getLogger().info("Test translation results: Hello World! -> " + translateManager.translate("Hello World!"));
                NWTWEngine.getPlugin().getLogger().info("Test translation results: 你好 世界！ -> " + translateManager.translate("zh_CN", "en", "你好 世界！"));
            }
        });
        getServer().getScheduler().scheduleRepeatingTask(scoreboardManager.getTask(), 20);
        getServer().getPluginManager().registerEvents(new FakeInventoryListener(),this);
//        getServer().getPluginManager().registerEvents(new TestListener(),this);
        getLogger().info(getName()+"已經開啟");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTask(scoreboardManager.getTask().getTaskId());
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

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public GeoIP getIpManager() {
        return ipManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public BossBarManager getBossBarManager() {
        return bossBarManager;
    }

    public SkinManager getSkinManager() {
        return skinManager;
    }

    public Gson getGson() {
        return gson;
    }

    public TranslateManager getTranslateManager() {
        return translateManager;
    }

    public CustomItemManager getCustomItemManager() {
        return customItemManager;
    }

    public Proxy getProxy() {
        return proxy;
    }
}
