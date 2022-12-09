package com.josemarcellio.simpleanimalcatcher.listener;

import com.josemarcellio.simpleanimalcatcher.SimpleAnimalCatcherMain;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SpawnEggMeta;

public class SimpleAnimalCatcherListener implements Listener {

    private final SimpleAnimalCatcherMain plugin;
    public SimpleAnimalCatcherListener(SimpleAnimalCatcherMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeashEntity (PlayerLeashEntityEvent event) {
        Player player = event.getPlayer();
        String entityName = event.getEntity().getName();

        ItemStack itemStack = new ItemStack(Material.MONSTER_EGG);
        ItemMeta itemMeta = itemStack.getItemMeta();

        FileConfiguration configuration = this.plugin.getConfig();

        if (player.getInventory().getItemInMainHand().hasItemMeta()
                && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
                .equals(ChatColor.translateAlternateColorCodes('&',
                        configuration.getString("lead.name")))) {

            event.setCancelled(true);

            if (player.getInventory().getItemInMainHand().getAmount() > 1) {
                player.getInventory().getItemInMainHand().setAmount(player.getInventory()
                        .getItemInMainHand().getAmount() - 1);
            } else {
                player.getInventory().getItemInMainHand().setType(Material.AIR);
            }

            ((SpawnEggMeta) itemMeta).setSpawnedType(event.getEntity().getType());
            itemStack.setItemMeta(itemMeta);
            itemStack.setAmount(1);

            event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), itemStack);
            event.getEntity().remove();

            if (configuration.getBoolean("catch-message.enable")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        configuration.getString("catch-message.message")
                                .replace("{animal}", entityName)));
            }
        }
    }
}
