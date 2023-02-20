package NWTW.Engine.Utils;

import cn.nukkit.Server;
import cn.nukkit.level.Location;

public class Utils {
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    public static String Location2String(Location location){
        return location.x +
                "," +
                location.y +
                "," +
                location.z +
                "," +
                location.yaw +
                "," +
                location.pitch +
                "," +
                location.headYaw +
                "," +
                location.getLevelName();
    }
    public static Location String2Location(String string){
        String[] arr = string.split(",");
        double x = Double.parseDouble(arr[0]);
        double y = Double.parseDouble(arr[1]);
        double z = Double.parseDouble(arr[2]);
        double yaw = Double.parseDouble(arr[3]);
        double pitch = Double.parseDouble(arr[4]);
        double headyaw = Double.parseDouble(arr[5]);
        String level = arr[6];
        return new Location(x,y,z,yaw,pitch,headyaw, Server.getInstance().getLevelByName(level));
    }
}
