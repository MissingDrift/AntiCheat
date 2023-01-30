package me.missingdrift.anticheat.checks.misc.badpackets;

import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.check.api.CheckInformation;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.Packet;

@CheckInformation(checkName = "BadPackets", lagBack = false, punishmentVL = 1)
public class BadPacketsA extends Check {

    @Override
    public void onPacket(PacketEvent event) {
        switch (event.getType()) {
            case Packet.Client.FLYING:
            case Packet.Client.LOOK:
            case Packet.Client.POSITION_LOOK:
            case Packet.Client.POSITION: {
                User user = event.getUser();
                double pitch = Math.abs(user.getCurrentLocation().getPitch());

                double maxPitch = user.getBlockData().climbable ? 91.11F : 90.0F;

                if (pitch > maxPitch) {
                    flag(user,
                            "pitch: " + pitch
                    );
                }

                break;
            }
        }
    }
}
