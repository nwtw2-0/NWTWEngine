package NWTW.Engine;

import NWTW.Engine.GeoIP.GeoIP;
import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import NWTW.Engine.ScoreBoard.ScoreBoard;
import NWTW.Engine.ScoreBoard.ScoreBoardAPI;
import NWTW.Engine.ScoreBoard.ScoreBoardListener;
import NWTW.Engine.ScoreBoard.ScoreBoardTestListener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scoreboard.data.DisplaySlot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NWTWEngine extends PluginBase {
    private static NWTWEngine plugin;
    public ScoreBoard scoreBoard;

    @Override
    public void onLoad() {
        plugin = this;
        super.onLoad();
    }

    @Override
    public void onEnable() {
        PlaceHolderAPI placeHolderAPI = new PlaceHolderAPI();
        placeHolderAPI.registerDefaultPlaceHolder();
        new GeoIP(getDataFolder().toString());
        getServer().getScheduler().scheduleRepeatingTask(ScoreBoard.task,20);
        getServer().getPluginManager().registerEvents(new ScoreBoardListener(),this);
        getServer().getPluginManager().registerEvents(new ScoreBoardTestListener(),this);
        getLogger().info(getName()+"已經開啟");
        scoreBoard = new ScoreBoard("test","Hello World", DisplaySlot.SIDEBAR);
        scoreBoard.addLine("tps:%server_tps%");
        scoreBoard.addLine("time:%server_time_yyyy-MM-dd HH:mm:ss%");
        scoreBoard.addLine("mc version:%server_version%");
        scoreBoard.addLine("core:%server_name%");
        scoreBoard.addLine("你的名稱: %player_name%");
        scoreBoard.addLine("所在位置: %player_world% x:%player_x% y:%player_y% z:%player_z%");
        scoreBoard.addLine("你現在的血量: %player_max_health%/%player_health%");
        ScoreBoardAPI.registerScoreBoard(this,scoreBoard);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTask(ScoreBoard.task.getTaskId());
        getLogger().info(getName()+"已經關閉");
        super.onDisable();
    }

    public static NWTWEngine getPlugin() {
        return plugin;
    }
    public static String getStringDate(String str) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(str);
        return formatter.format(currentTime);
    }

}
