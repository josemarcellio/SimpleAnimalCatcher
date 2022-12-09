package com.josemarcellio.simpleanimalcatcher.command;

import com.josemarcellio.simpleanimalcatcher.SimpleAnimalCatcherMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SimpleAnimalCatcherCommand implements CommandExecutor {

    private final SimpleAnimalCatcherMain plugin;
    public SimpleAnimalCatcherCommand(SimpleAnimalCatcherMain plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("simpleanimalcatcher.admin")) {
            FileConfiguration configuration = this.plugin.getConfig();

            ItemStack itemStack = new ItemStack(Material.LEASH, 1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', configuration.getString("lead.name")));
            itemStack.setItemMeta(itemMeta);

            player.getInventory().addItem(itemStack);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&7You receive a "
                            + configuration.getString("lead.name")));

        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cYou don't have permission!"));
        }
        return false;
    }
}
