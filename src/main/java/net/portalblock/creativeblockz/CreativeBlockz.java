package net.portalblock.creativeblockz;

import com.worldcretornica.plotme.PlotGen;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMe;
import net.portalblock.creativeblockz.commands.CBCommand;
import net.portalblock.creativeblockz.commands.Donor;
import net.portalblock.creativeblockz.commands.Member;
import net.portalblock.creativeblockz.commands.Spawn;
import net.portalblock.creativeblockz.listeners.GeneralListener;
import net.portalblock.creativeblockz.listeners.JoinListener;
import net.portalblock.creativeblockz.worlds.LobbyGen;
import net.portalblock.creativeblockz.worlds.LobbyWorld;
import net.portalblock.creativeblockz.worlds.MemberWorld;
import net.portalblock.creativeblockz.worlds.RankWorld;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by portalBlock on 2/22/14.
 */
public class CreativeBlockz extends JavaPlugin {
    public static CreativeBlockz instance;
    public static World memberWorld;
    public static World rankWorld;
    public static World lobby;

    public static Location lobbyL;
    public static Location defaultL;
    public static Location rankL;

    @Override
    public void onEnable(){
        instance = this;
        saveResource("cbConfig.yml", false);
        getCommand("creativeblockz").setExecutor(new CBCommand());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("donor").setExecutor(new Donor());
        getCommand("member").setExecutor(new Member());

        //STARTUP PLOTME
        PlotMe plotMe = new PlotMe();
        plotMe.onEnable();

        getServer().getScheduler().runTask(this, new Runnable() {
            @Override
            public void run() {
                new LobbyWorld();
                new MemberWorld();
                new RankWorld();
                loadSpawns();
            }
        });


        //Register JoinListener
        getServer().getPluginManager().registerEvents(new JoinListener(), this);

        //Register the GeneralListener
        getServer().getPluginManager().registerEvents(new GeneralListener(), this);

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldname, String id)
    {
        if(worldname.equals("Lobby")){
            return new LobbyGen(instance);
        }
        if(PlotManager.isPlotWorld(worldname))
        {
            return new PlotGen(PlotManager.getMap(worldname));
        }
        else
        {
            PlotMe.logger.warning(PlotMe.PREFIX + "Configuration not found for PlotMe world '" + worldname + "' Using defaults");
            return new PlotGen();
        }
    }


    private void loadSpawns(){
        //Register the config files
        File file = new File(CreativeBlockz.instance.getDataFolder(), "cbConfig.yml");
        FileConfiguration cbConfig = YamlConfiguration.loadConfiguration(file);

        //Load spawn for lobby.
        if(cbConfig.getBoolean("lobby.isSet")){
            String w = cbConfig.getString("lobby.world");
            double x = cbConfig.getDouble("lobby.x");
            double y = cbConfig.getDouble("lobby.y");
            double z = cbConfig.getDouble("lobby.z");
            double yaw = cbConfig.getDouble("lobby.yaw");
            double pitch = cbConfig.getDouble("lobby.pitch");
            lobbyL = new Location(getServer().getWorld(w), x, y, z, (float) yaw, (float) pitch);
            getLogger().info("Spawn for lobby world loaded at: "+lobbyL.toString());
        }

        //Load spawn for defaultBlockz.
        if(cbConfig.getBoolean("default.isSet")){
            String w = cbConfig.getString("default.world");
            double x = cbConfig.getDouble("default.x");
            double y = cbConfig.getDouble("default.y");
            double z = cbConfig.getDouble("default.z");
            double yaw = cbConfig.getDouble("default.yaw");
            double pitch = cbConfig.getDouble("default.pitch");
            defaultL = new Location(getServer().getWorld(w), x, y, z, (float) yaw, (float) pitch);
            getLogger().info("Spawn for member world loaded at: "+defaultL.toString());
        }

        //Load spawn for rankBlockz.
        if(cbConfig.getBoolean("rank.isSet")){
            String w = cbConfig.getString("rank.world");
            double x = cbConfig.getDouble("rank.x");
            double y = cbConfig.getDouble("rank.y");
            double z = cbConfig.getDouble("rank.z");
            double yaw = cbConfig.getDouble("rank.yaw");
            double pitch = cbConfig.getDouble("rank.pitch");
            rankL = new Location(getServer().getWorld(w), x, y, z, (float) yaw, (float) pitch);
            getLogger().info("Spawn for rank world loaded at: "+rankL.toString());
        }
    }
}
