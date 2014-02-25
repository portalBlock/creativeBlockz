package net.portalblock.creativeblockz.listeners;

import com.worldcretornica.plotme.Plot;
import com.worldcretornica.plotme.PlotManager;
import com.worldcretornica.plotme.PlotMe;
import net.portalblock.creativeblockz.CreativeBlockz;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by portalBlock on 2/24/14.
 */
public class GeneralListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        Plot plot = PlotManager.getPlotById(e.getPlayer());
        if(plot != null){
            if(!plot.isAllowed(e.getPlayer().getName())){
                e.setCancelled(true);
            }
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPistonExtend(BlockPistonExtendEvent e){
        Block piston = e.getBlock();
        for(Block b : e.getBlocks()){
            Plot p = PlotManager.getMap(b).plots.get(PlotManager.getPlotId(piston.getLocation()));
            if(p == null || !PlotManager.isBlockInPlot(p, b.getLocation())){
                e.setCancelled(true);
                CreativeBlockz.instance.getServer().broadcastMessage(PlotMe.PREFIX+ ChatColor.DARK_RED+"A block on a plot owned by "+ChatColor.GOLD+p.getOwner()+ ChatColor.DARK_RED+" was attempted to be pushed off the plot, this is not allowed!");
                return;
            }
        }
    }
}
