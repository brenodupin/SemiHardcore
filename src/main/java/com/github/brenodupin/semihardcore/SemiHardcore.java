package com.github.brenodupin.semihardcore;

import com.github.brenodupin.semihardcore.handlers.DeathHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class SemiHardcore extends JavaPlugin {

    public FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        instance=this;
        Bukkit.getLogger().info("[SemiHardcore] - Enable");
        new DeathHandler(this);

        config.addDefault("start-percentage",80.0);
        config.addDefault("end-percentage",50.0);
        config.addDefault("decrement-percentage",0.2);
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[SemiHardcore] - Disable");
    }

    public static SemiHardcore instance;
}
