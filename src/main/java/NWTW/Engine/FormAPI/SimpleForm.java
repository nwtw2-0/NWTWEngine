package NWTW.Engine.FormAPI;

import NWTW.Engine.FormAPI.Response.FormResponseSimple;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

public class SimpleForm extends Form{
    public SimpleForm(FormWindowSimple simple){
        window = simple;
    }
    public SimpleForm(){
        window = new FormWindowSimple("","");
    }
    public SimpleForm(String title){
        window = new FormWindowSimple(title,"");
    }
    public SimpleForm(String title,String content){
        window = new FormWindowSimple(title,content);
    }

    public void send(Player player, FormResponseSimple response) {
        map.put(player,response);
        player.showFormWindow(window);
    }
    public SimpleForm setTitle(String value) {
        ((FormWindowSimple) window).setTitle(value);
        return this;
    }

    public SimpleForm setContent(String value) {
        ((FormWindowSimple) window).setContent(value);
        return this;
    }

    public SimpleForm addContent(String value){
        ((FormWindowSimple) window).setContent(((FormWindowSimple) window).getContent() + value);
        return this;
    }

    public SimpleForm addContentLine(String value){
        return addContent(value + "\n");
    }

    public SimpleForm addContentOnNextLine(String value){
        return addContent("\n" + value);
    }

    public SimpleForm addButton(String text) {
        ((FormWindowSimple) window).addButton(new ElementButton(text));
        return this;
    }

    public SimpleForm addButton(String text, ImageType type, String ico) {
        ElementButton button = new ElementButton(text);
        button.addImage(new ElementButtonImageData((type == ImageType.PATH) ? "path" : "url", ico));
        ((FormWindowSimple) window).addButton(button);
        return this;
    }
}
