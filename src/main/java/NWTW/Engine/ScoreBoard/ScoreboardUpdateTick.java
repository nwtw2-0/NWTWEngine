package NWTW.Engine.ScoreBoard;

import NWTW.Engine.NWTWEngine;
import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;

import java.util.Map;

public class ScoreboardUpdateTick extends Task {
    @Override
    public void onRun(int i) {
        for (Player player: NWTWEngine.getPlugin().getScoreboardManager().getSelectScoreboard().keySet()){
            if (!player.isConnected()||!player.isOnline()) NWTWEngine.getPlugin().getScoreboardManager().removePlayerSelectScoreboard(player);
        }
        for(Map.Entry<Player,ScoreBoard> entry : NWTWEngine.getPlugin().getScoreboardManager().getSelectScoreboard().entrySet()){
            entry.getValue().update(entry.getKey());
        }
    }
}
