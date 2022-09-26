package dev.lupluv.langmodule.bungee.utils;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public static void loadFiles() throws IOException {
        File folder = new File("plugins//LanguageModule");
        File configFile = new File("plugins//LanguageModule//config.yml");
        if(!folder.exists()) folder.mkdir();
        if(!configFile.exists()){
            configFile.createNewFile();
            Configuration cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(configFile);
            cfg.set("DefaultLanguage", "en_US");
            cfg.set("Mysql.Host", "localhost");
            cfg.set("Mysql.Port", "3306");
            cfg.set("Mysql.Database", "database");
            cfg.set("Mysql.Username", "username");
            cfg.set("Mysql.Password", "password");
            YamlConfiguration.getProvider(YamlConfiguration.class).save(cfg, configFile);
        }
    }

    public static File getConfigFile(){
        return new File("plugins//LanguageModule//config.yml");
    }

    public static Configuration getMysqlConfig(){
        try {
            return YamlConfiguration.getProvider(YamlConfiguration.class).load(new File("plugins//LanguageModule//config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
