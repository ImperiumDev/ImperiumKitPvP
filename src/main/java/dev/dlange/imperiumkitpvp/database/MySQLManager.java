package dev.dlange.imperiumkitpvp.database;

import dev.dlange.imperiumkitpvp.Core;
import dev.dlange.imperiumkitpvp.database.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLManager {

    private final Core main;
    private MySQL db;

    public MySQLManager(Core main) {
        this.main = main;
    }

    public void setupDB() throws SQLException, ClassNotFoundException {
        this.db = new MySQL(this.main, "sql3.freemysqlhosting.net", "3306",
                "sql380146", "USERNAME", "PASSWORD");
        this.db.openConnection();
        Statement statement = this.db.getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS `data_kitpvp` (`uuid` varchar(50),`name` varchar(17),`kills` int,`deaths` int,`coins` int)");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS `data_global` (`uuid` varchar(50),`name` varchar(17),`coins` int)");
        statement.close();
    }

    public void closeDB() throws SQLException, ClassNotFoundException {
        for (String s : Core.plugin.stats.getPlayerCoins.keySet()) setCoins(Bukkit.getOfflinePlayer(s), Core.plugin.stats.getPlayerCoins.get(s));
        for (String s : Core.plugin.stats.getPlayerDeaths.keySet()) setDeath(Bukkit.getOfflinePlayer(s), Core.plugin.stats.getPlayerDeaths.get(s));
        for (String s : Core.plugin.stats.getPlayerGlobalCoins.keySet()) setGlobalCoins(Bukkit.getOfflinePlayer(s), Core.plugin.stats.getPlayerGlobalCoins.get(s));
        for (String s : Core.plugin.stats.getPlayerKills.keySet()) setKill(Bukkit.getOfflinePlayer(s), Core.plugin.stats.getPlayerKills.get(s));

        this.db.closeConnection();
    }

    public void setupPlayer1(Player p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        PreparedStatement statement = this.db.getConnection().
                prepareStatement("INSERT INTO `data_global` (`uuid`,`name`,`coins`) VALUES ('" + pName + "','"+p.getName()+"','0');");
        statement.executeUpdate();
        statement.close();
    }

    public void setupPlayer2(Player p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        PreparedStatement statement = this.db.getConnection().
                prepareStatement("INSERT INTO `data_kitpvp` (`uuid`,`name`,`kills`, `deaths`, `coins`) VALUES ('" + pName + "','"+p.getName()+"','0', '0', '0');");
        statement.executeUpdate();
        statement.close();
    }

    public String getName1(OfflinePlayer p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        Statement statement = this.db.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `data_kitpvp` WHERE `uuid`='" + pName + "';");
        if (!rs.next()) return null;
        return rs.getString("name");
    }

    public void setName1(OfflinePlayer p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        PreparedStatement statement = this.db.getConnection().prepareStatement
                ("UPDATE `data_kitpvp` SET `name`='" + (p.getName()) + "' WHERE `uuid`='"
                        + pName + "';");
        statement.executeUpdate();
        statement.close();
    }

    public String getName2(OfflinePlayer p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        Statement statement = this.db.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `data_global` WHERE `uuid`='" + pName + "';");
        if (!rs.next()) return null;
        return rs.getString("name");
    }

    public void setName2(OfflinePlayer p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        PreparedStatement statement = this.db.getConnection().prepareStatement
                ("UPDATE `data_global` SET `name`='" + (p.getName()) + "' WHERE `uuid`='"
                        + pName + "';");
        statement.executeUpdate();
        statement.close();
    }

    public int getKills(OfflinePlayer p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        Statement statement = this.db.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `data_kitpvp` WHERE `uuid`='" + pName + "';");
        if (!rs.next()) return 0;
        return rs.getInt("kills");
    }

    public void setKill(OfflinePlayer p, int kills) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        PreparedStatement statement = this.db.getConnection().prepareStatement
                ("UPDATE `data_kitpvp` SET `kills`='" + (kills) + "' WHERE `uuid`='"
                        + pName + "';");
        statement.executeUpdate();
        statement.close();
    }

    public int getDeaths(OfflinePlayer p) throws SQLException, ClassNotFoundException {
        String pName = p.getName();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        Statement statement = this.db.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `data_kitpvp` WHERE `uuid`='" + pName + "';");
        if (!rs.next()) return 0;
        return rs.getInt("deaths");
    }

    public void setDeath(OfflinePlayer p, int kills) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        PreparedStatement statement = this.db.getConnection().prepareStatement
                ("UPDATE `data_kitpvp` SET `deaths`='" + kills + "' WHERE `uuid`='"
                        + pName + "';");
        statement.executeUpdate();
        statement.close();
    }

    public int getCoins(OfflinePlayer p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        Statement statement = this.db.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `data_kitpvp` WHERE `uuid`='" + pName + "';");
        if (!rs.next()) return 0;
        return rs.getInt("coins");
    }

    public void setCoins(OfflinePlayer p, int amount) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        PreparedStatement statement = this.db.getConnection().prepareStatement
                ("UPDATE `data_kitpvp` SET `coins`='" + amount + "' WHERE `uuid`='"
                        + pName + "';");
        statement.executeUpdate();
        statement.close();
    }

    public int getGlobalCoins(OfflinePlayer p) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        Statement statement = this.db.getConnection().createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `data_global` WHERE `uuid`='" + pName + "';");
        if (!rs.next()) return 0;
        return rs.getInt("coins");
    }

    public void setGlobalCoins(OfflinePlayer p, int amount) throws SQLException, ClassNotFoundException {
        String pName = p.getUniqueId().toString();
        if (!this.db.checkConnection()) {
            this.db.openConnection();
        }
        PreparedStatement statement = this.db.getConnection().prepareStatement
                ("UPDATE `data_global` SET `coins`='" + (amount) + "' WHERE `uuid`='"
                        + pName + "';");
        statement.executeUpdate();
        statement.close();
    }

    public synchronized boolean playerDataContainsPlayer(OfflinePlayer player) {
        boolean b1;
        try {
            PreparedStatement sql = this.db.getConnection().prepareStatement("SELECT * FROM `data_kitpvp` WHERE uuid=?;");
            sql.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = sql.executeQuery();
            boolean containsPlayer = resultSet.next();

            sql.close();
            resultSet.close();

            return containsPlayer;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public synchronized boolean playerDataContainsPlayer2(OfflinePlayer player) {
        try {
            PreparedStatement sql = this.db.getConnection().prepareStatement("SELECT * FROM `data_global` WHERE uuid=?;");
            sql.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = sql.executeQuery();
            boolean containsPlayer = resultSet.next();

            sql.close();
            resultSet.close();

            return containsPlayer;
        } catch (Exception e) {
            return false;
        }
    }
}
