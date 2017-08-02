package com.grizz.generators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
    @Getter @Setter private List<String> lines;



    // TODO: Write all code for Sign on Generator.
}
