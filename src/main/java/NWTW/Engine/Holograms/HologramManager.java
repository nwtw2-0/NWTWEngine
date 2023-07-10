package NWTW.Engine.Holograms;

import NWTW.Engine.Config.Config;
import NWTW.Engine.Utils.Utils;
import cn.nukkit.level.Location;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HologramManager {
    @Getter
    private final ArrayList<HologramEntity> entities;
    private final Config config;
    public HologramManager(Config config) {
        this.entities = new ArrayList<>();
        this.config = config;
        loadHolograms();
    }
    public void loadHolograms(){
        for (String keys:config.getKeys(false)) {
            HologramEntity entity = new HologramEntity(keys,config.getLocation(keys+".Location"),true,config.getInt(keys+".Period"),config.getStringList(keys+".Content"));
            entity.spawnToAll();
            entities.add(entity);
            config.remove(keys);
        }
        config.save();
    }
    public void saveHolograms(){
        for (HologramEntity entity : entities){
            if (!entity.isSave()) continue;
            Map<String,Object> map = new HashMap<>();
            map.put("Location", Utils.Location2String(entity.getLocation()));
            map.put("Content",entity.getLines());
            map.put("Period",entity.getPeriod());
            config.set(entity.getIdentifierName(),map);
            entity.close();
        }
        config.save();
    }
    public HologramEntity createHologram(String identifier, Location location, int period, boolean save){
        return new HologramEntity(identifier,location,save,period,new ArrayList<>());
    }
}
