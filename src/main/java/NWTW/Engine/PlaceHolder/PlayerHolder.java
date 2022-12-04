package NWTW.Engine.PlaceHolder;

import cn.nukkit.Player;

public class PlayerHolder implements IPlaceHolder{
    @Override
    public String getIdentifier() {
        return "player";
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if(p == null) return "";
        return switch (identifier) {
            case "allow_flight" -> String.valueOf(p.getAllowFlight());
            case "armor_helmet_name" -> p.getInventory().getHelmet().getName();
            case "armor_helmet_data" -> String.valueOf(p.getInventory().getHelmet().getDamage());
            case "armor_chestplate_name" -> p.getInventory().getChestplate().getName();
            case "name" -> p.getName();
            case "uuid" -> p.getUniqueId().toString();
            case "x"-> String.valueOf(p.getLocation().getX());
            case "y"-> String.valueOf(p.getLocation().getY());
            case "z"-> String.valueOf(p.getLocation().getZ());
            case "yaw"-> String.valueOf(p.getLocation().yaw);
            case "pitch"-> String.valueOf(p.getLocation().pitch);
            case "max_health"->String.valueOf(p.getMaxHealth());
            case "health"-> String.valueOf(p.getHealth());
            case "world"->p.getLevelName();
            default -> null;
        };
    }
}
