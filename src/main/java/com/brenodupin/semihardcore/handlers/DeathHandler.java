package com.brenodupin.semihardcore.handlers;

import com.brenodupin.semihardcore.SemiHardcore;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.util.Random;

public class DeathHandler implements Listener {
    public DeathHandler(SemiHardcore plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent event) {
        //Bukkit.getLogger().info("SemiHardcore - EVENT TRIGGER");

        double percent = (double) SemiHardcore.instance.getConfig().getInt("start-percentage") - (int) event.getPlayer().getStatistic(Statistic.TIME_SINCE_DEATH)/2400;
        int min = SemiHardcore.instance.getConfig().getInt("end-percentage");
        if (percent*100 < min) percent = (double) min/100;
        else percent = percent/100;

        Random rand = new Random();
        int total = (int) Math.ceil(event.getDrops().size()*percent);
        String message = "Otário " + event.getPlayer().getName() + " morreu e perdeu " + total + " itens";
        Bukkit.broadcastMessage(message);

        //Bukkit.getLogger().info("death time: " + event.getPlayer().getStatistic(Statistic.TIME_SINCE_DEATH)*50);

        for (int i = 0; i < total; i++) {
            event.getDrops().remove(rand.nextInt(event.getDrops().size()));
        }
        //Bukkit.getLogger().info(event.getDrops().toString());
    }
}