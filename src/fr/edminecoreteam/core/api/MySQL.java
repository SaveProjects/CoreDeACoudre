package fr.edminecoreteam.core.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL 
{
	private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;
    private static Connection connection;
    
    public MySQL(final String urlBase, final String host, final String database, final String userName, final String password) {
        this.urlBase = urlBase;
        this.host = host;
        this.database = database;
        this.userName = userName;
        this.password = password;
    }
    
    public static Connection getConnection() {
        return MySQL.connection;
    }
    
    public void connexion() {
        if (!this.isOnline()) {
            try {
                MySQL.connection = DriverManager.getConnection(String.valueOf(this.urlBase) + this.host + "/" + this.database, this.userName, this.password);
                System.out.println("");
                System.out.println(" EDMINE API");
                System.out.println("   Informations:");
                System.out.println("    \u2022 Base de donn\u00e9s: Connect\u00e9");
                System.out.println("");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void deconnexion() {
        if (!this.isOnline()) {
            try {
                MySQL.connection.close();
                System.out.println("");
                System.out.println(" EDMINE API");
                System.out.println("   Informations:");
                System.out.println("    \u2022 Base de donn\u00e9s: D\u00e9connect\u00e9");
                System.out.println("");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void creatingTableDeACoudre() {
        try 
        {
        	PreparedStatement stm = MySQL.connection.prepareStatement("CREATE TABLE IF NOT EXISTS ed_deacoudre (`player_name` varchar(255) NOT NULL, `player_played_games` int(11), `player_victoires` int(11), `player_defaites` int(11), `player_blocs_places` int(11), `player_bloc_select` int(11), PRIMARY KEY (`player_name`), UNIQUE(`player_name`), INDEX(`player_name`)) CHARACTER SET utf8");
            stm.execute();
            stm.close();
            System.out.println("ED-NETWORK API");
			System.out.println("DATABASE: ed_deacoudre loaded.");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean isOnline() {
        try {
            if (MySQL.connection == null || MySQL.connection.isClosed()) {
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
