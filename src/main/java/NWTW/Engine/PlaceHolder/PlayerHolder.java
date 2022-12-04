package NWTW.Engine.PlaceHolder;

import cn.nukkit.Player;

public class PlayerHolder implements IPlaceHolder{
    @Override
    public String getIdentifier() {
        return "player";
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        return null;
    }
}
