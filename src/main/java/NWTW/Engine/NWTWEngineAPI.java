package NWTW.Engine;

import NWTW.Engine.BossBar.BossBarManager;
import NWTW.Engine.CustomItem.CustomItemManager;
import NWTW.Engine.CustomSkin.SkinManager;
import NWTW.Engine.GeoIP.GeoIP;
import NWTW.Engine.Holograms.HologramManager;
import NWTW.Engine.Inventory.InventoryManager;
import NWTW.Engine.ScoreBoard.ScoreboardManager;
import NWTW.Engine.Translate.TranslateManager;
import com.google.gson.Gson;

public class NWTWEngineAPI {
    public static ScoreboardManager getScoreboardManager() {
        return NWTWEngine.getPlugin().getScoreboardManager();
    }

    public static GeoIP getIpManager() {
        return NWTWEngine.getPlugin().getIpManager();
    }

    public static InventoryManager getInventoryManager() {
        return NWTWEngine.getPlugin().getInventoryManager();
    }

    public static BossBarManager getBossBarManager() {
        return NWTWEngine.getPlugin().getBossBarManager();
    }

    public static SkinManager getSkinManager() {
        return NWTWEngine.getPlugin().getSkinManager();
    }

    public static Gson getGson() {
        return NWTWEngine.getPlugin().getGson();
    }

    public static TranslateManager getTranslateManager() {
        return NWTWEngine.getPlugin().getTranslateManager();
    }

    public static CustomItemManager getCustomItemManager() {
        return NWTWEngine.getPlugin().getCustomItemManager();
    }
    public static HologramManager getHologramManager(){
        return NWTWEngine.getPlugin().getHologramManager();
    }
}
