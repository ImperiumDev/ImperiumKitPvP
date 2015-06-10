package dev.dlange.imperiumkitpvp.stats;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Stats {

    public HashMap<String, Integer> getPlayerKills = new HashMap<String, Integer>();
    public HashMap<String, Integer> getPlayerDeaths = new HashMap<String, Integer>();
    public HashMap<String, Integer> getPlayerCoins = new HashMap<String, Integer>();
    public HashMap<String, Integer> getPlayerGlobalCoins = new HashMap<String, Integer>();

    public int getKills(Player p) {
        return getPlayerKills.get(p.getName());
    }

    public int getCoins(Player p) {
        return getPlayerCoins.get(p.getName());
    }

    public int getDeaths(Player p) {
        return getPlayerDeaths.get(p.getName());
    }

    public int getGlobalCoins(Player p) {
        return getPlayerGlobalCoins.get(p.getName());
    }

    public void addGlobalCoins(Player p, int amount) {
        getPlayerGlobalCoins.put(p.getName(), getGlobalCoins(p) + amount);
    }

    public void addCoins(Player p, int amount) {
        getPlayerCoins.put(p.getName(), getCoins(p) + amount);
    }

    public void addDeaths(Player p, int amount) {
        getPlayerDeaths.put(p.getName(), getDeaths(p) + amount);
    }

    public void addKills(Player p, int amount) {
        getPlayerKills.put(p.getName(), getKills(p) + amount);
    }
}
