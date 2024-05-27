package me.marco.tribos.config;

import me.marco.tribos.domain.Tribe;
import org.bukkit.configuration.ConfigurationSection;
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
        String name = tribe.getName();
        config.set("tribos." + name + ".triboNome", name);
        config.set("tribos." + name + ".owner", p.getDisplayName());
        config.set("tribos." + name + ".money", tribe.getMoney());
        config.set("tribos." + name + ".level", tribe.getLevel());
        config.set("tribos." + name + ".members", tribe.getMembers());
        saveConfig();
    }

    public String getTribeByPlayerName(String playerName) {
        ConfigurationSection tribesSection = config.getConfigurationSection("tribos");

        if (tribesSection == null) {
            return null;
        }

        for (String tribeKey : tribesSection.getKeys(false)) {
            ConfigurationSection tribeSection = tribesSection.getConfigurationSection(tribeKey);

            if (tribeSection != null) {
                String owner = tribeSection.getString("owner");

                if (playerName.equals(owner)) {
                    return tribeSection.getString("triboNome");
                }
            }
        }

        return null;
    }

    public boolean playerAlreadyHaveATribe(String playerName) {
        ConfigurationSection tribosSection = config.getConfigurationSection("tribos");
        if (tribosSection == null) {
            return false;
        }

        for (String tribeKey : tribosSection.getKeys(false)) {
            String owner = tribosSection.getString(tribeKey + ".owner");
            if (playerName.equals(owner)) {
                return true;
            }
        }
        return false;
    }



    public Tribe loadTribe(String tribeName) {
        ConfigurationSection tribeSection = config.getConfigurationSection("tribos." + tribeName);

        if (tribeSection == null) {
            return null;
        }

        String name = tribeSection.getString("triboNome");
        Long money = tribeSection.getLong("money");

        Tribe tribe = new Tribe();
        tribe.setName(name);
        tribe.setMoney(money);

        return tribe;
    }

    public void deleteTribe(Tribe tribe) {
        String tribeName = tribe.getName();
        if (config.contains("tribos." + tribeName)) {
            config.set("tribos." + tribeName, null);
            saveConfig();
            plugin.getLogger().info("Tribo '" + tribeName + "' foi deletada com sucesso.");
        }
    }
}
