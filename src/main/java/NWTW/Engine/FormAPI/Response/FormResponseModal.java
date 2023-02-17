package NWTW.Engine.FormAPI.Response;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowModal;

public interface FormResponseModal extends FormResponse{
    void handle(Player targetPlayer, FormWindowModal targetForm,int id);
}
