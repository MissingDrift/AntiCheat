package me.missingdrift.anticheat.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConfigValues {
    private boolean lagBack, punish, announce;
    private String punishCommand, prefix, announceMessage;
}
