package me.marco.tribos;

import me.marco.tribos.commands.CreateTribe;
import me.marco.tribos.config.DataHandler;
import me.marco.tribos.events.ChatListenerEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Tribos extends JavaPlugin {

    private DataHandler dataHandler;

    @Override
    public void onEnable() {
        dataHandler = new DataHandler(this);
        dataHandler.createConfig();

        Objects.requireNonNull(getCommand("tribo")).setExecutor(new CreateTribe(dataHandler));
        getServer().getPluginManager().registerEvents(new ChatListenerEvent(dataHandler), this);
    }

    @Override
    public void onDisable() {
    }

    public DataHandler getDataHandler() {
        return dataHandler;
    }
}
