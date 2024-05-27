package me.marco.tribos.events;

import me.marco.tribos.config.DataHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListenerEvent implements Listener {
    private final DataHandler dataHandler;

    public ChatListenerEvent(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String tribe = dataHandler.getTribeByPlayerName(player.getDisplayName());

        if (tribe != null) {
            String format = ChatColor.LIGHT_PURPLE +  "[" + tribe + "] " + ChatColor.WHITE + player.getDisplayName() + ": " + ChatColor.GRAY + event.getMessage();
            event.setFormat(format);
        } else {
            String format = ChatColor.GRAY +  "[" + "Membro" + "] " + ChatColor.WHITE + player.getDisplayName() + ": " + ChatColor.GRAY + event.getMessage();
            event.setFormat(format);
        }
    }
}
