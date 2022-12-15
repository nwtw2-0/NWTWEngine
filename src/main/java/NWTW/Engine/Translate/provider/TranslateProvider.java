package NWTW.Engine.Translate.provider;

import org.jetbrains.annotations.NotNull;

/**
 * @author LT_Name
 */
public interface TranslateProvider {

    String getProviderName();

    default boolean isSupportAuto() {
        return true;
    }

    String translate(@NotNull String text);

    String translate(@NotNull String sourceLanguage, @NotNull String targetLanguage, @NotNull String text);

}
