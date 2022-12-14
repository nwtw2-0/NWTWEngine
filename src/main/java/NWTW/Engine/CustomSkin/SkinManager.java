package NWTW.Engine.CustomSkin;

import NWTW.Engine.NWTWEngine;
import cn.nukkit.Server;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.network.protocol.PlayerSkinPacket;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.Utils;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkinManager {
    @Getter
    private final HashMap<String, Skin> skins;
    @Getter
    @Setter
    private Path path;

    public SkinManager(Path path) {
        this.skins = new HashMap<>();
        this.path = path;
        if(Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        loadSkin();
    }
    private void loadSkin() {
        File[] files = path.toFile().listFiles();
        if(files != null && files.length > 0){
            for(File file:files){
                String skinName = file.getName();
                if(new File(path.toFile().getPath()+"/"+skinName+"/skin.png").exists()){
                    Skin skin = new Skin();
                    BufferedImage skindata = null;
                    try {
                        skindata = ImageIO.read(new File(path.toFile().getPath()+"/"+skinName+"/skin.png"));
                    } catch (IOException var19) {
                        System.out.println("不存在此造型");
                    }

                    if (skindata != null) {
                        skin.setSkinData(skindata);
                        skin.setSkinId(skinName);
                    }
                    //如果是4D造型
                    File skinJsonFile = new File(path.toFile().getPath()+"/"+ skinName + "/skin.json");
                    if(skinJsonFile.exists()){
                        Map<String, Object> skinJson = (new Config(path.toFile().getPath()+"/"+skinName+"/skin.json", Config.JSON)).getAll();
                        String geometryName = null;

                        String formatVersion = (String) skinJson.getOrDefault("format_version", "1.10.0");
                        skin.setGeometryDataEngineVersion(formatVersion); //设置皮肤版本，主流格式有1.16.0,1.12.0(Blockbench新模型),1.10.0(Blockbench Legacy模型),1.8.0
                        switch (formatVersion){
                            case "1.16.0":
                            case "1.12.0":
                                geometryName = getGeometryName(skinJsonFile);
                                if(geometryName.equals("nullvalue")){
                                    NWTWEngine.getPlugin().getLogger().error("暂不支持" + skinName + "造型所用格式！請等待更新！");
                                }else{
                                    skin.generateSkinId(skinName);
                                    skin.setSkinResourcePatch("{\"geometry\":{\"default\":\"" + geometryName + "\"}}");
                                    skin.setGeometryName(geometryName);
                                    try {
                                        skin.setGeometryData(Utils.readFile(skinJsonFile));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    NWTWEngine.getPlugin().getLogger().info("造型 " + skinName + " 讀取中");
                                }
                                break;
                            default:
                                NWTWEngine.getPlugin().getLogger().warning("["+skinJsonFile.getName()+"] 的版本格式為："+formatVersion + "，正在嘗試讀取！");
                            case "1.10.0":
                            case "1.8.0":
                                for (Map.Entry<String, Object> entry : skinJson.entrySet()) {
                                    if (geometryName == null) {
                                        if (entry.getKey().startsWith("geometry")) {
                                            geometryName = entry.getKey();
                                        }
                                    }else {
                                        break;
                                    }
                                }
                                skin.generateSkinId(skinName);
                                skin.setSkinResourcePatch("{\"geometry\":{\"default\":\"" + geometryName + "\"}}");
                                skin.setGeometryName(geometryName);
                                try {
                                    skin.setGeometryData(Utils.readFile(skinJsonFile));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                        }
                    }
                    NWTWEngine.getPlugin().getLogger().info(skinName+"造型讀取完成");
                    skins.put(skinName,skin);
                }else{
                    NWTWEngine.getPlugin().getLogger().info("錯誤造型名稱格式 請將造型文件命名為 skin.png");
                }
            }
        }
    }

    public String getGeometryName(File file) {
        Config originGeometry = new Config(file, Config.JSON);
        if (!originGeometry.getString("format_version").equals("1.12.0") && !originGeometry.getString("format_version").equals("1.16.0")) {
            return "nullvalue";
        }
        //先读取minecraft:geometry下面的项目
        List<Map<String, Object>> geometryList = (List<Map<String, Object>>) originGeometry.get("minecraft:geometry");
        //不知道为何这里改成了数组，所以按照示例文件读取第一项
        Map<String, Object> geometryMain = geometryList.get(0);
        //获取description内的所有
        Map<String, Object> descriptions = (Map<String, Object>) geometryMain.get("description");
        return (String) descriptions.getOrDefault("identifier", "geometry.unknown"); //获取identifier
    }
    public void setSkin(EntityHuman human, String name){
        Skin skin = skins.get(name);
        Skin oldSkin = human.getSkin();
        human.setSkin(skin);
        PlayerSkinPacket packet = new PlayerSkinPacket();
        packet.skin = skin;
        packet.newSkinName = name;
        packet.oldSkinName = oldSkin.getSkinId();
        packet.uuid = human.getUniqueId();
        Server.broadcastPacket(Server.getInstance().getOnlinePlayers().values(), packet);
    }
}
