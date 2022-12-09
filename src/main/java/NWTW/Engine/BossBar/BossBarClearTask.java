package NWTW.Engine.BossBar;

import NWTW.Engine.NWTWEngine;
import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;

public class BossBarClearTask extends Task {
    private final Player player;
    private final Long id;
    public BossBarClearTask(Player player, Long id) {
        this.player = player;
        this.id = id;
    }

    @Override
    public void onRun(int i) {
        NWTWEngine.getPlugin().getBossBarManager().removeBossBar(player,id);
    }
}
