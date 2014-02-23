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
        if(CreativeBlockz.instance.getServer().getWorld("defaultBlockz") != null){
            CreativeBlockz.instance.getLogger().info("defaultBlockz is already loaded, why is this even being called???");
            return;
        }

        CreativeBlockz.instance.getLogger().info("Creating instance of defaultBlockz now :D");
        WorldCreator worldCreator = new WorldCreator("defaultBlockz");
        worldCreator.generator(new PlotGen(PlotManager.getMap("defaultBlockz")));
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.generateStructures(false);
        World world = worldCreator.createWorld();
        world.setSpawnFlags(false, false);
        world.setPVP(false);
        world.setStorm(false);
        world.setThundering(false);
        world.setAutoSave(true);

        CreativeBlockz.instance.memberWorld = world;
    }
}
