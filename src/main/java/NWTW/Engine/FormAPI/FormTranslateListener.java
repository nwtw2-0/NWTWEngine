package NWTW.Engine.FormAPI;

import NWTW.Engine.FormAPI.Response.FormResponseCustom;
import NWTW.Engine.FormAPI.Response.FormResponseModal;
import NWTW.Engine.FormAPI.Response.FormResponseSimple;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.form.window.FormWindowSimple;

import java.util.ArrayList;
import java.util.List;

public class FormTranslateListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void FormTranslate(PlayerFormRespondedEvent event){
        Player player = event.getPlayer();
        FormWindow window = event.getWindow();
        FormResponse response = window.getResponse();

        if (Form.map.containsKey(player)) {
            NWTW.Engine.FormAPI.Response.FormResponse temp =  Form.map.get(player);
            Form.map.remove(player);

            Object data;

            if (response == null || event.wasClosed()) {
                if(temp instanceof FormResponseCustom){
                    ((FormResponseCustom) temp).handle(player, (FormWindowCustom) window, null);

                }else if(temp instanceof FormResponseModal) {
                    ((FormResponseModal) temp).handle(player, (FormWindowModal) window,  -1);

                }else if(temp instanceof FormResponseSimple){
                    ((FormResponseSimple) temp).handle(player, (FormWindowSimple) window, -1);
                }
                return;
            }

            if (window instanceof FormWindowSimple) {
                data = ((cn.nukkit.form.response.FormResponseSimple) response).getClickedButtonId();
                ((FormResponseSimple) temp).handle(player, (FormWindowSimple) window, (int) data);
                return;
            }

            if (window instanceof FormWindowCustom) {
                data = new ArrayList<>(((cn.nukkit.form.response.FormResponseCustom) response).getResponses().values());
                ((FormResponseCustom) temp).handle(player, (FormWindowCustom) window, (List<Object>) data);
                return;
            }

            if (window instanceof FormWindowModal) {
                data = ((cn.nukkit.form.response.FormResponseModal) response).getClickedButtonId();
                ((FormResponseModal) temp).handle(player, (FormWindowModal) window, (int) data);
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void playerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Form.map.remove(player);
    }
}
