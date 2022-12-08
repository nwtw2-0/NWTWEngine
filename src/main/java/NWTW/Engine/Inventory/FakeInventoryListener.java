package NWTW.Engine.Inventory;

import NWTW.Engine.Inventory.Event.FakeInventoryItemInputEvent;
import NWTW.Engine.Inventory.Event.FakeInventoryItemTakeEvent;
import cn.nukkit.Server;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryTransactionEvent;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import cn.nukkit.item.Item;

public class FakeInventoryListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryTransaction(InventoryTransactionEvent event) {
        event.getTransaction().getActions().forEach(action -> {
            if (action instanceof SlotChangeAction slotChange) {
                if (slotChange.getInventory() instanceof CraftInventory inventory) {
                    int slot = slotChange.getSlot();
                    Item sourceItem = action.getSourceItem();
                    Item target = action.getTargetItem();
                    if (sourceItem.getId() == Item.AIR){
                        FakeInventoryItemInputEvent event1 = new FakeInventoryItemInputEvent(inventory,event.getTransaction().getSource(),target,slot);
                        Server.getInstance().getPluginManager().callEvent(event1);
                        if (event1.isCancelled()){
                            event.setCancelled(true);
                        }
                    }else if(target.getId() == Item.AIR){
                        FakeInventoryItemTakeEvent event1 = new FakeInventoryItemTakeEvent(inventory,event.getTransaction().getSource(),sourceItem,slot);
                        Server.getInstance().getPluginManager().callEvent(event1);
                        if (event1.isCancelled()){
                            event.setCancelled(true);
                        }
                    }else if(target.getId() !=Item.AIR && sourceItem.getId() != Item.AIR){
                        FakeInventoryItemInputEvent event1 = new FakeInventoryItemInputEvent(inventory,event.getTransaction().getSource(),target,slot);
                        Server.getInstance().getPluginManager().callEvent(event1);
                        if (event1.isCancelled()){
                            event.setCancelled(true);
                        }
                        FakeInventoryItemTakeEvent event2 = new FakeInventoryItemTakeEvent(inventory,event.getTransaction().getSource(),sourceItem,slot);
                        Server.getInstance().getPluginManager().callEvent(event2);
                        if (event2.isCancelled()){
                            event.setCancelled(true);
                        }
                    }
                }
            }
        });
    }
}
