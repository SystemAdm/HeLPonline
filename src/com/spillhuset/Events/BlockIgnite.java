package com.spillhuset.Events;

import com.spillhuset.OddJob;
import com.spillhuset.Utils.Enum.Zone;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

import java.util.UUID;

public class BlockIgnite implements Listener {
    /**
     * Cancel Ignition
     *
     * @param event
     */
    @EventHandler
    public void blockIgnite(BlockIgniteEvent event) {
        if (event.getBlock().getType() == Material.TNT) {
            // CHECK GUILD
            UUID guild = OddJob.getInstance().getGuildManager().getGuildUUIDByChunk(event.getBlock().getLocation().getChunk());
            if (guild != null && !guild.equals(OddJob.getInstance().getGuildManager().getGuildUUIDByZone(Zone.WILD))) {
                event.setCancelled(true);
            }
        }
    }
}
