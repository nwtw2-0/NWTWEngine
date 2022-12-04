package NWTW.Engine.ScoreBoard;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerQuitEvent;

public class ScoreBoardListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuite(PlayerQuitEvent event){
        if (ScoreBoard.selectScoreBoard.containsKey(event.getPlayer()))
            ScoreBoard.selectScoreBoard.remove(event.getPlayer());
    }
}
