package NWTW.Engine.FormAPI;

import NWTW.Engine.FormAPI.Response.FormResponse;
import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindow;

import java.util.HashMap;

public abstract class Form {
    public static final HashMap<Player, FormResponse> map = new HashMap<>();
    protected FormWindow window;
    public void send(Player player){
        player.showFormWindow(window);
    }
}
