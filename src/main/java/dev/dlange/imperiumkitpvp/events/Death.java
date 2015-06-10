package dev.dlange.imperiumkitpvp.events;

import dev.dlange.imperiumkitpvp.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.SQLException;

public class Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Core.plugin.stats.addDeaths(e.getEntity(), 1);
        if (e.getEntity().getKiller() != null) {
            Core.plugin.stats.addKills(e.getEntity().getKiller(), 1);
            Core.plugin.stats.addGlobalCoins(e.getEntity().getKiller(), 1);
            Core.plugin.stats.addCoins(e.getEntity().getKiller(), 10);
        }
        Core.plugin.sb.setScoreboard(e.getEntity());
        Core.plugin.sb.setScoreboard(e.getEntity().getKiller());
    }
}
