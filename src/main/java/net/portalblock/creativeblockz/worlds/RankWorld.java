package net.portalblock.creativeblockz.worlds;

import com.worldcretornica.plotme.PlotGen;
import com.worldcretornica.plotme.PlotManager;
import net.portalblock.creativeblockz.CreativeBlockz;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;

/**
 * Created by portalBlock on 2/23/14.
 */
public class RankWorld {
    public RankWorld(){
        if(CreativeBlockz.instance.getServer().getWorld("rankWorld") != null){
            CreativeBlockz.instance.getLogger().info("rankWorld Loaded, no need to go further.");
            return;
        }

        File dir = new File("/");
        for(File file : dir.listFiles()){
            if(file.getName().equals("rankWorld")&&file.isDirectory()){
                CreativeBlockz.instance.getLogger().info("rankWorld exists :D I will create an instance now.");
                WorldCreator wc = new WorldCreator("rankWorld");
                wc.generator(new PlotGen(PlotManager.getMap("rankWorld")));
                wc.environment(World.Environment.NORMAL);
                CreativeBlockz.rankWorld = wc.createWorld();
                return;
            }
        }

        CreativeBlockz.instance.getLogger().info("rankWorld doesn't exist, I will make one now :D");
        WorldCreator wc = new WorldCreator("rankWorld");
        wc.generator(new PlotGen(PlotManager.getMap("rankWorld")));
        wc.environment(World.Environment.NORMAL);
        CreativeBlockz.rankWorld = wc.createWorld();
    }
}
