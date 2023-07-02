package fr.edminecoreteam.core;

import fr.edminecoreteam.core.utils.ChangeHubInfo;
import fr.edminecoreteam.core.utils.FoundLobby;
import fr.edminecoreteam.core.utils.SkullNBT;
import fr.edminecoreteam.core.utils.TitleBuilder;
import fr.edminecoreteam.core.listeners.Instance;
import fr.edminecoreteam.core.listeners.PlayerListeners;
import fr.edminecoreteam.core.points.PointsDataManager;
import fr.edminecoreteam.core.scoreboard.JoinScoreboardEvent;
import fr.edminecoreteam.core.scoreboard.LeaveScoreboardEvent;
import fr.edminecoreteam.core.scoreboard.ScoreboardManager;
import fr.edminecoreteam.core.teams.ChatTeam;
import fr.edminecoreteam.core.teams.TabListTeams;
import fr.edminecoreteam.core.admin.AdminCommand;
import fr.edminecoreteam.core.admin.AdminInteractions;
import fr.edminecoreteam.core.admin.AdminMenu;
import fr.edminecoreteam.core.api.MySQL;
import fr.edminecoreteam.core.data.BlocsPlaceDataManager;
import fr.edminecoreteam.core.gamemanager.BlockManager;
import fr.edminecoreteam.core.gamemanager.InteractionGames;
import fr.edminecoreteam.core.gamemanager.LoadWorld;
import fr.edminecoreteam.core.gamemanager.gui.ChooseBlock;
import fr.edminecoreteam.core.gamemanager.gui.Difficulty;
import fr.edminecoreteam.core.listeners.EventsListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

public class Core extends JavaPlugin implements PluginMessageListener 
{
    private static Core instance;
    public int srvNumber;
    public boolean isForceStart = false;
    public BlockManager blockManager;
    private HashMap<Player, Integer> playerBloc = new HashMap<>();
    private HashMap<Player, Integer> playerPage = new HashMap<>();
	private HashMap<Player, String> playerPageType = new HashMap<>();
    private PointsDataManager pointsDataManager;
    private BlocsPlaceDataManager blocsPlaceDataManager;
    private ScoreboardManager scoreboardManager;
    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;
    private List<Player> players;
    private List<String> playersName;
    public String getGameWorldName;
    public TitleBuilder title;
    public MySQL database;
    public int timerJump;
    public int timerJump(int i) { this.timerJump = i; return i; }
    public int timerEnd;
    public int timerEnd(int i) { this.timerEnd = i; return i; }
    public int timerGame;
    public int timerGame(int i) { this.timerGame = i; return i; }
    public int timerStart;
    public int timerStart(int i) { this.timerStart = i; return i; }
    
    //Initialisation des votes de cartes
    
    //Initialisation des équipes
    private List<String> deathPlayer;
    private List<String> specPlayer;
    private List<String> waitForJump;
    private List<String> inJump;
    private List<String> livingPlayer;
    
    private List<String> easy;
    private List<String> normal;
    private List<String> hard;
    
    
    private State state;
    
    public Core() {
    	title = new TitleBuilder();
    	blockManager = new BlockManager();
    	
        players = new ArrayList<Player>();
        playersName = new ArrayList<String>();
        waitForJump = new ArrayList<String>();
        inJump = new ArrayList<String>();
        specPlayer = new ArrayList<String>();
        deathPlayer = new ArrayList<String>();
        livingPlayer = new ArrayList<String>();
        
        easy = new ArrayList<String>();
        normal = new ArrayList<String>();
        hard = new ArrayList<String>();

    }
    
    public void onEnable() {
    	saveDefaultConfig();
    	this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	    this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    	
    	MySQLConnect();
        setState(State.WAITING);
        ScoreboardManager();
        registerListener();
        
        getGameWorld();
        ChangeHubInfo srvInfo = new ChangeHubInfo(this.getServer().getServerName());
        srvInfo.setMOTD("WAITING");
        FoundLobby fLobby = new FoundLobby();
        srvNumber = fLobby.getServerPerGroup();
    }
    
    @Override
    public void onDisable() {
    	this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
	    this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    	getScoreboardManager().onDisable();
    }
    
    private void MySQLConnect() {
        Core.instance = this;
        (this.database = new MySQL("jdbc:mysql://", "45.140.165.235", "22728-database", "22728-database", "S5bV5su4p9")).connexion();
        database.creatingTableDeACoudre();
    }
    
