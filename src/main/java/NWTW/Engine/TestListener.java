package NWTW.Engine;

import NWTW.Engine.FormAPI.CustomForm;
import NWTW.Engine.FormAPI.SimpleForm;
import NWTW.Engine.Inventory.CraftInventory;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import NWTW.Engine.ScoreBoard.ScoreBoard;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementSlider;
import cn.nukkit.form.element.ElementStepSlider;
import cn.nukkit.inventory.InventoryType;
import cn.nukkit.item.Item;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.utils.BossBarColor;
import cn.nukkit.utils.TextFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;


public class TestListener implements Listener {
    @EventHandler
    public void join(BlockBreakEvent event) {
        CustomForm form = new CustomForm("標題");
        form.addInput("哈哈是我啦");
        form.addSlider("拉條",0,5,1);
        List<String> list = new Vector<>();
        for(int i = 0 ; i < 20;i++){
            list.add(String.valueOf((char) ('a'+i)));
        }
        form.addDropDown("測試選單條",list);
        form.addStepSlider("stepslider",list);
        form.send(event.getPlayer(),((targetPlayer, targetForm, data) -> {
            System.out.println(data);
            for (Object datum : data) targetPlayer.sendMessage(String.valueOf(datum));
        }));
    }
}
