package NWTW.Engine.PlaceHolder;

import cn.nukkit.Player;

public interface IPlaceHolder {
    String getIdentifier();
    String onPlaceholderRequest(Player p, String identifier);
}
