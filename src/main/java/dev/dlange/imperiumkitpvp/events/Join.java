package dev.dlange.imperiumkitpvp.events;

import dev.dlange.imperiumkitpvp.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class Join implements Listener {
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Core.plugin, new Runnable() {
            @Override
            public void run() {
                if (!Core.plugin.sqlManager.playerDataContainsPlayer(p)) {
                    try {
                        Core.plugin.sqlManager.setupPlayer2(p);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                if (!Core.plugin.sqlManager.playerDataContainsPlayer2(p)) {
                    try {
                        Core.plugin.sqlManager.setupPlayer1(p);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                try {
                    if (Core.plugin.stats.getPlayerCoins.get(p.getName()) == null)
                        Core.plugin.stats.getPlayerCoins.put(p.getName(), Core.plugin.sqlManager.getCoins(p));
                    if (Core.plugin.stats.getPlayerKills.get(p.getName()) == null)
                        Core.plugin.stats.getPlayerKills.put(p.getName(), Core.plugin.sqlManager.getKills(p));
                    if (Core.plugin.stats.getPlayerGlobalCoins.get(p.getName()) == null)
                        Core.plugin.stats.getPlayerGlobalCoins.put(p.getName(), Core.plugin.sqlManager.getGlobalCoins(p));
                    if (Core.plugin.stats.getPlayerDeaths.get(p.getName()) == null)
                        Core.plugin.stats.getPlayerDeaths.put(p.getName(), Core.plugin.sqlManager.getDeaths(p));
                    if (!Core.plugin.sqlManager.getName1(p).equals(p.getName()))
                        Core.plugin.sqlManager.setName1(p);
                    if (!Core.plugin.sqlManager.getName2(p).equals(p.getName()))
                        Core.plugin.sqlManager.setName2(p);

                    e.getPlayer().sendMessage("Deaths - " + Core.plugin.stats.getDeaths(p));
                    e.getPlayer().sendMessage("Kills - " + Core.plugin.stats.getKills(p));
                    e.getPlayer().sendMessage("Coins - " + Core.plugin.stats.getCoins(p));
                    e.getPlayer().sendMessage(" ");
                    e.getPlayer().sendMessage("Global Coins - " + Core.plugin.stats.getGlobalCoins(p));

                    Core.plugin.sb.setScoreboard(e.getPlayer());
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
