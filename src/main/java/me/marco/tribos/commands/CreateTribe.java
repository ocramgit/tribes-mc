package me.marco.tribos.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class CreateTribe implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] params) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can perform this command!");
            return true;
        }
        Player player = (Player) commandSender;

            Inventory inventory = Bukkit.createInventory(player, 27, "§4Tribes menu");
            ItemStack getHayItem = new ItemStack(Material.HAY_BLOCK);
            ItemMeta getHayItemMeta = getHayItem.getItemMeta();
            assert getHayItemMeta != null;
            getHayItemMeta.setDisplayName("§7Create a new Tribe");
            getHayItem.setItemMeta(getHayItemMeta);
            inventory.setItem(11, getHayItem);
            player.openInventory(inventory);

            return true;
    }
}
