package me.evlad.npc.dataobjects;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;

import org.bukkit.Location;
import world.bentobox.bentobox.database.objects.DataObject;
import world.bentobox.bentobox.database.objects.Table;

import javax.annotation.Nullable;

@Table(name = "NPCData")
public class NPCDatas implements DataObject {
    @Expose
    private String uniqueId;

    @Expose
    private Map<String, Location> npcs;

    public NPCDatas() {}

    public NPCDatas(String uniqueId, Map<String, Location> npcs) {
        this.uniqueId = uniqueId;
        this.npcs = npcs;
    }

    public NPCDatas(String uniqueId) {
        this(uniqueId, new HashMap<>());
    }

    @Override
    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public @Nullable Location getNpc(String name) {
        return this.npcs.get(name);
    }

    public void setNpc(String name, Location value) {
        this.npcs.put(name, value);
    }
}
