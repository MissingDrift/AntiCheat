package me.missingdrift.anticheat.base.processor.impl.processors;

import lombok.Getter;
import lombok.Setter;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.processor.api.Processor;
import me.missingdrift.anticheat.base.processor.api.ProcessorInformation;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.Packet;
import me.missingdrift.anticheat.tinyprotocol.packet.in.WrappedInEntityActionPacket;
import me.missingdrift.anticheat.util.EventTimer;
import me.missingdrift.anticheat.util.MaterialHelper;

@ProcessorInformation(name = "Elytra")
@Getter @Setter
public class ElytraProcessor extends Processor {

    private boolean usingElytra;
    private EventTimer lastElytraPacket, lastElytraTick, lastElytraToggle;
    private double invalidElytraThreshold, fireworkBoost;

    @Override
    public void onPacket(PacketEvent event) {
        switch (event.getType()) {
            case Packet.Client.ENTITY_ACTION: {
                WrappedInEntityActionPacket wrappedInEntityActionPacket = new WrappedInEntityActionPacket(
                        event.getPacket(), event.getUser().getPlayer());

                if (wrappedInEntityActionPacket.getAction() == WrappedInEntityActionPacket.EnumPlayerAction
                        .START_FALL_FLYING) {
                    if (!this.isUsingElytra() && MaterialHelper.hasElytra(user)) {
                        this.fireworkBoost = 1.995;
                        this.usingElytra = true;
                        this.lastElytraPacket.reset();
                        this.lastElytraToggle.reset();
                    }
                }
                break;
            }

            case Packet.Client.FLYING:
            case Packet.Client.LOOK:
            case Packet.Client.POSITION_LOOK:
            case Packet.Client.POSITION: {

                if (this.isUsingElytra()) {
                    double deltaXZ = user.getMovementProcessor().getDeltaXZ();
                    double deltaY = user.getMovementProcessor().getDeltaY();

                    if (user.getTick() % 5 == 0) {
                        if (deltaY < 3E-2 && deltaXZ > .295) {
                            this.fireworkBoost += 0.082;
                        } else {
                            this.fireworkBoost -= (this.fireworkBoost > .3 ? .050 : 0);
                        }
                    }

                    if (this.lastElytraPacket.passed(5) && user.getMovementProcessor().isOnGround()) {
                        this.usingElytra = false;
                        this.lastElytraToggle.reset();
                    }


                    if (deltaY == 0 || deltaXZ == 0) {
                        if ((this.invalidElytraThreshold += 1.2) > 4) {
                            this.lastElytraToggle.reset();
                            this.usingElytra = false;
                            this.invalidElytraThreshold = 0;
                        }
                    } else {
                        this.invalidElytraThreshold -= (this.invalidElytraThreshold > 0 ? .20 : 0);
                    }

                    this.lastElytraTick.reset();
                }
                break;
            }
        }
    }

    @Override
    public void setupTimers(User user) {
        this.lastElytraPacket = new EventTimer(20, user);
        this.lastElytraTick = new EventTimer(20, user);
        this.lastElytraToggle = new EventTimer(20, user);
    }
}
