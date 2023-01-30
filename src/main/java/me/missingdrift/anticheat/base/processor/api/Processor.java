package me.missingdrift.anticheat.base.processor.api;

import lombok.Getter;
import me.missingdrift.anticheat.base.event.CallableEvent;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.user.User;

@Getter
public class Processor implements CallableEvent, ProcessorInterface {
    public User user;

    public void setup(User user) {
        this.user = user;
        this.setupTimers(user);
    }

    @Override
    public void onPacket(PacketEvent event) {
        //
    }

    @Override
    public void setupTimers(User user) {
        //
    }

    @Override
    public void onConnection(User user) {
        //
    }
}
