package NWTW.Engine.ScoreBoard;

import NWTW.Engine.NWTWEngine;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;

public class ScoreBoardTestListener implements Listener {
    @EventHandler
    public void onJoin(PlayerLocallyInitializedEvent event){
        ScoreBoardAPI.setSelectScoreBoard(event.getPlayer(), NWTWEngine.getPlugin().scoreBoard);
    }
}
