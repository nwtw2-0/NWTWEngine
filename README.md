# NWTWEngine
NWTW伺服器依賴包引擎
此插件的主要作用為做為所有子插件的依賴需要導入外部jar的套件都經由此插件進行處理減少記憶體資源浪費
目前已使用的外部函數 MYSql MonogoDB SQLite GeoIP


如何獲取玩家所在的國家及縣市
'''
var ip = player.getIP();
GeoIP.getContry(ip); //返回玩家所在的國家
GeoIP.getCity(ip)；// 返回玩家所在的縣市
'''