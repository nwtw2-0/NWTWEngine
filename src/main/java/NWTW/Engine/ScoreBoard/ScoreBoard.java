package NWTW.Engine.ScoreBoard;

import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import cn.nukkit.Player;
import cn.nukkit.network.protocol.RemoveObjectivePacket;
import cn.nukkit.network.protocol.SetDisplayObjectivePacket;
import cn.nukkit.network.protocol.SetScorePacket;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.scoreboard.data.SortOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {
    @Getter
    @Setter
    private  String title;
    @Setter
    @Getter
    private  String name;
    private final Map<Integer, ScoreBoardLine> lines = new HashMap<>();
    @Setter
    @Getter
    private  DisplaySlot displaySlot;
    public ScoreBoard(String title, String name, DisplaySlot displaySlot) {
        this.title = title;
        this.name = name;
        this.displaySlot = displaySlot;
    }
    public ScoreBoard(){

    }
    public void addLine(String text){
        if (lines.size() <=15){
            lines.put(lines.size(),new ScoreBoardLine(text,0));
        }
    }
    public void update(Player player){
        removeScoreBoard(player);
        SetDisplayObjectivePacket displayObjectivePacket = new SetDisplayObjectivePacket();
        displayObjectivePacket.displaySlot = displaySlot;
        displayObjectivePacket.displayName = title;
        displayObjectivePacket.objectiveName = name;
        displayObjectivePacket.criteriaName = "dummy";
        displayObjectivePacket.sortOrder = SortOrder.ASCENDING;
        player.dataPacket(displayObjectivePacket);
        SetScorePacket scorePacket = new SetScorePacket();
        scorePacket.action = SetScorePacket.Action.SET;
        lines.forEach((slot,line)->{
            scorePacket.infos.add(new SetScorePacket.ScoreInfo(slot,getName(),slot,PlaceHolderAPI.translate(player,line.text())));
        });
        player.dataPacket(scorePacket);
    }
    public void removeScoreBoard(Player player){
        RemoveObjectivePacket objectivePacket = new RemoveObjectivePacket();
        objectivePacket.objectiveName = getName();
        player.dataPacket(objectivePacket);
    }
}
