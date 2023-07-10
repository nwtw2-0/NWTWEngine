package NWTW.Engine.Utils;

import NWTW.Engine.NWTWEngine;
import cn.nukkit.Server;
import cn.nukkit.level.Location;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
        return new Location(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]),Double.parseDouble(arr[2]),Double.parseDouble(arr[3]),Double.parseDouble(arr[4]),Double.parseDouble(arr[5]), Server.getInstance().getLevelByName(arr[6]));
    }
    public static boolean DownloadFromURL(String urlString,String path){
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 建立輸出流
            OutputStream outputStream = new FileOutputStream(path);

            // 下載文件
            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // 下載成功，關閉流
            outputStream.close();
            inputStream.close();
            return true;
        } catch (IOException e) {
            // 處理 IO 錯誤
            e.printStackTrace();
            return false;
        }
    }
}
