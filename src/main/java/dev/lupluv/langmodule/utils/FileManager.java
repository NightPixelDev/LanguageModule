package dev.lupluv.langmodule.utils;

import dev.lupluv.langmodule.api.UserManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.DEFAULT_BUFFER_SIZE;

public class FileManager {

    private static FileManager instance;

    public static FileManager getInstance() {
        if(instance == null){
            instance = new FileManager();
        }
        return instance;
    }

    File configFile;
    File mysqlFile;
    File defaultMessagesFile;

    public void loadFiles() throws IOException {
        File folder = new File("plugins//LanguageAPI");
        File langFolder = new File("plugins//LanguageAPI//Languages");
        configFile = new File("plugins//LanguageAPI//config.yml");
        defaultMessagesFile = new File("plugins//LanguageAPI//Languages//en_US.yml");
        mysqlFile = new File("plugins//LanguageAPI//mysql.yml");
        if(!folder.exists()) folder.mkdir();
        if(!langFolder.exists()) langFolder.mkdir();
        if(!configFile.exists()){
            configFile.createNewFile();
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
            cfg.set("Version", 1.0);
            cfg.set("DefaultLanguage", "en_US");
            cfg.set("en_US.Name", "English");
            cfg.set("en_US.Tag", "en_US");
            cfg.set("en_US.File", "en_US.yml");
            cfg.set("en_US.DisplayName", "&4&lEnglish US");
            cfg.set("en_US.DisplayNameSelected", "&4&lEnglish US &a(selected)");
            cfg.set("en_US.SkullValue", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY" +
                    "3JhZnQubmV0L3RleHR1cmUvNWU3ODk5YjQ4MDY4NTg2OTdlMjgzZjA4NGQ5MTczZmU0ODc4ODY0NTM3NzQ2MjZiMjRiZDhjZmVjYzc3YjNmIn19fQ==");
            cfg.save(configFile);
        }
        if(!defaultMessagesFile.exists()){
            defaultMessagesFile.createNewFile();
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(defaultMessagesFile);
            cfg.set("Prefix", "&6Language &8| &7");
            cfg.set("NoPerms", "%prefix% &cYou aren't permitted to do that");
            cfg.set("SelectedLanguage", "%prefix% &7You have selected the Language %language_name% (%language_tag%)");
            cfg.save(defaultMessagesFile);
        }
        if(!mysqlFile.exists()){
            mysqlFile.createNewFile();
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(mysqlFile);
            cfg.set("Host", "localhost");
            cfg.set("Port", "3306");
            cfg.set("Database", "database");
            cfg.set("Username", "username");
            cfg.set("Password", "password");
            cfg.save(mysqlFile);
        }
    }

    public File getConfigFile() {
        return configFile;
    }

    public File getMysqlFile() {
        return mysqlFile;
    }

    public FileConfiguration getConfig(){
        return YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getMysql(){
        return YamlConfiguration.loadConfiguration(mysqlFile);
    }

    public void copyResourceFile(String src, String dest) throws IOException {
        InputStream resourceURL = this.getClass().getResourceAsStream(src);

        File file2 = new File(dest);

        copyInputStreamToFile(resourceURL, file2);

    }

    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }
}
