package NWTW.Engine;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.utils.BossBarColor;


public class TestListener implements Listener {
    @EventHandler
    public void chat(PlayerChatEvent event) {
        var bar = NWTWEngine.getPlugin().getBossBarManager().createPlayerBossBar(event.getPlayer(), BossBarColor.PINK, event.getPlayer().getName(), 20f);
        NWTWEngine.getPlugin().getBossBarManager().addBossBar(event.getPlayer(), bar, 5);
        NWTWEngine.getPlugin().getSkinManager().setSkin(event.getPlayer(),"小埋");
    }
}
