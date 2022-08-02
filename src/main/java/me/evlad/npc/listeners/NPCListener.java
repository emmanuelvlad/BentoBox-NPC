package me.evlad.npc.listeners;

import me.evlad.npc.dataobjects.NPCObject;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import world.bentobox.bentobox.api.events.island.IslandEnterEvent;
import me.evlad.npc.NPCAddon;

import java.util.List;

public class NPCListener implements Listener {
    private final NPCAddon addon;

    public NPCListener(NPCAddon addon) {
        this.addon = addon;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onIslandEnter(IslandEnterEvent e) {
        addon.log("entered");
        List<NPCObject> islandNPCs = addon.getNPCs(e.getIsland().getUniqueId());
        NPCObject npc = addon.getManager().getOrCreateNpc("main", e.getIsland());

        Location spawnPoint = e.getIsland().getSpawnPoint(World.Environment.NORMAL);
        npc.setLocation(spawnPoint);
//        if (npc == null) {
//            addon.log("null");
//            npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "main");
//            addon.getManager().createNpc("main", e.getIsland());
//        } else {
//            npc = CitizensAPI.getNPCRegistry().get
//        }
//        npc.getEntity().setGravity(false);
//        CitizensAPI.getNPCRegistry().get

//        npc.spawn(spawnPoint);
        // Stop LimitsJoinPermCheck else reset limits upgrades when player join
        //e.setCancelled(true);
    }

}
