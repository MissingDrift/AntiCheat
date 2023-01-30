package me.missingdrift.anticheat.checks.misc.badpackets;

import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.check.api.CheckInformation;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.Packet;
import me.missingdrift.anticheat.tinyprotocol.packet.in.WrappedInHeldItemSlotPacket;
import me.missingdrift.anticheat.tinyprotocol.packet.out.WrappedOutHeldItemSlot;

@CheckInformation(checkName = "BadPackets", checkType = "D", lagBack = false, punishmentVL = 2)
public class BadPacketsD extends Check {

    private int lastSlot;

    @Override
    public void onPacket(PacketEvent event) {
        switch (event.getType()) {

            case Packet.Client.HELD_ITEM_SLOT: {
                User user = event.getUser();

                WrappedInHeldItemSlotPacket heldItemSlot = new WrappedInHeldItemSlotPacket(event.getPacket(), user.getPlayer());

                int slot = heldItemSlot.getSlot();

                if (slot == lastSlot) {
                 ///   flag(user);
                }

                lastSlot = slot;
                break;
            }

            case Packet.Server.HELD_ITEM: {
                User user = event.getUser();

                WrappedOutHeldItemSlot heldItemSlot = new WrappedOutHeldItemSlot(event.getPacket(), user.getPlayer());

                int slot = heldItemSlot.getSlot();

                if (slot == lastSlot) {
             //       flag(user);
                }

                lastSlot = slot;
                break;
            }
        }
    }
}