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
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;
        FileConfiguration config = plugin.getConfig();

        if (args.length == 1 && args[0].equalsIgnoreCase("give")) {
            if (!player.hasPermission("simpleanimalcatcher.admin")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&cYou don't have permission to use this command."));
                return true;
            }

            ItemStack itemStack = new ItemStack(Material.LEASH, 1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',
                    config.getString("lead.name")));
            itemStack.setItemMeta(itemMeta);

            player.getInventory().addItem(itemStack);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&7You receive a " + config.getString("lead.name")));
        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!player.hasPermission("simpleanimalcatcher.admin")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&cYou don't have permission to use this command."));
                return true;
            }

            plugin.reloadConfig();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&7Configuration reloaded."));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cInvalid usage. Use /sac give or /sac reload"));
        }

        return true;
    }
}