    private void registerListener() {
        Core.instance = this;
        pointsDataManager = new PointsDataManager();
        blocsPlaceDataManager = new BlocsPlaceDataManager();
		Bukkit.getPluginManager().registerEvents((Listener)new EventsListener(this), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new PlayerListeners(this), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new LoadWorld(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new TabListTeams(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ChatTeam(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new Instance(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new SkullNBT(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new InteractionGames(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new Difficulty(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ChooseBlock(), (Plugin)this);
        
        Bukkit.getPluginManager().registerEvents((Listener)new AdminMenu(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new AdminInteractions(), (Plugin)this);
        this.getCommand("game").setExecutor((CommandExecutor)new AdminCommand());
    }
    
    private void getGameWorld()
    {
    	String world = LoadWorld.getRandomSubfolderName("gameTemplate/");
    	LoadWorld.createGameWorld(world);
    	getGameWorldName = world;
    }
    
    private void ScoreboardManager() {
    	instance = this;
    	
    	Bukkit.getPluginManager().registerEvents(new JoinScoreboardEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LeaveScoreboardEvent(), this);
    	
    	scheduledExecutorService = Executors.newScheduledThreadPool(16);
    	executorMonoThread = Executors.newScheduledThreadPool(1);
    	scoreboardManager = new ScoreboardManager();
    }
    
    public int getBlocSelect(Player player) {
        if (playerBloc.containsKey(player)) {
            return playerBloc.get(player);
        }
        return 0;
    }
	
	public void addBlocSelect(Player player, int value) {
		playerBloc.put(player, value);
    }

    // Méthode pour supprimer un joueur et sa valeur int associée de la carte
    public void removeBlocSelect(Player player) {
    	playerBloc.remove(player);
    }
    
    
    public void setState(State state) {
        this.state = state;
    }
    
    public boolean isState(State state) {
        return this.state == state;
    }
    
    public List<Player> getPlayers() {
        return this.players;
    }
    
    public List<String> getPlayersName() {
        return this.playersName;
    }
    
    public List<String> getWaitForJump() {
    	return this.waitForJump;
    }
    
    public List<String> getInJump() {
    	return this.inJump;
    }
    
    public List<String> getSpecPlayer() {
    	return this.specPlayer;
    }
    
    public List<String> getDeathPlayer() {
    	return this.deathPlayer;
    }
    
    public List<String> getEasyJump() {
    	return this.easy;
    }
    
    public List<String> getNormalJump() {
    	return this.normal;
    }
    
    public List<String> getHardJump() {
    	return this.hard;
    }
    
    public List<String> getIsLiving() {
    	return this.livingPlayer;
    }
    
    public void addPoints(Player player, int amount) {
    	pointsDataManager.addPoints(player.getUniqueId(), amount);
    }

    public void removePoints(Player player, int amount) {
    	pointsDataManager.removePoints(player.getUniqueId(), amount);
    }

    public int getPoints(Player player) {
        return pointsDataManager.getPlayerData(player.getUniqueId()).getPoints();
    }
    
    public Player getPlayerWithMostPoints(List<Player> players) {
        if (players.isEmpty()) {
            return null;
        }

        Player playerWithMostCoins = players.get(0);
        int maxCoins = getPoints(playerWithMostCoins);

        for (Player player : players) {
            int coins = getPoints(player);
            if (coins > maxCoins) {
                maxCoins = coins;
                playerWithMostCoins = player;
            }
        }

        return playerWithMostCoins;
    }
    
    public void addBlocs(Player player, int amount) {
    	blocsPlaceDataManager.addBlocs(player.getUniqueId(), amount);
    }

    public void removeBlocs(Player player, int amount) {
    	blocsPlaceDataManager.removeBlocs(player.getUniqueId(), amount);
    }

    public int getBlocsPlace(Player player) {
        return blocsPlaceDataManager.getPlayerData(player.getUniqueId()).getPoints();
    }
    
    
    public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation)
    		  throws IOException {
    		    Files.walk(Paths.get(sourceDirectoryLocation))
    		      .forEach(source -> {
    		          Path destination = Paths.get(destinationDirectoryLocation, source.toString()
    		            .substring(sourceDirectoryLocation.length()));
    		          try {
    		              Files.copy(source, destination);
    		          } catch (IOException e) {
    		              e.printStackTrace();
    		          }
    		      });
    		}
    
    @Override
	public void onPluginMessageReceived(String channel, Player player, byte[] message) 
	{
	    if (!channel.equals("BungeeCord")) 
	    {
	      return;
	    }
	    ByteArrayDataInput in = ByteStreams.newDataInput(message);
	    String subchannel = in.readUTF();
	    if (subchannel.equals("SomeSubChannel")) 
	    {
	      // Use the code sample in the 'Response' sections below to read
	      // the data.
	    }
	}
    
    public int getPage(Player player) {
        if (playerPage.containsKey(player)) {
            return playerPage.get(player);
        }
        return 0;
    }
	
	public void addPage(Player player, int value) {
		playerPage.put(player, value);
    }

	
    public void removePage(Player player) {
    	playerPage.remove(player);
    }
    
    public String getPageType(Player player) {
        if (playerPageType.containsKey(player)) {
            return playerPageType.get(player);
        }
        return "";
    }
	
	public void addPageType(Player player, String value) {
		playerPageType.put(player, value);
    }

	
    public void removePageType(Player player) {
    	playerPageType.remove(player);
    }
    
    public static Core getInstance() {
        return Core.instance;
    }
    
    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }
    
    public ScheduledExecutorService getExecutorMonoThread() {
        return this.executorMonoThread;
    }
    
    public ScheduledExecutorService getScheduledExecutorService() {
        return this.scheduledExecutorService;
    }
    
    public static Plugin getPlugin() {
        return null;
    }
}
