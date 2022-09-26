package dev.lupluv.langmodule.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

public class StringGetter {

    private static StringGetter instance;

    public static StringGetter getInstance() {
        if(instance == null){
            instance = new StringGetter();
        }
        return instance;
    }

    public String getLanguageString(String pluginDir, String tag, String key){
        File file = getLanguageFile(tag, pluginDir);
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getString(key);
    }

    public List<String> getLanguageLines(String pluginDir, String tag, String key){
        File file = getLanguageFile(tag, pluginDir);
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.getStringList(key);
    }

    public File getLanguageFile(String tag, String pluginDir){
        return new File(pluginDir + "//Languages//" + tag + ".yml");
    }

}
