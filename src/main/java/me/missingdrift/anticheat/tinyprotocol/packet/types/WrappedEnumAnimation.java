package me.missingdrift.anticheat.tinyprotocol.packet.types;

import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.Reflections;
import me.missingdrift.anticheat.tinyprotocol.api.packets.reflections.types.WrappedClass;

public enum WrappedEnumAnimation {
    NONE,
    EAT,
    DRINK,
    BLOCK,
    BOW,
    SPEAR,
    CROSSBOW;

    private static final WrappedClass enumAnimation;

    public static WrappedEnumAnimation fromNMS(Object vanillaObject) {
        Enum vanilla = (Enum) vanillaObject;

        return valueOf(vanilla.name());
    }

    public Enum toVanilla() {
        return enumAnimation.getEnum(name());
    }

    static {
        enumAnimation = Reflections.getNMSClass("EnumAnimation");
    }
}
