package me.missingdrift.anticheat.base.check.impl;

import lombok.Getter;
import me.missingdrift.anticheat.Anticheat;
import me.missingdrift.anticheat.base.check.api.Check;
import me.missingdrift.anticheat.base.user.User;
import me.missingdrift.anticheat.checks.combat.aimassist.*;
import me.missingdrift.anticheat.checks.combat.autoclicker.*;
import me.missingdrift.anticheat.checks.combat.killaura.*;
import me.missingdrift.anticheat.checks.misc.badpackets.*;
import me.missingdrift.anticheat.checks.combat.velocity.*;
import me.missingdrift.anticheat.checks.misc.pingspoof.*;
import me.missingdrift.anticheat.checks.misc.scaffold.*;
import me.missingdrift.anticheat.checks.misc.timer.*;
import me.missingdrift.anticheat.checks.movement.flight.*;
import me.missingdrift.anticheat.checks.movement.speed.*;
import me.missingdrift.anticheat.checks.movement.step.*;
import me.missingdrift.anticheat.util.file.ChecksFile;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Getter
public class CheckManager {
    private final List<Check> checkList = new LinkedList<>();

    public void setupChecks(User user) {
        this.checkList.addAll(Anticheat.getInstance().getCheckManager().cloneChecks());
        this.checkList.forEach(check -> check.setupTimers(user));
    }
}