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
public class SetRank implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        File file = new File(CreativeBlockz.instance.getDataFolder(), "cbConfig.yml");
        FileConfiguration cbConfig = YamlConfiguration.loadConfiguration(file);
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("creativeblockz.setrank")||p.isOp()){
                Location l = p.getLocation();
                CreativeBlockz.rankL = l;
                cbConfig.set("rank.x", l.getX());
                cbConfig.set("rank.y", l.getY());
                cbConfig.set("rank.z", l.getZ());
                cbConfig.set("rank.yaw", l.getYaw());
                cbConfig.set("rank.pitch", l.getPitch());
                cbConfig.set("rank.isSet", true);
                try{
                    cbConfig.save(file);
                }catch (IOException e){
                    e.printStackTrace();
                }
                p.sendMessage(PlotMe.PREFIX+ ChatColor.GOLD+"Rank world spawn set!");
            }
        }
        return true;
    }
}
