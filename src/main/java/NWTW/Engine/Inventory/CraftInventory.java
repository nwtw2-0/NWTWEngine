package NWTW.Engine.Inventory;

import NWTW.Engine.Inventory.Event.FakeInventoryCloseEvent;
import NWTW.Engine.Inventory.Event.FakeInventoryOpenEvent;
import NWTW.Engine.Inventory.FakeBlock.FakeBlock;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.inventory.ContainerInventory;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.ContainerClosePacket;
import cn.nukkit.network.protocol.ContainerOpenPacket;

public class CraftInventory extends ContainerInventory {

    private final FakeBlock fakeBlock;
    private String title;
    public CraftInventory(InventoryType inventoryType) {
        this(inventoryType, null);
    }

    public CraftInventory(InventoryType inventoryType, String title) {
        super(null, inventoryType);
        this.title = title == null ? inventoryType.getDefaultTitle() : title;
        this.fakeBlock = InventoryManager.getFakeBlock(inventoryType);
    }

    @Override
    public void onOpen(Player player) {
        FakeInventoryOpenEvent event = new FakeInventoryOpenEvent(this,player);
        Server.getInstance().getPluginManager().callEvent(event);
        if (!event.isCancelled()){
            this.setContents(event.getInventory().getContents());
            this.setTitle(event.getInventory().getTitle());
        }else{
            return;
        }
        this.fakeBlock.create(player, this.getTitle());
        Server.getInstance().getScheduler().scheduleDelayedTask(() -> {
            ContainerOpenPacket packet = new ContainerOpenPacket();
            packet.windowId = player.getWindowId(this);
            packet.type = this.getType().getNetworkType();

            Vector3 position = this.fakeBlock.getPositions(player).get(0);
            packet.x = position.getFloorX();
            packet.y = position.getFloorY();
            packet.z = position.getFloorZ();
            player.dataPacket(packet);

            super.onOpen(player);

            this.sendContents(player);
        }, 3);
    }

    @Override
    public void onClose(Player player) {
        Server.getInstance().getPluginManager().callEvent(new FakeInventoryCloseEvent(this,player));
        ContainerClosePacket packet = new ContainerClosePacket();
        packet.windowId = player.getWindowId(this);
        packet.wasServerInitiated = player.getClosingWindowId() != packet.windowId;
        player.dataPacket(packet);
        super.onClose(player);
        this.fakeBlock.remove(player);
    }


    @Override
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
