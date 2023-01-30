package me.missingdrift.anticheat.checks.misc.inventory;

import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.check.api.CheckInformation;
import me.missingdrift.anticheat.base.event.PacketEvent;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.tinyprotocol.api.Packet;
import me.missingdrift.anticheat.tinyprotocol.packet.in.WrappedInWindowClickPacket;

@CheckInformation(checkName = "Inventory", checkType = "B", lagBack = false, punishmentVL = 5)
public class InventoryB extends Check {

    @Override
    public void onPacket(PacketEvent event) {
        switch (event.getType()) {
            case Packet.Client.WINDOW_CLICK: {
                User user = event.getUser();

                WrappedInWindowClickPacket clickPacket = new WrappedInWindowClickPacket(event.getPacket(), user.getPlayer());
                if (clickPacket.getId() == 0) {
                    if (!user.getMovementProcessor().isInInventory()) {
                        flag(user, "Clicking in inventory while its not open");
                    }
                }
                break;
            }
        }
    }
}
