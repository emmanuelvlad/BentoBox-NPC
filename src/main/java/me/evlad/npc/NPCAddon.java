package me.evlad.npc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import me.evlad.npc.dataobjects.NPCDatas;
import me.evlad.npc.listeners.NPCListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.jdt.annotation.NonNull;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.database.Database;
import me.evlad.npc.Settings;

public final class NPCAddon extends Addon {
    private Settings settings;
    private boolean hooked;
    private Database database;

    @Override
    public void onLoad() {
        super.onLoad();
        this.saveDefaultConfig();
        this.settings = new Settings(this);
    }
	public void test() {
        
    }
    @Override
    public void onEnable() {
        if (this.getState().equals(State.DISABLED)) {
            this.logWarning("Upgrades Addon is not available or disabled!");
            return;
        }

        List<String> hookedGameModes = new ArrayList<>();

        getPlugin().getAddonsManager().getGameModeAddons().stream()
                .filter(g -> !settings.getDisabledGameModes().contains(g.getDescription().getName()))
                .forEach(g -> {
                    if (g.getPlayerCommand().isPresent()) {

//                        new PlayerUpgradeCommand(this, g.getPlayerCommand().get());
//
//                        UpgradesAddon.UPGRADES_RANK_RIGHT.addGameModeAddon(g);

                        this.hooked = true;
                        hookedGameModes.add(g.getDescription().getName());
                    }
                });

        if (this.hooked) {
            this.database = new Database<>(this, NPCDatas.class);
            this.registerListener(new NPCListener(this));
            this.log("NPC addon enabled");
        } else {
            this.logError("NPC addon could not hook into any GameMode and therefore will not do anything");
            this.setState(State.DISABLED);
        }
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onReload() {
        super.onReload();

        if (this.hooked)
            this.settings = new Settings(this);
        this.log("Island npc addon reloaded");
    }

    /**
     * @return the settings
     */
    public Settings getSettings() {
        return settings;
    }

    public NPCDatas getNPCs(@NonNull String targetIsland) {
//        NPCDatas npcs = this.upgradesCache.get(targetIsland);
//        if (upgradesData != null)
//            return upgradesData;
        NPCDatas data = this.database.objectExists(targetIsland) ?
                (NPCDatas) Optional.ofNullable(this.database.loadObject(targetIsland)).orElse(new NPCDatas(targetIsland)) :
                new NPCDatas(targetIsland);
//        this.upgradesCache.put(targetIsland, data);
        return data;
    }
}
