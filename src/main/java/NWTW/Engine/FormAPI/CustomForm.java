package NWTW.Engine.FormAPI;

import NWTW.Engine.FormAPI.Response.FormResponseCustom;
import cn.nukkit.Player;
import cn.nukkit.form.element.*;
import cn.nukkit.form.window.FormWindowCustom;

import java.util.List;

public class CustomForm extends Form{
    public CustomForm(FormWindowCustom form){
        this.window = form;
    }

    public CustomForm() {
        window = new FormWindowCustom("");
    }

    public CustomForm(String title) {
        window = new FormWindowCustom(title);
    }

    public void send(Player player, FormResponseCustom response){
        map.put(player, response);
        player.showFormWindow(window);
    }

    public CustomForm setTitle(String value) {
        ((FormWindowCustom) window).setTitle(value);
        return this;
    }

    public CustomForm addLabel(String value) {
        ((FormWindowCustom) window).addElement(new ElementLabel(value));
        return this;
    }

    public CustomForm addInput() {
        ElementInput element = new ElementInput("");
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addInput(String name) {
        ElementInput element = new ElementInput(name);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addInput(String name, String placeholder) {
        ElementInput element = new ElementInput(name, placeholder);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addInput(String name, String placeholder, String defaultText) {
        ElementInput element = new ElementInput(name, placeholder, defaultText);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addToggle() {
        ElementToggle element = new ElementToggle("");
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addToggle(String name) {
        ElementToggle element = new ElementToggle(name);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addToggle(String name, boolean defaultValue) {
        ElementToggle element = new ElementToggle(name, defaultValue);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addDropDown(String name, List<String> list) {
        ElementDropdown element = new ElementDropdown(name, list);
        ((FormWindowCustom) window).addElement(element);

        return this;
    }

    public CustomForm addDropDown(String name, List<String> list, int defaultValue) {
        ElementDropdown element = new ElementDropdown(name, list, defaultValue);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addSlider(String name, int min, int max) {
        ElementSlider element = new ElementSlider(name, min, max);
        ((FormWindowCustom) window).addElement(element);

        return this;
    }

    public CustomForm addSlider(String name, int min, int max, int step) {
        ElementSlider element = new ElementSlider(name, min, max, step, 3);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addSlider(String name, int min, int max, int step, int defaultValue) {
        ElementSlider element = new ElementSlider(name, min, max, step, defaultValue);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addStepSlider(String name, List<String> list) {
        ElementStepSlider element = new ElementStepSlider(name, list);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }

    public CustomForm addStepSlider(String name, List<String> list, int defaultStep) {
        ElementStepSlider element = new ElementStepSlider(name, list, defaultStep);
        ((FormWindowCustom) window).addElement(element);
        return this;
    }
}
