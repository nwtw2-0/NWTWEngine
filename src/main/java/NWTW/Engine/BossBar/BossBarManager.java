package NWTW.Engine.BossBar;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.BossBarColor;
import cn.nukkit.utils.DummyBossBar;

public class BossBarManager {

    public DummyBossBar createPlayerBossBar(Player player, BossBarColor color,String text,float length){
        return new DummyBossBar.Builder(player).color(color).length(length).text(text).build();
    }
    public void addBossBar(Player player,DummyBossBar bar,int time){
        player.createBossBar(bar);
        Server.getInstance().getScheduler().scheduleDelayedTask(new BossBarClearTask(player, bar.getBossBarId()),20*time);
    }
    public void removeBossBar(Player player,Long id){
        player.getDummyBossBar(id).destroy();
    }
}
