package me.missingdrift.anticheat.tinyprotocol.packet.in;

import lombok.Getter;
import me.missingdrift.anticheat.tinyprotocol.api.NMSObject;
import me.missingdrift.anticheat.tinyprotocol.api.ProtocolVersion;
import me.missingdrift.anticheat.tinyprotocol.reflection.FieldAccessor;
import org.bukkit.entity.Player;

@Getter
public class WrappedInCloseWindowPacket extends NMSObject {
    private static final String packet = Client.CLOSE_WINDOW;

    // Fields
    private static final FieldAccessor<Integer> fieldId = fetchField(packet, int.class, 0);

    // Decoded data
    private int id;

    public WrappedInCloseWindowPacket(Object packet, Player player) {
        super(packet, player);
    }

    @Override
    public void updateObject() {

    }

    @Override
    public void process(Player player, ProtocolVersion version) {
        id = fetch(fieldId);
    }
}
