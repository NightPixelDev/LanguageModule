package dev.lupluv.langmodule.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    public static Inventory getLanguageSelectorInventory(Player p){
        Inventory inv = Bukkit.createInventory(null, 9*5, "§c§lSelect your Language");

        FileConfiguration cfg = FileManager.getInstance().getConfig();

        int i = 0;
        for(String key : cfg.getKeys(true)){
            if(key.endsWith("Tag")){
                i++;
                String tag = cfg.getString(key);
                if(LangConnector.getLangTag(p).equalsIgnoreCase(cfg.getString(key))){
                    ItemStack is = Util.createCustomSkull(cfg.getString(tag + ".SkullValue")
                            , cfg.getString(tag + ".DisplayNameSelected").replace("&", "§"), Lore.create("§7" + tag.replace("&", "§")));
                    inv.addItem(is);
                }else{
                    ItemStack is = Util.createCustomSkull(cfg.getString(tag + ".SkullValue")
                            , cfg.getString(tag + ".DisplayName").replace("&", "§"), Lore.create("§7" + tag.replace("&", "§")));
                    inv.addItem(is);
                }
            }
        }

        if(i == 0){
            ItemStack is = Util.createCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cm" +
                    "UvNmJkMmY5MzQ3NmFiNjlmYWY1YTUxOWViNTgzMmRiODQxYzg1MjY2ZTAwMWRlNWIyNmU0MjdmNDFkOThlNWM3ZSJ9fX0="
                    , "§c§lNo Languages found.", Lore.create("Please contact an Admin for more Info."));
        }

        return inv;

    }

}
