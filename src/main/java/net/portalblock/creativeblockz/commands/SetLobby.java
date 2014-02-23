package net.portalblock.creativeblockz.commands;

import net.portalblock.creativeblockz.CreativeBlockz;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

/**
 * Created by portalBlock on 2/22/14.
 */
public class SetLobby implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        File file = new File(CreativeBlockz.instance.getDataFolder(), "cbConfig.yml");
        FileConfiguration cbConfig = YamlConfiguration.loadConfiguration(file);
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("creativeblockz.setlobby")||p.isOp()){
                Location l = p.getLocation();
                cbConfig.set("lobby.x", l.getX());
                cbConfig.set("lobby.y", l.getY());
                cbConfig.set("lobby.z", l.getZ());
                cbConfig.set("lobby.yaw", l.getYaw());
                cbConfig.set("lobby.pitch", l.getPitch());
                cbConfig.set("lobby.isSet", true);
                try{
                    cbConfig.save(file);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
