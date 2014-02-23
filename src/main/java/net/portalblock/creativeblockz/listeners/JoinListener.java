package net.portalblock.creativeblockz.listeners;

import com.worldcretornica.plotme.PlotMe;
import net.portalblock.creativeblockz.CreativeBlockz;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

/**
 * Created by portalBlock on 2/23/14.
 */
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent e){
        e.setJoinMessage("");
        File cfg = new File("/plugins/creativeBlockz/cbConfig.yml");
        FileConfiguration cbConfig = YamlConfiguration.loadConfiguration(cfg);
        if(cbConfig.getBoolean("lobby.isSet")){
            if(!e.getPlayer().hasPlayedBefore()){
                double x, y, z, yaw, pitch;
                String name;
                name = cbConfig.getString("lobby.world");
                World w = CreativeBlockz.instance.getServer().getWorld(name);
                x = cbConfig.getDouble("lobby,x");
                y = cbConfig.getDouble("lobby.y");
                z = cbConfig.getDouble("lobby.z");
                yaw = cbConfig.getDouble("lobby.yaw");
                pitch = cbConfig.getDouble("lobby.pitch");
                final Location loc = new Location(w, x, y, z, (float) yaw, (float) pitch);
                    CreativeBlockz.instance.getServer().getScheduler().scheduleSyncDelayedTask(CreativeBlockz.instance, new Runnable() {
                        @Override
                        public void run() {
                            CreativeBlockz.instance.getServer().broadcastMessage(PlotMe.PREFIX+ ChatColor.GOLD+"Welcome "+ChatColor.AQUA+e.getPlayer().getName()+ChatColor.GOLD+" to craftingBlockz creative!");
                            e.getPlayer().teleport(loc);
                        }
                    }, 1L);
            }else{
                CreativeBlockz.instance.getServer().getScheduler().scheduleSyncDelayedTask(CreativeBlockz.instance, new Runnable() {
                    @Override
                    public void run() {
                        e.getPlayer().sendMessage(PlotMe.PREFIX+ ChatColor.GOLD+"Welcome back to creative!");
                    }
                }, 1L);
            }
        }
    }
}
