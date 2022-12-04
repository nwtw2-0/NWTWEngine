package NWTW.Engine;

import cn.nukkit.plugin.PluginBase;

public class NWTWEngine extends PluginBase {
    private static NWTWEngine plugin;
    @Override
    public void onLoad() {
        plugin = this;
        super.onLoad();
    }

    @Override
    public void onEnable() {
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
}
