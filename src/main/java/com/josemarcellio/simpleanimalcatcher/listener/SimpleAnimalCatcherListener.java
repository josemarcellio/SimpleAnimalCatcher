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
        String leadName = ChatColor.translateAlternateColorCodes('&',
                configuration.getString("lead.name"));

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (mainHand.hasItemMeta() && mainHand.getItemMeta().getDisplayName().equals(leadName)) {
            event.setCancelled(true);

            int leadAmount = mainHand.getAmount();
            if (leadAmount > 1) {
                mainHand.setAmount(leadAmount - 1);
            } else {
                mainHand.setType(Material.AIR);
            }

            ((SpawnEggMeta) itemMeta).setSpawnedType(event.getEntity().getType());
            itemStack.setItemMeta(itemMeta);
            event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), itemStack);

            event.getEntity().remove();

            boolean catchMessageEnabled = configuration.getBoolean("catch-message.enable");
            if (catchMessageEnabled) {
                String catchMessage = ChatColor.translateAlternateColorCodes('&',
                        configuration.getString("catch-message.message").replace("{animal}",
                                entityName));
                player.sendMessage(catchMessage);
            }
        }
    }
}
