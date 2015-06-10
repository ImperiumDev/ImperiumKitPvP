package dev.dlange.imperiumkitpvp.scoreboard;

import dev.dlange.imperiumkitpvp.Core;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

public class Scoreboard {

    public void setScoreboard(final Player p) {
        org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        if (board.getObjective(p.getName()) == null) {
            board.registerNewObjective(p.getName(), "dummy");
        }
        Objective obj = board.getObjective(p.getName());
        obj.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "KitPvP");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        obj.getScore("Kills:").setScore(Core.plugin.stats.getKills(p));
        obj.getScore("Deaths:").setScore(Core.plugin.stats.getDeaths(p));
        obj.getScore("Coins:").setScore(Core.plugin.stats.getCoins(p));
        p.setScoreboard(board);
    }
}
