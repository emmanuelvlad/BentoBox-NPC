package me.evlad.npc.listeners;

import me.evlad.npc.dataobjects.NPCDatas;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import world.bentobox.bentobox.api.events.island.IslandEnterEvent;
import world.bentobox.bentobox.database.objects.Island;
import me.evlad.npc.NPCAddon;

public class NPCListener implements Listener {
    private final NPCAddon addon;

    public NPCListener(NPCAddon addon) {
        this.addon = addon;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onIslandEnter(IslandEnterEvent e) {
        addon.log("entered");
        NPCDatas islandNPCs = addon.getNPCs(e.getIsland().getUniqueId());
        Location npcLoc = islandNPCs.getNpc("main");
        Location spawnPoint = e.getIsland().getSpawnPoint(World.Environment.NORMAL);
        NPC npc;
        if (npcLoc == null) {
            npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "main");
            islandNPCs.setNpc("main", spawnPoint);
        } else {
//            npc = CitizensAPI.getNPCRegistry().get
        }
//        npc.getEntity().setGravity(false);
//        CitizensAPI.getNPCRegistry().get

//        npc.spawn(spawnPoint);
        // Stop LimitsJoinPermCheck else reset limits upgrades when player join
        //e.setCancelled(true);
    }

}
