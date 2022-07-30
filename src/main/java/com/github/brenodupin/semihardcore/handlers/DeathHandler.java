package com.github.brenodupin.semihardcore.handlers;

import com.github.brenodupin.semihardcore.SemiHardcore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
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
    // Bukkit.getLogger().info("SemiHardcore - EVENT TRIGGER");

    // get config percentages
    Player player = event.getPlayer();
    int days = (int) Math.ceil((double) player.getStatistic(Statistic.TIME_SINCE_DEATH) / 2400);
    float start_percentage =
        (float) SemiHardcore.instance.getConfig().getDouble("start-percentage");
    float decrement_percentage =
        (float) SemiHardcore.instance.getConfig().getDouble("decrement-percentage");
    float min = (float) SemiHardcore.instance.getConfig().getDouble("end-percentage");
    float percent = (start_percentage - days * decrement_percentage);

    // if calculated percentage less than end-percentage, use end-percentage
    if (percent < min) percent = min / 100;
    else percent = percent / 100; // percentage changed form (5% -> 0.05)

    Random rand = new Random();
    int total_itens = event.getDrops().size();
    int total_lost = (int) Math.ceil(total_itens * percent);
    for (int i = 0; i < total_lost; i++) event.getDrops().remove(rand.nextInt(total_itens - i));

    Component msg =
        Component.text("Otário " + player.getName() + " perdeu " + total_lost + " itens");
    event.getPlayer().getServer().broadcast(msg.color(TextColor.color(0xFF0E0E)));
    Bukkit.getLogger()
        .info(
            "[SemiHardcore] Player "
                + player.getName()
                + " died and lost "
                + total_lost
                + " itens");
  }
}
