package com.josemarcellio.simpleanimalcatcher;

import com.josemarcellio.simpleanimalcatcher.command.SimpleAnimalCatcherCommand;
import com.josemarcellio.simpleanimalcatcher.listener.SimpleAnimalCatcherListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleAnimalCatcherMain extends JavaPlugin {

    @Override
    public void onEnable() {

        this.getLogger().info("Simple Animal Catcher by JoseMarcellio");

        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents( new SimpleAnimalCatcherListener(this), this);
        this.getCommand("simpleanimalcatcher").setExecutor(new SimpleAnimalCatcherCommand(this));
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Simple Animal Catcher by JoseMarcellio");
    }
}
