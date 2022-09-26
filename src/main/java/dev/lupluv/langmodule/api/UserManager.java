package dev.lupluv.langmodule.api;

import org.bukkit.entity.Player;

import java.util.UUID;

public class UserManager {

    /*
      Language Module API UserManager.java @ NightPixel
      Made by LUPLUV
     */

    /**
     * By using the UserManager#getLanguageUser and UserManager#getUser
     * Methods you can get a LanguageUser class to manage the settings
     * of any specific user
     */
    public LanguageUser getLanguageUser(String name){
        return new LanguageUser(name).loadByName();
    }

    public LanguageUser getLanguageUser(UUID uuid){
        return new LanguageUser(uuid).loadByUUID();
    }

    public LanguageUser getLanguageUser(Player player){
        return getLanguageUser(player.getUniqueId());
    }

    public LanguageUser getUser(String name){
        return getLanguageUser(name);
    }

    public LanguageUser getUser(UUID uuid){
        return getLanguageUser(uuid);
    }

    public LanguageUser getUser(Player player){
        return getLanguageUser(player);
    }

    /**
     * By using the UserManager#userExists Methods you can get a
     * boolean which contains if there is a user in the database
     * with that name or uuid
     */
    public boolean userExists(String name){
        return getUser(name).existsByName();
    }

    public boolean userExists(UUID uuid){
        return getUser(uuid).existsByUuid();
    }

}
