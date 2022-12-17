package NWTW.Engine.Discord;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBuilder {
    @Getter
    private JDA jda;
    //todo 此類別我還在想要如何進行規劃 之後再填坑
    public DiscordBuilder(String token){
        jda = JDABuilder.createDefault("token").build();
    }
}
