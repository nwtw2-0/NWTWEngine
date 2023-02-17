package NWTW.Engine.FormAPI.Response;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowSimple;

public interface FormResponseSimple extends FormResponse{
    void handle(Player targetPlayer, FormWindowSimple targetForm,int id);
}
