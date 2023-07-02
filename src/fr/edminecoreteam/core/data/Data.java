package fr.edminecoreteam.core.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.edminecoreteam.core.api.MySQL;

public class Data 
{
	private String p;
	private String table;
	
	public Data(String p) {
		this.p = p;
		this.table = "ed_deacoudre";
	}
	
	public void createData() 
    {
        if (!hasData()) 
        {
            try 
            {
                PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO " + table + " (player_name, player_played_games ,player_victoires, player_defaites, player_blocs_places, player_bloc_select) VALUES (?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, p); /*player_name*/
                preparedStatement.setInt(2, 0); /*player_played_games*/
                preparedStatement.setInt(3, 0); /*player_victoires*/
                preparedStatement.setInt(4, 0); /*player_defaites*/
                preparedStatement.setInt(5, 0); /*player_blocs_places*/
                preparedStatement.setInt(6, 0); /*player_bloc_select*/
                preparedStatement.execute();
                preparedStatement.close();
            }
            catch (SQLException e) 
            {
                e.toString();
            }
        }
    }
	
	public boolean hasData() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_victoires FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        }
        catch (SQLException e) 
        {
            e.toString();
            return false;
        }
    }
	
	public int getPlayedGames() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_played_games FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
                id = rs.getInt("player_played_games");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
	
	public int getWin() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_victoires FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
                id = rs.getInt("player_victoires");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
	
	public int getLose() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_defaites FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
                id = rs.getInt("player_defaites");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
	
	public int getBlocsPlaces() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_blocs_places FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
                id = rs.getInt("player_blocs_places");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
	
	public int getBlocSelect() 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT player_bloc_select FROM " + table + " WHERE player_name = ?");
            preparedStatement.setString(1, p);
            int id = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) 
            {
                id = rs.getInt("player_bloc_select");
            }
            preparedStatement.close();
            return id;
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
            return 0;
        }
    }
	
	public void addPlayedGames(int amount) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_played_games = player_played_games + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void addVictoires(int amount) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_victoires = player_victoires + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void addDefaites(int amount) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_defaites = player_defaites + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void addBlocsPlaces(int amount) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_blocs_places = player_blocs_places + ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
	
	public void setBloc(int amount) 
    {
        try 
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE " + table + " SET player_bloc_select = ? WHERE player_name = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, p);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
