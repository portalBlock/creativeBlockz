package net.portalblock.creativeblockz.worlds;

import net.portalblock.creativeblockz.CreativeBlockz;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;

/**
 * Created by portalBlock on 2/23/14.
 */
public class LobbyWorld {
    public LobbyWorld(){
        if(CreativeBlockz.instance.getServer().getWorld("lobbyWorld") != null){
            CreativeBlockz.instance.getLogger().info("lobbyWorld Loaded, no need to go further.");
            return;
        }

        File dir = new File("/");
        for(File file : dir.listFiles()){
            if(file.getName().equals("lobbyWorld")&&file.isDirectory()){
                CreativeBlockz.instance.getLogger().info("lobbyWorld exists :D I will create an instance now.");
                WorldCreator wc = new WorldCreator("lobbyWorld");
                wc.generator(new LobbyGen(CreativeBlockz.instance));
                wc.environment(World.Environment.NORMAL);
                CreativeBlockz.lobby = wc.createWorld();
                return;
            }
        }

        CreativeBlockz.instance.getLogger().info("lobbyWorld doesn't exist, I will make one now :D");
        WorldCreator wc = new WorldCreator("lobbyWorld");
        wc.generator(new LobbyGen(CreativeBlockz.instance));
        wc.environment(World.Environment.NORMAL);
        CreativeBlockz.lobby = wc.createWorld();
    }
}
