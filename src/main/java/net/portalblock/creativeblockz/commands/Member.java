package net.portalblock.creativeblockz.commands;

import com.worldcretornica.plotme.PlotMe;
import net.portalblock.creativeblockz.CreativeBlockz;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by portalBlock on 2/23/14.
 */
public class Member implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage(PlotMe.PREFIX+ ChatColor.GOLD+"Teleporting....");
            p.teleport(CreativeBlockz.defaultL);
        }
        return true;
    }
}
