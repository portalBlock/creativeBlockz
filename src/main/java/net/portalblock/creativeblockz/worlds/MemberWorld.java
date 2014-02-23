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
public class MemberWorld {
    public MemberWorld(){
        if(CreativeBlockz.instance.getServer().getWorld("memberWorld") != null){
            CreativeBlockz.instance.getLogger().info("memberWorld Loaded, no need to go further.");
            return;
        }

        File dir = new File("/");
        for(File file : dir.listFiles()){
            if(file.getName().equals("memberWorld")&&file.isDirectory()){
                CreativeBlockz.instance.getLogger().info("memberWorld exists :D I will create an instance now.");
                WorldCreator wc = new WorldCreator("memberWorld");
                wc.generator(new PlotGen(PlotManager.getMap("memberWorld")));
                wc.environment(World.Environment.NORMAL);
                CreativeBlockz.memberWorld = wc.createWorld();
                return;
            }
        }

        CreativeBlockz.instance.getLogger().info("memberWorld doesn't exist, I will make one now :D");
        WorldCreator wc = new WorldCreator("memberWorld");
        wc.generator(new PlotGen(PlotManager.getMap("memberWorld")));
        wc.environment(World.Environment.NORMAL);
        CreativeBlockz.memberWorld = wc.createWorld();
    }
}
