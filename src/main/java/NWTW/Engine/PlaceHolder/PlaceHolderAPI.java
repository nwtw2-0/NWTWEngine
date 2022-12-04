package NWTW.Engine.PlaceHolder;

import cn.nukkit.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceHolderAPI {
    public static ArrayList<IPlaceHolder> place = new ArrayList<>();
    public static void registerPlaceHolder(IPlaceHolder placeHolder){
        place.add(placeHolder);
    }
    public static void removePlaceHolder(IPlaceHolder placeHolder){
        place.remove(placeHolder);
    }

    public static String translate(Player player,String org){
        for(IPlaceHolder placeHolder : place){
            Pattern pattern = Pattern.compile("%"+placeHolder.getIdentifier()+"_.+%");
            Matcher matcher = pattern.matcher(org);
            while (matcher.find()){
                String group = matcher.group();
                String fix = group.substring(placeHolder.getIdentifier().length()+2,group.length()-1);
                org = org.replace(group,placeHolder.onPlaceholderRequest(player,fix));
                matcher = pattern.matcher(org);
            }
        }
        return org;
    }
    public void registerDefaultPlaceHolder(){
        registerPlaceHolder(new NukkitHolder());
    }
}
