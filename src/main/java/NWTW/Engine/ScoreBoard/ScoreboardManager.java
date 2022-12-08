package NWTW.Engine.ScoreBoard;

import NWTW.Engine.NWTWEngine;
import cn.nukkit.Player;
import cn.nukkit.plugin.Plugin;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreboardManager {
    @Getter
    private final HashMap<Plugin, List<ScoreBoard>> registerScoreboard = new HashMap<>();
    private final ScoreboardUpdateTick task = new ScoreboardUpdateTick();
    @Getter
    private final HashMap<Player,ScoreBoard> selectScoreboard = new HashMap<>();
    public void registerScoreboard(Plugin plugin,ScoreBoard scoreBoard){
        if (!registerScoreboard.containsKey(plugin)) registerScoreboard.put(plugin,new ArrayList<>());
        registerScoreboard.get(plugin).add(scoreBoard);
        NWTWEngine.getPlugin().getLogger().info(plugin.getName()+"的積分版:"+scoreBoard.getName()+"註冊完成");
    }
    public void setPlayerSelectScoreboard(Player player,ScoreBoard scoreBoard){
        if (!isRegister(scoreBoard)){
            NWTWEngine.getPlugin().getLogger().alert(scoreBoard.getName()+"未進行註冊");
            return;
        }
        if (!selectScoreboard.containsKey(player)){
            selectScoreboard.put(player,null);
        }else {
            getPlayerSelectScoreboard(player).removeScoreBoard(player);
        }
        selectScoreboard.replace(player,scoreBoard);
    }
    public ScoreBoard getPlayerSelectScoreboard(Player player){
        return selectScoreboard.getOrDefault(player,null);
    }
    public void removePlayerSelectScoreboard(Player player){
        getPlayerSelectScoreboard(player).removeScoreBoard(player);
        selectScoreboard.remove(player);
    }

    public ScoreboardUpdateTick getTask() {
        return task;
    }
    public boolean isRegister(ScoreBoard scoreBoard){
        for(List<ScoreBoard> scoreBoards : registerScoreboard.values()){
            if (scoreBoards.contains(scoreBoard)) return true;
        }
        return false;
    }
    public ScoreBoard createScoreboard(){
        return new ScoreBoard();
    }
}
