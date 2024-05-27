package me.marco.tribos.commands;

import me.marco.tribos.config.DataHandler;
import me.marco.tribos.domain.Tribe;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateTribe implements CommandExecutor {
    private final DataHandler dataHandler;

    public CreateTribe(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] params) {
        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

                if (params.length == 2 && params[0].equalsIgnoreCase("criar")) {

                    if (dataHandler.playerAlreadyHaveATribe(player.getDisplayName())) {
                        player.sendMessage("Tu já pertences a uma tribo.");
                        return false;
                    }

                    String tribeName = params[1];
                    Tribe tribe = new Tribe(tribeName, player, List.of(player), 1500L, 0);
                    player.sendMessage("Criaste a tribo: " + tribeName);
                    dataHandler.setTribeCreation(player, tribe);
                }

                if(params.length > 1 && params[0].equals("ver")) {
                    Tribe tribe = dataHandler.loadTribe(params[1]);

                    if (tribe == null) {
                        player.sendMessage("A tribo " + params[1] + " não existe.");
                        return false;
                    }

                    player.sendMessage("Name: " + ChatColor.GRAY + tribe.getName() + "\n" + ChatColor.WHITE + "Money: " + ChatColor.GRAY + tribe.getMoney());
                }

            if(params.length >= 1 && params[0].equals("eliminar")) {
                    String tribeName = dataHandler.getTribeByPlayerName(player.getDisplayName());
                    Tribe tribe = dataHandler.loadTribe(tribeName);

                    dataHandler.deleteTribe(tribe);
                    player.sendMessage("Tribo eliminada.");
            }

            return true;
        }
        return true;
    }
}
