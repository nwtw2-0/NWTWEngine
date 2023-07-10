package NWTW.Engine.Holograms;

import NWTW.Engine.NWTWEngineAPI;
import cn.nukkit.entity.Entity;
import cn.nukkit.level.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class HologramEntity extends Entity {
    @Getter
    private boolean save;
    @Getter
    private ArrayList<String> lines;
    @Getter
    private int period;
    @Getter
    @Setter
    private Location location;
    @Getter
    private String identifierName;
    public HologramEntity(String identifier,Location location, boolean save, int period, List<String> strings) {
        super(location.getLevel().getChunk(location.getFloorX() >> 4,location.getChunkZ() >>4), Entity.getDefaultNBT(location));
        lines = (ArrayList<String>) strings;
        this.period = period;
        this.save = save;
        this.location = location;
        this.identifierName = identifier;
        this.teleport(location);
        this.setNameTagVisible(true);
        this.setNameTagAlwaysVisible(true);
        StringBuilder name = new StringBuilder();
        for (String str: lines) {
            name.append(str).append("\n");
        }
        this.setNameTag(name.toString());
    }

    @Override
    public int getNetworkId() {
        return 64;
    }

    @Override
    public boolean entityBaseTick(int tickDiff) {
        if (this.ticksLived % period != 0) return super.entityBaseTick(tickDiff);
        StringBuilder name = new StringBuilder();
        for (String str: lines) {
            name.append(str).append("\n");
        }
        this.setNameTag(name.toString());
        return super.entityBaseTick(tickDiff);
    }
    public HologramEntity addLine(String line){
        lines.add(line);
        return this;
    }
    public void spawn(){
        this.spawnToAll();
        NWTWEngineAPI.getHologramManager().getEntities().add(this);
    }
}
