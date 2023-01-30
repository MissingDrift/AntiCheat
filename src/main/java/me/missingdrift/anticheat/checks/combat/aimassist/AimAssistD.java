package me.missingdrift.anticheat.checks.combat.aimassist;

import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.check.api.CheckInformation;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.Packet;
import me.missingdrift.anticheat.tinyprotocol.packet.in.WrappedInUseEntityPacket;
import me.missingdrift.anticheat.util.MathUtil;
import org.bukkit.Bukkit;

@CheckInformation(checkName = "AimAssist", checkType = "D", lagBack = false, punishmentVL = 25, canPunish = false)
public class AimAssistD extends Check {

    private double threshold;

    private Float lastPitchDifference;
    private Float lastYawDifference;

    /**
     * Credits to Sim0n as this is his detection.
     */

    @Override
    public void onPacket(PacketEvent event) {
        User user = event.getUser();

        if (event.getType().equalsIgnoreCase(Packet.Client.USE_ENTITY)) {
            WrappedInUseEntityPacket useEntityPacket = new WrappedInUseEntityPacket(event.getPacket(), user.getPlayer());

            if (useEntityPacket.getAction() == WrappedInUseEntityPacket.EnumEntityUseAction.ATTACK) {

                float pitchDifference = Math.abs(user.getCurrentLocation().getPitch()
                        - user.getLastLocation().getPitch());

                float yawDifference = Math.abs(user.getCurrentLocation().getYaw()
                        - user.getLastLocation().getYaw());

                if (lastYawDifference != null && lastPitchDifference != null) {

                    float yawAccel = Math.abs(pitchDifference - lastPitchDifference);
                    float pitchAccel = Math.abs(yawDifference - lastYawDifference);

                    if (yawDifference > 3.0F && pitchDifference <= 10.0F && yawAccel > 2F && pitchAccel > 2F
                            && pitchDifference < yawDifference) {

                        double pitchGCD = MathUtil.gcd(pitchDifference, lastPitchDifference);

                        if (pitchGCD < 0.009) {
                            if (threshold++ > 5) {
                                flag(user, "Not following proper GCD");
                            }
                        } else {
                            threshold -= Math.min(threshold, 0.75f);
                        }
                    }
                }

                lastYawDifference = yawDifference;
                lastPitchDifference = pitchDifference;
            }
        }
    }
}
