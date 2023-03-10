
package me.missingdrift.anticheat.tinyprotocol.api.packets.reflections;

import lombok.Getter;
import me.missingdrift.anticheat.tinyprotocol.api.ProtocolVersion;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.types.WrappedClass;
import org.bukkit.Bukkit;

@Getter
public class Reflections {
    private static final String craftBukkitString;
    private static final String netMinecraftServerString;

    static {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        craftBukkitString = "org.bukkit.craftbukkit." + version + ".";
        netMinecraftServerString = "net.minecraft.server." + version + ".";
    }

    public static boolean classExists(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static WrappedClass getUtilClass(String name) {
        return getClass((ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)
                ? "net.minecraft.util." : "") + name);
    }

    public static WrappedClass getCBClass(String name) {
        return getClass(craftBukkitString + name);
    }

    public static WrappedClass getNMSClass(String name) {
        return getClass(netMinecraftServerString + name);
    }

    public static WrappedClass getClass(String name) {
        try {
            return new WrappedClass(Class.forName(name));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static WrappedClass getClass(Class clazz) {
        return new WrappedClass(clazz);
    }
}
