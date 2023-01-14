package NWTW.Engine.CustomItem;

import NWTW.Engine.NWTWEngine;
import cn.nukkit.Player;
import cn.nukkit.item.Item;
import cn.nukkit.item.customitem.ItemCustom;
import java.util.HashMap;

public class CustomItemManager {
    public HashMap<String, ModItem> itemMap;
    public CustomItemManager(){
        itemMap = new HashMap<>();
    }
    public void registerCustomItem(String name,ModItem item){
        if (item.isCustom()){
            ItemCustom itemCustom = (ItemCustom) item.getItem();
            Item.registerCustomItem(itemCustom.getClass());
        }
        itemMap.put(name,item);
        NWTWEngine.getPlugin().getLogger().info("自定義道具:"+name+"註冊完成");
    }
    public Item getCustomItem(String name){
        return itemMap.getOrDefault(name,null).getItem();
    }
    public void givePlayerItem(Player player,String name){
        player.getInventory().addItem(getCustomItem(name));
    }
}
