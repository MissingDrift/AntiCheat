
package me.missingdrift.anticheat.tinyprotocol.api.packets.channelhandler;

import me.missingdrift.anticheat.tinyprotocol.api.ProtocolVersion;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.Reflections;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.types.WrappedField;
import me.missingdrift.anticheat.tinyprotocol.reflection.Reflection;
import org.bukkit.entity.Player;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class ChannelHandlerAbstract {
    static final WrappedField networkManagerField = Reflections.getNMSClass("PlayerConnection").getFieldByName("networkManager");
    static final WrappedField playerConnectionField = Reflections.getNMSClass("EntityPlayer").getFieldByName("playerConnection");
    // Packets we have to intercept
    static final Class<?> PACKET_SET_PROTOCOL = Reflection.getMinecraftClass("PacketHandshakingInSetProtocol");
    static final Class<?> PACKET_LOGIN_IN_START = Reflection.getMinecraftClass("PacketLoginInStart");
    final Executor addChannelHandlerExecutor;
    final Executor removeChannelHandlerExecutor;
    final String handlerKey;
    final String playerKey;

    ChannelHandlerAbstract() {
        addChannelHandlerExecutor = Executors.newSingleThreadExecutor();
        removeChannelHandlerExecutor = Executors.newSingleThreadExecutor();
        this.handlerKey = "packet_handler";
        this.playerKey = "bac_player_handler";
    }

    public abstract void addChannel(Player player);

    public abstract void removeChannel(Player player);

    public abstract void sendPacket(Player player, Object packet);

    public abstract ProtocolVersion getProtocolVersion(Player player);

}
