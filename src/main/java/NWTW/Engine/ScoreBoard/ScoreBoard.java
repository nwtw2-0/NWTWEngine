package NWTW.Engine.ScoreBoard;

import NWTW.Engine.NWTWEngine;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.scoreboard.manager.IScoreboardManager;
import cn.nukkit.scoreboard.scoreboard.Scoreboard;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreBoard {
    public static final HashMap<Player,ScoreBoard> selectScoreBoard = new HashMap<>();
   public static final HashMap<Plugin, ArrayList<ScoreBoard>> scoreBoards = new HashMap<>();
   public static ScoreBoardUpdateTask task = new ScoreBoardUpdateTask();
   private String boardName;
   private final ArrayList<String> lines;
   private final Scoreboard scoreboard;
    public ScoreBoard(String boardName, String title, DisplaySlot slot) {
        this.boardName = boardName;
        scoreboard = new Scoreboard(boardName,title);
        IScoreboardManager manager = Server.getInstance().getScoreboardManager();
        if(manager.addScoreboard(scoreboard)){
            NWTWEngine.getPlugin().getLogger().info("積分版"+boardName+"創建成功");
        }
        manager.setDisplay(slot,scoreboard);
        lines = new ArrayList<>();
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
    public void update(Player player){
        ArrayList<String> list = new ArrayList<>();
        lines.forEach(item ->{
            list.add(PlaceHolderAPI.translate(player,item));
        });
        scoreboard.setLines(list);
    }

    public ArrayList<String> getLines() {
        return lines;
    }
    public void addLine(String line){
        lines.add(line);
    }
}
