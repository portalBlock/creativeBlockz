package net.portalblock.creativeblockz;

import com.worldcretornica.plotme.PlotGen;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMe;
import net.portalblock.creativeblockz.commands.CBCommand;
import net.portalblock.creativeblockz.worlds.LobbyGen;
import net.portalblock.creativeblockz.worlds.LobbyWorld;
import net.portalblock.creativeblockz.worlds.MemberWorld;
import net.portalblock.creativeblockz.worlds.RankWorld;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by portalBlock on 2/22/14.
 */
public class CreativeBlockz extends JavaPlugin {
    public static CreativeBlockz instance;
    public static World memberWorld;
    public static World rankWorld;
    public static World lobby;

    @Override
    public void onEnable(){
        instance = this;

        getCommand("creativeblockz").setExecutor(new CBCommand());

        //TODO: Register the world commands and register the join listeners

        //STARTUP PLOTME
        PlotMe plotMe = new PlotMe();
        plotMe.onEnable();

        getServer().getScheduler().runTask(this, new Runnable() {
            @Override
            public void run() {
                new LobbyWorld();
                new MemberWorld();
                new RankWorld();
            }
        });
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
}
