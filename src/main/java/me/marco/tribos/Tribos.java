package me.marco.tribos;

import me.marco.tribos.commands.CreateTribe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Tribos extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("tribe")).setExecutor(new CreateTribe());

    }

    @Override
    public void onDisable() {
    }
}
