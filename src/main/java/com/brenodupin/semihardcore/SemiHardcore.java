package com.brenodupin.semihardcore;

import com.brenodupin.semihardcore.handlers.DeathHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SemiHardcore extends JavaPlugin {

    public FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        instance=this;
        Bukkit.getLogger().info("Startup - SemiHardcore");
        new DeathHandler(this);

        config.addDefault("start-percentage",80);
        config.addDefault("end-percentage",50);
        config.addDefault("decrement-percentage",0.2);
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Shutting Down - SemiHardcore");
    }

    public static SemiHardcore instance;

/*
    private void checkConfig(FileConfiguration config) {
        if (config == null) {
            config.addDefault("start-percentage",0.8);
            config.addDefault("end-percentage",0.5);
            config.addDefault("decrement-percentage",0.002);
        }
    }
*/

}
