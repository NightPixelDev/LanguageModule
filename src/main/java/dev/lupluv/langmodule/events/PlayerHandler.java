package dev.lupluv.langmodule.events;

import dev.lupluv.langmodule.utils.FileManager;
import dev.lupluv.langmodule.utils.LangConnector;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerHandler implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        if(!LangConnector.isUserExists(p.getUniqueId())){
            LangConnector.createUser(p, FileManager.getInstance().getConfig().getString("DefaultLanguage"));
        }else {
            LangConnector.updateName(p);
        }

    }

}
