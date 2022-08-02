package me.evlad.npc.managers;

import me.evlad.npc.NPCAddon;
import me.evlad.npc.dataobjects.NPCObject;
import me.evlad.npc.utils.Utils;
import org.bukkit.Location;
import org.bukkit.World;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import world.bentobox.bentobox.database.Database;
import world.bentobox.bentobox.database.objects.Island;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class NPCManager {
    private final NPCAddon addon;
    private final Database<NPCObject> NPCDatabase;
    private final Map<String, NPCObject> NPCCache;

    public NPCManager(NPCAddon addon) {
        this.addon = addon;

        this.NPCDatabase = new Database<>(addon, NPCObject.class);
        this.NPCCache = new ConcurrentHashMap<>();

        this.load();
    }

    public @Nullable NPCObject getNPCById(String id) {
        for (Map.Entry<?, ?> entry : this.NPCCache.entrySet()) {
            final NPCObject value = (NPCObject) entry.getValue();

            if (value.getTypeId().equals(id)) {
                return value;
            }
        }

        return null;
    }

    public List<NPCObject> getNPCsOnIsland(String islandId) {
        return this.NPCCache.values().stream().filter(i -> {
            Location loc = i.getLocation();
//            if (loc == null || !loc.getWorld().getName().equals(world.getName()))
            if (loc == null)
                return false;

            return true;
        }).collect(Collectors.toList());
//        return this.NPCCache.entrySet().stream().collect(Collectors.toList());
//        return
    }

    public NPCObject getOrCreateNpc(String id, Island island) {
        NPCObject checkNpc = getNPCById(id);
        if (checkNpc != null) {
            return checkNpc;
        }

        return createNpc(id, island);
    }

    public NPCObject createNpc(String id, Island island) {
        if (getNPCById(id) != null) {
            return null;
        }
        NPCObject npc = new NPCObject();
        npc.setTypeId(id);
        npc.setIslandId(island.getUniqueId());
        npc.setLocation(island.getSpawnPoint(World.Environment.NORMAL));

        NPCCache.put(npc.getUniqueId(), npc);

        return npc;
    }

//    public NPCObject getIslandNPC(String islandId, World world)
//    {
//        return this.getIslandNPC(islandId, Utils.getGameMode(world));
//    }
//
//    @NonNull
//    private NPCObject getIslandNPC(@NonNull String uniqueID, String gameMode) {
//        if (this.NPCCache.containsKey(uniqueID))
//        {
//            return this.NPCCache.get(uniqueID);
//        }
//
//        // The player is not in the cache
//        // Check if the player exists in the database
//
//        if (this.NPCDatabase.objectExists(uniqueID))
//        {
//            // Load player from database
//            NPCObject data = this.NPCDatabase.loadObject(uniqueID);
//            // Store in cache
//            if (data != null)
//            {
//                this.load(data);
//                return data;
//            }
//            this.addon.logError("Could not load NULL likes data object for " + uniqueID + " in " + gameMode);
//        }
//
////        return newPlayer(gameMode, uniqueID);
//    }

    public void load() {
        this.NPCCache.clear();

        this.addon.getLogger().info("Loading npc...");

        this.NPCDatabase.loadObjects().forEach(this::load);
    }
    private void load(NPCObject object) {
        this.NPCCache.put(object.getUniqueId(), object);
    }
}
