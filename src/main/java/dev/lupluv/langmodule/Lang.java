package dev.lupluv.langmodule;

import dev.lupluv.langmodule.api.API;
import dev.lupluv.langmodule.commands.LanguageCmd;
import dev.lupluv.langmodule.events.ClickEvent;
import dev.lupluv.langmodule.events.PlayerHandler;
import dev.lupluv.langmodule.mysql.MySQL;
import dev.lupluv.langmodule.utils.FileManager;
import dev.lupluv.langmodule.utils.Strings;
import net.md_5.bungee.config.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Lang extends JavaPlugin {

    private static Lang plugin;
    public static MySQL mySQL;
    public static API api;

    public static void initializeBungee(){
        plugin = new Lang();
        reloadMysqlBungee();
        api = new API();
    }

    @Override
    public void onEnable() {

        plugin = this;
        api = new API();

        try {
            FileManager.getInstance().loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        reloadMysql();

        getCommand("language").setExecutor(new LanguageCmd());
        getCommand("sprache").setExecutor(new LanguageCmd());
        getCommand("lang").setExecutor(new LanguageCmd());

        getServer().getPluginManager().registerEvents(new PlayerHandler(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);

    }

    @Override
    public void onDisable() {

    }

    public static Lang getPlugin() {
        return plugin;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }

    public static API getApi() {
        return api;
    }

    public static void reloadMysql(){
        if(FileManager.getInstance().getMysqlFile().exists()) {
            FileConfiguration cfg = FileManager.getInstance().getMysql();
            if (mySQL != null) {
                mySQL = null;
            }
            mySQL = new MySQL(cfg.getString("Host"), cfg.getString("Port"), cfg.getString("Database"), cfg.getString("Username"), cfg.getString("Password"));
            mySQL.connect();
            if(mySQL.isConnected()){
                mySQL.update("CREATE TABLE IF NOT EXISTS lang_connector (UUID VARCHAR(100),NAME VARCHAR(100),LANG VARCHAR(100));");
            }
        }else{
            System.out.println(Strings.getInstance().mysqlPrefix + " MySQL File couldn't be found.");
        }
    }

    public static void reloadMysqlBungee(){
        if(dev.lupluv.langmodule.bungee.utils.FileManager.getConfigFile().exists()) {
            Configuration cfg = dev.lupluv.langmodule.bungee.utils.FileManager.getMysqlConfig();
            if (mySQL != null) {
                mySQL = null;
            }
            mySQL = new MySQL(cfg.getString("Mysql.Host"), cfg.getString("Mysql.Port"), cfg.getString("Mysql.Database"), cfg.getString("Mysql.Username"), cfg.getString("Mysql.Password"));
            mySQL.connect();
            if(mySQL.isConnected()){
                mySQL.update("CREATE TABLE IF NOT EXISTS lang_connector (UUID VARCHAR(100),NAME VARCHAR(100),LANG VARCHAR(100));");
            }
        }else{
            System.out.println(Strings.getInstance().mysqlPrefix + " MySQL File couldn't be found.");
        }
    }

}
