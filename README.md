# NWTWEngine

NWTW伺服器依賴包引擎
此插件的主要作用為做為所有子插件的依賴需要導入外部jar的套件都經由此插件進行處理減少記憶體資源浪費
目前已使用的外部函數 MYSql MonogoDB SQLite GeoIP

---

如何獲取玩家所在的國家及縣市

```java
var ip = player.getAddress();
NWTWEngine.getPlugun().getIpManager().getContry(ip); //返回玩家所在的國家
NWTWEngine.getPlugun().getIpManager().getCity(ip)；// 返回玩家所在的縣市
```

---

如何創建一分積分版

```java
var scoreboard = NWTWEngine.getPlugin().ScoreboardManager().createScoreboard();
scoreboard.setTitle("title");
scoreboard.setName("name");
scoreboard.setDisplaySlot(DisplaySlot.SIDEBAR);
NWTWEngine.getPlugin().ScoreboardManager().registerScoreboard(this,scoreboard);
NWTWEngine.getPlugin().ScoreboardManager().setPlayerSelectScoreboard(player,scoreboard);
```
