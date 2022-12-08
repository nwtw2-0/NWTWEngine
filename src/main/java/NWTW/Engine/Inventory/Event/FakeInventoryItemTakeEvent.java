package NWTW.Engine.Inventory.Event;

import NWTW.Engine.Inventory.CraftInventory;
import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.item.Item;

public class FakeInventoryItemTakeEvent extends FakeInventoryEvent{
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }
    private Item item;
    private int slot;
    public FakeInventoryItemTakeEvent(CraftInventory inventory, Player player, Item item, int slot) {
        super(inventory, player);
        this.slot = slot;
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
