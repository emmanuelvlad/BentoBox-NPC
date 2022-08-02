package me.evlad.npc.dataobjects;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;

import org.bukkit.Location;
import world.bentobox.bentobox.database.objects.DataObject;
import world.bentobox.bentobox.database.objects.Table;

import javax.annotation.Nullable;

@Table(name = "NPCData")
public class NPCObject implements DataObject {
    @Expose
    private String uniqueId;

    @Expose
    private Location location;
    @Expose
    private String islandId;
    private boolean changed;
    @Expose
    private String typeId;

    @Override
    public String getUniqueId() {
        return this.uniqueId;
    }

    @Override
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        setChanged();
    }

    public @Nullable Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
        setChanged();
    }

    public void setIslandId(String islandId) {
        this.islandId = islandId;
        setChanged();
    }

    public String getIslandId() {
        return this.islandId;
    }

    public String getTypeId() {
        return this.typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
        setChanged();
    }

    private void setChanged() {
        this.changed = true;
    }
}
