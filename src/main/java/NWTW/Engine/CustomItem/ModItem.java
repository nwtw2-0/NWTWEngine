package NWTW.Engine.CustomItem;

import cn.nukkit.item.Item;
import lombok.Getter;

public record ModItem(@Getter Item item, @Getter boolean custom) {
    public ModItem(Item item, boolean custom) {
        this.custom = custom;
        this.item = item;
        item.getNamedTag().putByte("NwtwEngine", 1);
    }
    public static boolean isModItem(Item item){
        return item.getNamedTag().contains("NwtwEngine");
    }
}
