package NWTW.Engine.FormAPI.Response;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowCustom;

import java.util.List;

public interface FormResponseCustom extends FormResponse{
    void handle(Player targetPlayer, FormWindowCustom targetForm, List<Object> data);
}
