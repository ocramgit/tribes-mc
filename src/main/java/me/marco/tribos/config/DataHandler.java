package me.marco.tribos.config;

import me.marco.tribos.domain.Tribe;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class DataHandler {
    private final JavaPlugin plugin;
    private File file;
    private FileConfiguration config;

    public DataHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void reloadConfig() {
        if (file == null) {
            file = new File(plugin.getDataFolder(), "tribos.yml");
        }
        if (!file.exists()) {
            createConfig();
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        reloadConfig();
        return config;
    }

    public void createConfig() {
        file = new File(plugin.getDataFolder(), "tribos.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs(); // Garantir que os diretórios pai existam
            plugin.saveResource("tribos.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file); // Carregar a configuração
    }

    public void saveConfig() {
        if (config == null || file == null) {
            return;
        }
        try {
            config.save(file);
        } catch (IOException ex) {
            plugin.getLogger().severe("Não foi possível salvar o arquivo de configuração 'tribos.yml'!");
        }
    }

    public void setTribeCreation(Player p, Tribe tribe) {
        config.set("tribos." + ".triboNome", tribe.getName());
        config.set("tribos." + ".owner", p.getDisplayName());
        config.set("tribos." + ".money", tribe.getMoney());
        config.set("tribos." + ".level", tribe.getLevel());
        config.set("tribos." + ".members", tribe.getMembers());
        saveConfig();
    }

    public String getPlayerTribe(String playerName) {
        return config.getString("tribos." + playerName);
    }

    public String getPlayerRole(String playerName) {
        return config.getString("tribos." + playerName + ".role");
    }
}
