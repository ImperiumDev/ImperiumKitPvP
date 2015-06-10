package dev.dlange.imperiumkitpvp;

import dev.dlange.imperiumkitpvp.database.MySQLManager;
import dev.dlange.imperiumkitpvp.events.Death;
import dev.dlange.imperiumkitpvp.events.Join;
import dev.dlange.imperiumkitpvp.scoreboard.Scoreboard;
import dev.dlange.imperiumkitpvp.stats.Stats;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Core extends JavaPlugin {

    public static Core plugin;

    // YES I USE A MYSQL LIB LICK ME <3
    public MySQLManager sqlManager;
    public Stats stats;
    public Scoreboard sb;

    @Override
    public void onEnable() {
        plugin = this;
        sqlManager = new MySQLManager(this);
        stats = new Stats();
        sb = new Scoreboard();
        try {
            sqlManager.setupDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setupEvents();
    }

    @Override
    public void onDisable() {
        try {
            sqlManager.closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setupEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new Join(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Death(), this);
    }
}
