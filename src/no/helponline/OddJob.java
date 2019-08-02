package no.helponline;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import no.helponline.Commands.*;
import no.helponline.Events.*;
import no.helponline.Managers.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class OddJob extends JavaPlugin {
    public static HashMap<UUID, Location> deathChest = new HashMap<>();
    public static List<Location> deathTrappedChest = new ArrayList<>();
    private static OddJob instance;
    private EconManager econManager;
    private GuildManager guildManager;
    private PlayerManager playerManager;
    private LockManager lockManager;
    private MessageManager messageManager;
    private HomesManager homesManager;
    private ConfigManager configManager;
    private BanManager banManager;
    private TeleportManager teleportManager;
    private MySQLManager mySQLManager;
    private WorldManger worldManager;

    public static OddJob getInstance() {
        return instance;
    }

    private static Economy econ = null;
    private static Permission perms = null;

    public static HashMap<UUID, UUID> inChunk = new HashMap<>(); //player - guild
    public static boolean vault;


    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            log("Needing vault");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        instance = this;
        banManager = new BanManager();
        configManager = new ConfigManager();
        econManager = new EconManager();
        guildManager = new GuildManager();
        homesManager = new HomesManager();
        lockManager = new LockManager();
        messageManager = new MessageManager();
        playerManager = new PlayerManager();
        teleportManager = new TeleportManager();
        mySQLManager = new MySQLManager();

        getCommand("econ").setExecutor(new EconCommand());
        getCommand("guild").setExecutor(new GuildCommand());
        getCommand("homes").setExecutor(new HomesCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("locks").setExecutor(new LockCommand());
        getCommand("suicide").setExecutor(new SuicideCommand());
        getCommand("kill").setExecutor(new KillCommand());
        getCommand("tp").setExecutor(new TpCommand());
        getCommand("tpall").setExecutor(new TpAllCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("tpa").setExecutor(new TpACommand());
        getCommand("tpaccept").setExecutor(new TpAcceptCommand());
        getCommand("tpdeny").setExecutor(new TpDenyCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("give").setExecutor(new GiveCommand());
        getCommand("gamemode").setExecutor(new GameModeCommand());
        getCommand("gamemode").setTabCompleter(new GameModeCommand());
        getCommand("tppos").setExecutor(new TpPosCommand());
        getCommand("player").setExecutor(new PlayerCommand());
        //getCommand("world").setExecutor(new WorldCommand());

        configManager.load();

        setupEconomy();

        Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new LocksEvents(), this);
        //Bukkit.getPluginManager().registerEvents(new SofaEvent(), this);  //TODO Sleep?
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ArmorstandEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onDeath(), this);
    }

    public void onDisable() {

    }


    public void log(String message) {
        getLogger().log(Level.INFO, ChatColor.YELLOW + message);
    }


    public static Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            return false;
        }
        econ = (Economy) rsp.getProvider();
        return (econ != null);
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = (Permission) rsp.getProvider();
        return (perms != null);
    }

    public GuildManager getGuildManager() {
        return guildManager;
    }

    public HomesManager getHomesManager() {
        return homesManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public EconManager getEconManager() {
        return econManager;
    }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }

    public BanManager getBanManager() {
        return banManager;
    }

    public LockManager getLockManager() {
        return lockManager;
    }

    public MySQLManager getMySQLManager() {
        return mySQLManager;
    }

    public WorldManger getWorldManager() {
        return worldManager;
    }
}