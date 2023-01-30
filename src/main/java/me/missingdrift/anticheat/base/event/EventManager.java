package me.missingdrift.anticheat.base.event;

import lombok.AllArgsConstructor;
import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.user.User;

@AllArgsConstructor
public class EventManager {
    private final User user;

    public void processProcessors(PacketEvent packetEvent) {
        this.user.getProcessorManager().getProcessors().forEach(processor ->
                processor.onPacket(packetEvent));
    }

    public void processChecks(PacketEvent packetEvent) {
        this.user.getCheckManager().getCheckList().stream().filter(Check::isEnabled).forEach(check ->
                check.onPacket(packetEvent));
    }
}
