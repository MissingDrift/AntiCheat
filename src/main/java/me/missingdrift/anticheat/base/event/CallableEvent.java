package me.missingdrift.anticheat.base.event;

import me.missingdrift.anticheat.base.user.User;

public interface CallableEvent {
    void onPacket(PacketEvent event);
    void setupTimers(User user);
    void onConnection(User user);
}
