package me.missingdrift.anticheat.tinyprotocol.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.types.WrappedField;

@Getter
@AllArgsConstructor
public class PacketField<T> {
    private final WrappedField field;
    private final T value;
}
