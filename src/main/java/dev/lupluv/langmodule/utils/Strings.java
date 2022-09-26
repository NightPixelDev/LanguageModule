package dev.lupluv.langmodule.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Strings {

    private static Strings instance;

    public static Strings getInstance() {
        if(instance == null){
            instance = new Strings();
        }
        return instance;
    }

    public String mysqlPrefix = "[LanguageModule - MySQL] ";

    public String getPrefix(Player p){
        if(LangConnector.isUserExists(p.getUniqueId())){
            if(LangConnector.getLangTag(p) != null){
                String tag = LangConnector.getLangTag(p);
                File file = new File("plugins//LanguageAPI//Languages//" + tag + ".yml");
                if(file.exists()) {
                    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    return cfg.getString("Prefix").replace("&", "§");
                }else{
                    File file2 = new File("plugins//LanguageAPI//Languages//" + FileManager.getInstance().getConfig().getString("DefaultLanguage") + ".yml");
                    if (file2.exists()) {
                        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file2);
                        return cfg.getString("Prefix").replace("&", "§");
                    } else {
                        return "§cNo Language File found, please contact an Admin to fix this issue.";
                    }
                }
            }
        }else {
            File file = new File("plugins//LanguageAPI//Languages//" + FileManager.getInstance().getConfig().getString("DefaultLanguage") + ".yml");
            if (file.exists()) {
                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                return cfg.getString("Prefix").replace("&", "§");
            } else {
                return "§cNo Language File found, please contact an Admin to fix this issue.";
            }
        }
        return "§cAn error occured while asking for the System Prefix";
    }

    public String getMessage(Player p, String key){
        if(LangConnector.isUserExists(p.getUniqueId())){
            if(LangConnector.getLangTag(p) != null){
                String tag = LangConnector.getLangTag(p);
                File file = new File("plugins//LanguageAPI//Languages//" + tag + ".yml");
                if(file.exists()) {
                    FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                    return cfg.getString(key).replace("&", "§").replace("%prefix%", getPrefix(p));
                }else{
                    File file2 = new File("plugins//LanguageAPI//Languages//" + FileManager.getInstance().getConfig().getString("DefaultLanguage") + ".yml");
                    if(file2.exists()) {
                        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file2);
                        return cfg.getString(key).replace("&", "§").replace("%prefix%", getPrefix(p));
                    }else{
                        return "§cNo Language File found, please contact an Admin to fix this issue.";
                    }
                }
            }
        }else {
            File file = new File("plugins//LanguageAPI//Languages//" + FileManager.getInstance().getConfig().getString("DefaultLanguage") + ".yml");
            if (file.exists()) {
                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                return cfg.getString(key).replace("&", "§").replace("%prefix%", getPrefix(p));
            } else {
                return "§cNo Language File found, please contact an Admin to fix this issue.";
            }
        }
        return "§cAn error occured while asking for the System Message";
    }

}
