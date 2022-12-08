package NWTW.Engine.Inventory.Event;

import NWTW.Engine.Inventory.CraftInventory;
import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;

public abstract class FakeInventoryEvent extends Event implements Cancellable {
    private final Player player;
    private final CraftInventory inventory;
    private boolean isCancelled;
    public FakeInventoryEvent(CraftInventory inventory, Player player) {
        this.inventory = inventory;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public CraftInventory getInventory() {
        return inventory;
    }
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
