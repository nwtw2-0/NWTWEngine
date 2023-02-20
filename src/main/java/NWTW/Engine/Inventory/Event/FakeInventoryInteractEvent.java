package NWTW.Engine.Inventory.Event;

import NWTW.Engine.Inventory.CraftInventory;
import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;

public class FakeInventoryInteractEvent extends FakeInventoryEvent{
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }
    public FakeInventoryInteractEvent(CraftInventory inventory, Player player) {
        super(inventory, player);
    }
}
