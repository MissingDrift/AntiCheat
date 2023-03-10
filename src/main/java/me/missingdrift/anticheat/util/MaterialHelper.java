package me.missingdrift.anticheat.util;

import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.ProtocolVersion;
import org.bukkit.block.Block;

import java.util.Locale;

public class MaterialHelper {
    public static boolean hasElytra(User user) {
        return (ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8_9)
                && user.getPlayer().getInventory().getChestplate().getType().name()
                .equalsIgnoreCase("ELYTRA"));
    }

    public static boolean isShulker(Block block) {
        return (ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8_9)
                && block.getType().name().toLowerCase(Locale.ROOT).contains("shulker_box"));
    }
}
