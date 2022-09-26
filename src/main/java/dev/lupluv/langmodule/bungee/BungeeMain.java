package dev.lupluv.langmodule.bungee;

import dev.lupluv.langmodule.Lang;
import dev.lupluv.langmodule.bungee.utils.FileManager;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public class BungeeMain extends Plugin {

    @Override
    public void onEnable() {
        try {
            FileManager.loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Lang.initializeBungee();
    }
}
