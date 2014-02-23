package net.portalblock.creativeblockz.commands;

import com.worldcretornica.plotme.PlotMe;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

/**
 * Created by portalBlock on 2/22/14.
 */
public class CBCommand implements CommandExecutor {
    private HashMap<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

    public CBCommand(){
        commands.put("setlobby", new SetLobby());
        commands.put("setrank", new SetRank());
        commands.put("setdefault", new SetDefault());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length < 1){
            printHelp(sender);
            return true;
        }
        CommandExecutor ce = commands.get(args[0]);
        if(ce == null){
            sender.sendMessage(PlotMe.PREFIX+ChatColor.RED+"Thats not a valid command.");
            return true;
        }
        ce.onCommand(sender, cmd, label, args);
        return true;
    }

    public void printHelp(CommandSender sender){
        sender.sendMessage(PlotMe.PREFIX+ ChatColor.GOLD+"/cb setlobby -- Sets the first spawn point for a new player.");
        sender.sendMessage(PlotMe.PREFIX+ ChatColor.GOLD+"/cb setrank -- Sets the spawn point for the rank world.");
        sender.sendMessage(PlotMe.PREFIX+ ChatColor.GOLD+"/cb setdefault -- Sets the member worlds spawn point.");
    }
}
