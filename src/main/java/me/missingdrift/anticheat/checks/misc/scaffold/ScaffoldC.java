package me.missingdrift.anticheat.checks.misc.scaffold;

import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.check.api.CheckInformation;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.Packet;

@CheckInformation(checkName = "Scaffold", checkType = "C", lagBack = false, punishmentVL = 10, canPunish = false)
public class ScaffoldC extends Check {

    private long lastFlying;
    private double threshold;

    @Override
    public void onPacket(PacketEvent event) {
        switch (event.getType()) {
            case Packet.Client.BLOCK_PLACE: {
                User user = event.getUser();

                if ((System.currentTimeMillis() - lastFlying) < 5L) {
                    if (++threshold > 8) {
                        flag(user, "Block Placement Packet Sent Late");
                    }
                } else {
                    threshold -= Math.min(threshold, 0.75);
                }

                break;
            }

            case Packet.Client.FLYING:
            case Packet.Client.LOOK:
            case Packet.Client.POSITION_LOOK:
            case Packet.Client.POSITION: {

                lastFlying = System.currentTimeMillis();

                break;
            }
        }
    }
}