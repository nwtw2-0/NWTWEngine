package NWTW.Engine.ScoreBoard;

import cn.nukkit.Player;
import cn.nukkit.plugin.Plugin;

import java.util.ArrayList;

public class ScoreBoardAPI {
    public static void registerScoreBoard(Plugin plugin,ScoreBoard scoreBoard){
        if (!ScoreBoard.scoreBoards.containsKey(plugin))ScoreBoard.scoreBoards.put(plugin,new ArrayList<>());
        ScoreBoard.scoreBoards.get(plugin).add(scoreBoard);
    }
    public static ScoreBoard getSelectScoreBoard(Player player){
        return ScoreBoard.selectScoreBoard.get(player);
    }
    public static void setSelectScoreBoard(Player player,ScoreBoard scoreBoard){
        ScoreBoard.selectScoreBoard.replace(player,scoreBoard);
    }
    public static void removeSelectScoreBoard(Player player){
        ScoreBoard.selectScoreBoard.remove(player);
    }
}
