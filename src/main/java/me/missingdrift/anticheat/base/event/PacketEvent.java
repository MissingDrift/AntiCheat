package me.missingdrift.anticheat.base.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.missingdrift.anticheat.base.user.User;

@Getter @AllArgsConstructor
public class PacketEvent {
    private final User user;
    private final Object packet;
    private final String type;
    private final long timestamp;
}
