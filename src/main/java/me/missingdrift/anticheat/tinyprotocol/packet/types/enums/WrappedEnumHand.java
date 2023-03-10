package me.missingdrift.anticheat.tinyprotocol.packet.types.enums;

import me.missingdrift.anticheat.tinyprotocol.api.ProtocolVersion;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.Reflections;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.types.WrappedClass;

public enum WrappedEnumHand {
    MAIN_HAND,
    OFF_HAND;

    public static WrappedClass enumHandClass;

    public static WrappedEnumHand getFromVanilla(Object object) {
        if(enumHandClass == null) return WrappedEnumHand.MAIN_HAND;

        if(object instanceof Enum)
            return valueOf(object.toString());

        return WrappedEnumHand.MAIN_HAND;
    }

    public <T> T toEnumHand() {
        return (T) enumHandClass.getEnum(name());
    }

    static {
        if(ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_9)) {
            enumHandClass = Reflections.getNMSClass("EnumHand");
        }
    }
}