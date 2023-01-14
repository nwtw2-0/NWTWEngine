package NWTW.Engine.CustomItem;

import NWTW.Engine.Utils;
import cn.nukkit.item.Item;
import lombok.Getter;


public record ModItem(@Getter Item item, @Getter boolean custom) {
    public ModItem(Item item, boolean custom) {
        this.custom = custom;
        this.item = item;
        item.getNamedTag().putByte("NwtwEngine", 1);
        item.getNamedTag().putBoolean("custom",custom);
    }
    public static boolean isModItem(Item item){
        return item.getNamedTag().contains("NwtwEngine");
    }

    @Override
    public String toString() {
        return item().getId()+":"+item.getDamage()+":"+item.getCount()+":"+ Utils.bytesToHexString(item.getCompoundTag());
    }
}
