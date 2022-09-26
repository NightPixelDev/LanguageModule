package dev.lupluv.langmodule.events;

import dev.lupluv.langmodule.Lang;
import dev.lupluv.langmodule.api.StringGetter;
import dev.lupluv.langmodule.utils.FileManager;
import dev.lupluv.langmodule.utils.InventoryManager;
import dev.lupluv.langmodule.utils.LangConnector;
import dev.lupluv.langmodule.utils.Strings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        if(e.getCurrentItem() == null) return;
        if(e.getCurrentItem().getType() == Material.AIR) return;
        ItemStack item = e.getCurrentItem();
        Material mat = item.getType();
        String title = e.getView().getTitle();
        if(title.equalsIgnoreCase("§c§lSelect your Language")){
            e.setCancelled(true);
            if(mat == Material.PLAYER_HEAD){
                if(!item.getItemMeta().getDisplayName().equalsIgnoreCase("§c§lNo Languages found.")){
                    String tag = item.getLore().get(0).replace("§7", "");
                    if(!item.getItemMeta().getDisplayName().equalsIgnoreCase(FileManager.getInstance().getConfig()
                            .getString(tag + ".DisplayNameSelected"))){
                        LangConnector.setLang(p, tag);
                        p.sendMessage(Strings.getInstance().getMessage(p, "SelectedLanguage").replace("%language_tag%", tag)
                                .replace("%language_name%", Lang.getApi().getLanguageName(tag)));
                        p.openInventory(InventoryManager.getLanguageSelectorInventory(p));
                    }
                }
            }
        }
    }

}
