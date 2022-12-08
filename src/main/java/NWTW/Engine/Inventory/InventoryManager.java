package NWTW.Engine.Inventory;

import NWTW.Engine.Inventory.FakeBlock.DoubleFakeBlock;
import NWTW.Engine.Inventory.FakeBlock.FakeBlock;
import NWTW.Engine.Inventory.FakeBlock.SingleFakeBlock;
import cn.nukkit.block.Block;
import cn.nukkit.blockentity.BlockEntity;
import cn.nukkit.inventory.InventoryType;

import java.util.EnumMap;
import java.util.Map;

public class InventoryManager {
    private static final Map<InventoryType, FakeBlock> FAKE_BLOCKS = new EnumMap<>(InventoryType.class);

    public static FakeBlock getFakeBlock(InventoryType inventoryType) {
        return FAKE_BLOCKS.get(inventoryType);
    }
    static {
        FAKE_BLOCKS.put(InventoryType.CHEST, new SingleFakeBlock(Block.CHEST, BlockEntity.CHEST));
        FAKE_BLOCKS.put(InventoryType.ENDER_CHEST, new SingleFakeBlock(Block.ENDER_CHEST, BlockEntity.ENDER_CHEST));
        FAKE_BLOCKS.put(InventoryType.DOUBLE_CHEST, new DoubleFakeBlock(Block.CHEST, BlockEntity.CHEST));
        FAKE_BLOCKS.put(InventoryType.FURNACE, new SingleFakeBlock(Block.FURNACE, BlockEntity.FURNACE));
        FAKE_BLOCKS.put(InventoryType.BREWING_STAND, new SingleFakeBlock(Block.BREWING_STAND_BLOCK, BlockEntity.BREWING_STAND));
        FAKE_BLOCKS.put(InventoryType.HOPPER, new SingleFakeBlock(Block.HOPPER_BLOCK, BlockEntity.HOPPER));
    }
    public CraftInventory createInventory(InventoryType type,String title){
        return new CraftInventory(type,title);
    }
}
