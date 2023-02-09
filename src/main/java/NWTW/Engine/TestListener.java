package NWTW.Engine;

import NWTW.Engine.PlaceHolder.PlaceHolderAPI;
import NWTW.Engine.ScoreBoard.ScoreBoard;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.utils.BossBarColor;
import cn.nukkit.utils.TextFormat;


public class TestListener implements Listener {
    @EventHandler
    public void join(PlayerLocallyInitializedEvent event) {
        ScoreBoard board = NWTWEngineAPI.getScoreboardManager().createScoreboard("我是標題","test",DisplaySlot.SIDEBAR);
        board.addLine(PlaceHolderAPI.translate(event.getPlayer(),"我的名稱為%player_name%"));
        board.addLine(TextFormat.RED+"這是第二行");
        NWTWEngineAPI.getScoreboardManager().registerScoreboard(NWTWEngine.getPlugin(),board);
        NWTWEngineAPI.getScoreboardManager().setPlayerSelectScoreboard(event.getPlayer(),board);
    }
}
