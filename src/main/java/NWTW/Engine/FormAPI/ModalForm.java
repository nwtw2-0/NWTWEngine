package NWTW.Engine.FormAPI;

import NWTW.Engine.FormAPI.Response.FormResponseModal;
import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindowModal;

public class ModalForm extends Form{
    public ModalForm(FormWindowModal form){
        this.window = form;
    }

    public ModalForm() {
        window = new FormWindowModal("", "", "", "");
    }

    public ModalForm(String title) {
        window = new FormWindowModal(title, "", "", "");
    }

    public ModalForm(String title, String content) {
        window = new FormWindowModal(title, content, "", "");
    }

    public ModalForm(String title, String content, String trueButton) {
        window = new FormWindowModal(title, content, trueButton, "");
    }

    public ModalForm(String title, String content, String trueButton, String falseButton) {
        window = new FormWindowModal(title, content, trueButton, falseButton);
    }

    public void send(Player player, FormResponseModal response){
        map.put(player, response);
        player.showFormWindow(window);
    }

    public ModalForm setTitle(String value) {
        ((FormWindowModal) window).setTitle(value);

        return this;
    }

    public ModalForm setContent(String value) {
        ((FormWindowModal) window).setContent(value);
        return this;
    }

    public ModalForm setButton1(String value) {
        ((FormWindowModal) window).setButton1(value);
        return this;
    }

    public ModalForm setButton2(String value) {
        ((FormWindowModal) window).setButton2(value);
        return this;
    }
}
