package me.marco.tribos.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CreateTribe implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] params) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can perform this command!");
            return true;
        }
        Player player = (Player) commandSender;

        if(params.length == 2 && params[0].equalsIgnoreCase("create")) {
            if (Objects.equals(params[1], null)) {
                player.sendMessage("§cYou should specify the name of the tribe!");
                return true;
            }
                player.sendMessage("§aYou have created the tribe: " + ChatColor.GOLD+params[1]);
                return true;
        }
        return false;
    }
}
