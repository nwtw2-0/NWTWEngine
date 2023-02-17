package NWTW.Engine;

import NWTW.Engine.Inventory.CraftInventory;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import NWTW.Engine.ScoreBoard.ScoreBoard;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.Item;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.utils.BossBarColor;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestListener implements Listener {
    @EventHandler
    public void join(PlayerChatEvent event) {
        CraftInventory craftInventory = NWTWEngineAPI.getInventoryManager().createInventory(InventoryType.CHEST,"我是標題");
        HashMap<Integer,Item> items = new HashMap<>();
        for (int i = 1; i < 20;i++){
            items.put(i,Item.get(i));
        }
        craftInventory.setContents(items);
        event.getPlayer().addWindow(craftInventory);
    }
}
