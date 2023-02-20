package NWTW.Engine.Utils;

public enum TimeFormat {
    //https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html格式來源
    hh_mm_ss("HH:mm:ss"),
    yyyy_mm_dd_hh_mm_ss("yyyy-MM-dd HH:mm:ss"),
    yyyy_mm_dd("yyyy-MM-dd");

    private final String format;
    TimeFormat(String format){
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
