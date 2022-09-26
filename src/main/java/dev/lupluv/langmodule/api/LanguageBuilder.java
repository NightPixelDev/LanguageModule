package dev.lupluv.langmodule.api;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.io.IOUtils.DEFAULT_BUFFER_SIZE;

public class LanguageBuilder {

    public static LanguageBuilder create(String defaultLanguage, String pluginDir){
        if(!new File(pluginDir + "//Languages").exists()){
            new File(pluginDir + "//Languages").mkdir();
        }
        return new LanguageBuilder(defaultLanguage, new HashMap<>(), new HashMap<>());
    }

    String defaultLanguage;
    Map<String, String> translations;
    Map<String, String> manualTranslations;

    public LanguageBuilder(String defaultLanguage, Map<String, String> translations, Map<String, String> manualTranslations) {
        this.defaultLanguage = defaultLanguage;
        this.translations = translations;
        this.manualTranslations = manualTranslations;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public void addTranslation(String pluginDir, String tag, String resourceFile){
        translations.put(pluginDir + "//Languages//" + tag + ".yml", resourceFile);
    }

    public void copyManualTranslation(String pluginDir, String tag, String source){
        translations.put(pluginDir + "//Languages//" + tag + ".yml", source);
    }

    public void build(){
        translations.keySet().forEach(each ->{
            String source = translations.get(each);
            if(!new File(each).exists()) {
                try {
                    copyResourceFile(source, each);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        manualTranslations.keySet().forEach(each ->{
            String source = translations.get(each);
            if(!new File(each).exists()) {
                try {
                    FileUtils.copyFile(new File(source), new File(each));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
