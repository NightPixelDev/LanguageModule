package dev.lupluv.langmodule.api;

import dev.lupluv.langmodule.utils.FileManager;
import dev.lupluv.langmodule.utils.LangConnector;

public class API extends LangConnector {

    /*
      Language Module API @ NightPixel
      Made by LUPLUV
     */

    /**
     * UserManager attribute
     */
    private final UserManager userManager;

    /**
     * Constructor initialises all attributes
     */
    public API() {
        userManager = new UserManager();
    }

    /**
     * By using API#getUserManager you can get a
     * class to manage all your users Languages
     */
    public UserManager getUserManager(){
        return userManager;
    }

    /**
     * Returns the language name based on the language tag
     */
    public String getLanguageName(String tag){
        return FileManager.getInstance().getConfig().getString(tag + ".Name");
    }

}
