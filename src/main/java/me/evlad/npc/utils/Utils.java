package me.evlad.npc.utils;

import org.bukkit.World;
import world.bentobox.bentobox.BentoBox;

public class Utils {
    public static String getGameMode(World world)
    {
        return BentoBox.getInstance().getIWM().getAddon(world).
                map(gameModeAddon -> gameModeAddon.getDescription().getName()).
                orElse(null);
    }

}
