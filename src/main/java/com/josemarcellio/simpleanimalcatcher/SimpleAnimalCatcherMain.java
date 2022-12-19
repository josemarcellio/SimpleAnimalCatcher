package com.josemarcellio.simpleanimalcatcher;

import com.josemarcellio.simpleanimalcatcher.command.SimpleAnimalCatcherCommand;
import com.josemarcellio.simpleanimalcatcher.listener.SimpleAnimalCatcherListener;
import com.josemarcellio.simpleanimalcatcher.metrics.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleAnimalCatcherMain extends JavaPlugin {

    @Override
    public void onEnable() {

        new Metrics(this, 17115);

        getLogger().info("Simple Animal Catcher by JoseMarcellio");

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents( new SimpleAnimalCatcherListener(this), this);
        getCommand("simpleanimalcatcher").setExecutor(new SimpleAnimalCatcherCommand(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Simple Animal Catcher by JoseMarcellio");
    }
}
