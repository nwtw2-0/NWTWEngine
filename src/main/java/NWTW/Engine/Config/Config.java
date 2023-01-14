package NWTW.Engine.Config;

import NWTW.Engine.CustomItem.ModItem;
import NWTW.Engine.Utils;
import cn.nukkit.item.Item;

import java.io.*;
import java.util.Base64;

public class Config extends cn.nukkit.utils.Config {
    public Config(String path, int type){
        super(path,type);
    }
    public void setObject(String key, Object obj){
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        String encode;
        try {
            objectOutputStream = new ObjectOutputStream(arrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            byte[] serializedObject = arrayOutputStream.toByteArray();
            encode = new String(Base64.getEncoder().encode(serializedObject));
            super.set(key,encode);
            objectOutputStream.close();
            arrayOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Object getObject(String key){
        Object result;
        byte[] serializedObject = Base64.getDecoder().decode(super.getString(key));
        ByteArrayInputStream in = new ByteArrayInputStream(serializedObject);
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(in);
            result = is.readObject();
            in.close();
            is.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public void setModItem(String key, ModItem  item){
        super.set(key,item.toString());
    }
    public ModItem getModItem(String key){
        String str =  super.getString(key);
        String[] arr = str.split(":");
        Item item = Item.get(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]) ,Integer.parseInt(arr[2]));
        item.setNamedTag(Item.parseCompoundTag(Utils.hexStringToBytes(arr[3])));
        return new ModItem(item,item.getNamedTag().getBoolean("custom"));
    }
}
