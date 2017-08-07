package com.grizz.generators;

import com.grizz.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import java.util.List;

/**
 * Created by Gbtank.
 */
@AllArgsConstructor
public class GeneratorSign {

    @Getter private Location location;
    @Getter private Generator generator;

    @Getter private Sign sign;
    @Getter private List<String> lines;

    public void setLines(List<String> lineList) {
        for(int index = 0; index < lineList.size(); index++) {
            String line = lineList.get(index);
            line = line.replace("{GEN_NAME}", generator.getSettings().getName()
            .replace("{GEN_LEVEL}", generator.getData().getLevel() + "")
            .replace("{GEN_DROP}", StringUtils.capitalise(generator.getSettings().getItem().getType().name()))
            .replace("{GEN_INTERVAL}", ((int) generator.getData().getInterval() / 20L) + "")
            .replace("{NEXT_LEVEL}", generator.getData().getLevel() + 1 + "")
            .replace("{NEXT_INTERVAL}", ((int) generator.getSettings().getUpgradeMap().get(generator.getData().getLevel() + 1).getInterval()) + ""));

            lines.set(index, ChatColor.translateAlternateColorCodes('&',line));
        }
    }

    // TODO: Write all code for Sign on Generator.
}
