package com.github.brenodupin.semihardcore.handlers;

import com.github.brenodupin.semihardcore.SemiHardcore;
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

        //get config percentages
        int days = (int) Math.ceil( (double) event.getPlayer().getStatistic(Statistic.TIME_SINCE_DEATH)/2400);
        float start_percentage = (float) SemiHardcore.instance.getConfig().getDouble("start-percentage");
        float decrement_percentage = (float) SemiHardcore.instance.getConfig().getDouble("decrement-percentage");
        float min = (float) SemiHardcore.instance.getConfig().getDouble("end-percentage");
        float percent = (start_percentage - days*decrement_percentage);

        //if calculated percentage less than end-percentage, use end-percentage
        if (percent < min) percent = min/100;
        else percent = percent/100;
        //percentage changed from (5% -> 0.05) form

        Random rand = new Random();
        int total_lost = (int) Math.ceil(event.getDrops().size()*percent); //TODO not delete itens, but pick not lost itens.

        //TODO proper messages
        String message = "Otário " + event.getPlayer().getName() + " morreu e perdeu " + total_lost + " itens";
        Bukkit.broadcastMessage(message);

        //Bukkit.getLogger().info("death time: " + event.getPlayer().getStatistic(Statistic.TIME_SINCE_DEATH)*50);

        //TODO better performance?
        for (int i = 0; i < total_lost; i++) {
            event.getDrops().remove(rand.nextInt(event.getDrops().size()));
        }
        //Bukkit.getLogger().info(event.getDrops().toString());
    }
}