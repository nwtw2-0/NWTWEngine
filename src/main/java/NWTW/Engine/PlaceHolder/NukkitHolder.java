package NWTW.Engine.PlaceHolder;

import NWTW.Engine.NWTWEngine;
import cn.nukkit.Nukkit;
import cn.nukkit.Player;
import cn.nukkit.Server;

public class NukkitHolder implements IPlaceHolder{
    @Override
    public String getIdentifier() {
        return "server";
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        switch (identifier){
            case "name"-> {
                return Nukkit.VERSION;
            }
            case "online"->{
                return String.valueOf(Server.getInstance().getOnlinePlayers().size());
            }
            case "version"->{
                return Nukkit.MINECRAFT_VERSION;
            }
            case "ram_total"->{
                return String.valueOf(Runtime.getRuntime().totalMemory());
            }
            case "ram_free"->{
                return String.valueOf(Runtime.getRuntime().freeMemory());
            }
            case "ram_max"->{
                return String.valueOf(Runtime.getRuntime().maxMemory());
            }
            case "tps"->{
                return String.valueOf(Server.getInstance().getTicksPerSecondAverage());
            }
        }
        if(identifier.contains("time_")){
            return NWTWEngine.getStringDate(identifier.split("_")[1]);
        }
        return null;
    }
}
