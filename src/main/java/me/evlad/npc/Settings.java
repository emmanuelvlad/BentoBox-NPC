package me.evlad.npc;

import java.util.Collection;
import java.util.HashSet;

public class Settings {
    private final NPCAddon addon;
    private final HashSet<String> disabledGameModes;

    public Settings(NPCAddon addon) {
        this.addon = addon;

        this.disabledGameModes = new HashSet<>(this.addon.getConfig().getStringList("disabled-gamemodes"));
    }

    public HashSet<String> getDisabledGameModes() {
        return disabledGameModes;
    }
}
