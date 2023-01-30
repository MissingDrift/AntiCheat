package me.missingdrift.anticheat.checks.misc.pingspoof;

import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.check.api.CheckInformation;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.Packet;
import org.bukkit.Bukkit;

@CheckInformation(checkName = "PingSpoof", checkType = "C", lagBack = false, canPunish = false, enabled = false, description = "Detects Ping Spoofing")
public class PingSpoofC extends Check {

    private double threshold;

    @Override
    public void onPacket(PacketEvent event) {
        switch (event.getType()) {
            case Packet.Client.FLYING:
            case Packet.Client.LOOK:
            case Packet.Client.POSITION_LOOK:
            case Packet.Client.POSITION: {

                User user = event.getUser();

                if (user.shouldCancel()
                        || user.getTick() < 60
                         || user.getPlayer().isDead()) {
                    return;
                }

                int pingK = user.getConnectionProcessor().getPing(),
                        pingT = user.getConnectionProcessor().getTransPing() + 250;


                if (pingK > pingT) {
                    if (threshold++ > 20) {
                        flag(user, "Ping Spoofing "+pingK + " "+pingT);
                    }
                } else {
                    threshold -= Math.min(threshold, 0.25);
                }

                break;
            }
        }
    }
}