package me.missingdrift.anticheat.checks.combat.killaura;

import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.check.api.CheckInformation;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.Packet;
import me.missingdrift.anticheat.tinyprotocol.packet.in.WrappedInUseEntityPacket;

@CheckInformation(checkName = "Killaura", checkType = "E", lagBack = false, description = "Check if player attacks while dead", punishmentVL = 5)
public class KillauraE extends Check {

    private double threshold;

    @Override
    public void onPacket(PacketEvent event) {
        User user = event.getUser();

        switch (event.getType()) {
            case Packet.Client.USE_ENTITY: {
                WrappedInUseEntityPacket attack = new WrappedInUseEntityPacket(event.getPacket(), user.getPlayer());

                if (attack.getAction() == WrappedInUseEntityPacket.EnumEntityUseAction.ATTACK) {

                    if (user.shouldCancel()
                            || user.getConnectionProcessor().isLagging()
                            || user.getTick() < 60) {

                        threshold = 0;
                        return;
                    }

                    if (user.getPlayer().isDead()) {
                        if (threshold++ > 4) {
                            flag(user, "Attacked while dead?");
                        }
                    } else {
                        threshold = 0;
                    }

                }
                break;
            }
        }
    }
}