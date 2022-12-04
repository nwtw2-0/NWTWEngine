package NWTW.Engine.ScoreBoard;

import cn.nukkit.Player;
import cn.nukkit.scheduler.Task;

import java.util.Map;

public class ScoreBoardUpdateTask extends Task {

    @Override
    public void onRun(int i) {
        for(Map.Entry<Player,ScoreBoard> entry:ScoreBoard.selectScoreBoard.entrySet()){
            entry.getValue().update(entry.getKey());
        }
    }

}
