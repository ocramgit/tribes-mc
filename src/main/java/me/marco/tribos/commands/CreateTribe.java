package me.marco.tribos.commands;

import me.marco.tribos.config.DataHandler;
import me.marco.tribos.domain.Tribe;
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

            if (dataHandler.getPlayerTribe(player.getDisplayName()) == null) {
                if (params.length == 2 && params[0].equalsIgnoreCase("criar")) {
                    String tribeName = params[1];
                    Tribe tribe = new Tribe(tribeName, player, List.of(player), 1500L, 0);
                    player.sendMessage("Criaste a tribo: " + tribeName);
                    dataHandler.setTribeCreation(player, tribe);
                } else {
                    player.sendMessage("Uso incorreto do comando. Use: /tribo criar <nome>");
                }
            } else {
                player.sendMessage("Tu já pertences a uma tribo.");
            }

                if(params.length > 1 && params[0].equals("ver")) {
                    Tribe tribe = dataHandler.loadTribe(params[1]);

                    player.sendMessage("Name: " + tribe.getName() + "\n" + "Money: " + tribe.getMoney());
                }

            return true;
        }
        return true;
    }
}
