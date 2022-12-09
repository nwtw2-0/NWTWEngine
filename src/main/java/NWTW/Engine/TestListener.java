package NWTW.Engine;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerToggleSneakEvent;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.utils.BossBarColor;


public class TestListener implements Listener {
    @EventHandler
    public void chat(PlayerChatEvent event){
      var bar =  NWTWEngine.getPlugin().getBossBarManager().createPlayerBossBar(event.getPlayer(), BossBarColor.PINK,event.getPlayer().getName(),20f);
      NWTWEngine.getPlugin().getBossBarManager().addBossBar(event.getPlayer(),bar,5);
    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event){

        event.getPlayer().addWindow(NWTWEngine.getPlugin().getInventoryManager().createInventory(InventoryType.DROPPER,"我妳他"));
    }
}
