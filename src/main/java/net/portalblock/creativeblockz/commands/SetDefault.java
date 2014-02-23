package net.portalblock.creativeblockz.commands;

import com.worldcretornica.plotme.PlotMe;
import net.portalblock.creativeblockz.CreativeBlockz;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by portalBlock on 2/22/14.
 */
public class SetDefault implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        File file = new File(CreativeBlockz.instance.getDataFolder(), "cbConfig.yml");
        FileConfiguration cbConfig = YamlConfiguration.loadConfiguration(file);
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("creativeblockz.setdefault")||p.isOp()){
                Location l = p.getLocation();
                CreativeBlockz.defaultL = l;
                cbConfig.set("default.x", l.getX());
                cbConfig.set("default.y", l.getY());
                cbConfig.set("default.z", l.getZ());
                cbConfig.set("default.yaw", l.getYaw());
                cbConfig.set("default.pitch", l.getPitch());
                cbConfig.set("default.isSet", true);
                try{
                    cbConfig.save(file);
                }catch (IOException e){
                    e.printStackTrace();
                }
                p.sendMessage(PlotMe.PREFIX+ ChatColor.GOLD+"Member world spawn set!");
            }
        }
        return true;
    }
}
