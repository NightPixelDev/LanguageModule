package dev.lupluv.langmodule.api;

import dev.lupluv.langmodule.utils.LangConnector;

import java.util.UUID;

public class LanguageUser {

    /*
      Language Module API LanguageUser.java @ NightPixel
      Made by LUPLUV
     */

    /**
     * These attributes contain all data that will be
     * collected from every user, and they are saved
     * in the database
     */
    UUID uuid;
    String name;
    String language;

    /**
     * Constructors to create a LanguageUser or
     * to search for an existing one
     */
    public LanguageUser(UUID uuid) {
        this.uuid = uuid;
    }

    public LanguageUser(String name) {
        this.name = name;
    }

    public LanguageUser(UUID uuid, String name, String language) {
        this.uuid = uuid;
        this.name = name;
        this.language = language;
    }

    /**
     * Setters and Getters to modify the LanguageUsers data
     *
     * Important Note: getLanguage() gives you the specified language tag, not the name
     * To get the language name you have to use the Method Lang.getAPI().getLanguageName(tag);
     */
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Custom Methods to create, load, save and delete a LanguageUser
     * create() creates a new database entry with the data collected in the attributes
     * loadByUuid() loads the data from the database entry, identified by the given uuid into the attributes
     * loadByName() loads the data from the database entry, identified by the given name into the attributes
     * existsByUuid() returns a boolean that contains if the user has an entry, identified by the given uuid in the database
     * existsByName() returns a boolean that contains if the user has an entry, identified by the given name in the database
     * save() saves the data to the database entry, identified by the given uuid
     * delete() deletes the database entry, identified by the given uuid
     */
    public void create(){
        LangConnector.createUser(uuid, name, language);
    }

    public LanguageUser loadByUUID(){
        name = LangConnector.getNameByUuid(uuid);
        language = LangConnector.getLangTag(uuid);
        return this;
    }

    public LanguageUser loadByName(){
        uuid = LangConnector.getUuidByName(name);
        language = LangConnector.getLangTag(uuid);
        return this;
    }

    public boolean existsByUuid(){
        return LangConnector.isUserExists(uuid);
    }

    public boolean existsByName(){
        return LangConnector.isUserExists(name);
    }

    public LanguageUser save(){
        LangConnector.updateName(uuid, name);
        LangConnector.setLang(uuid, language);
        return this;
    }

    public void delete(){
        LangConnector.deleteUser(uuid);
    }

}
