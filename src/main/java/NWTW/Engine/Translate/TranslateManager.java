package NWTW.Engine.Translate;

import NWTW.Engine.NWTWEngine;
import NWTW.Engine.Translate.provider.GoogleTranslateProvider;
import NWTW.Engine.Translate.provider.TranslateProvider;
import cn.nukkit.scheduler.AsyncTask;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class TranslateManager {
    private TranslateProvider provider;
    public TranslateManager(){
        provider = new GoogleTranslateProvider();
    }
    public String translate(@NotNull String text) {
        return this.translate("auto", "zh", text);
    }

    public void translate(@NotNull String text, @NotNull Consumer<String> consumer) {
        NWTWEngine.getPlugin().getServer().getScheduler().scheduleAsyncTask(NWTWEngine.getPlugin(), new AsyncTask() {
            @Override
            public void onRun() {
                consumer.accept(translate(text));
            }
        });
    }

    public String translate(@NotNull String sourceLanguage, @NotNull String targetLanguage, @NotNull String text) {
        return this.provider.translate(sourceLanguage, targetLanguage, text);
    }

    public void translate(@NotNull String sourceLanguage, @NotNull String targetLanguage, @NotNull String text, @NotNull Consumer<String> consumer) {
        NWTWEngine.getPlugin().getServer().getScheduler().scheduleAsyncTask(NWTWEngine.getPlugin(), new AsyncTask() {
            @Override
            public void onRun() {
                consumer.accept(translate(sourceLanguage, targetLanguage, text));
            }
        });
    }
}
