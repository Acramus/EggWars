package com.grizz.generators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Created by Gbtank.
 */
@AllArgsConstructor
public class GeneratorSign {

    @Getter @Setter private String serverName;
    @NonNull @Getter @Setter private String worldName;

    // TODO: Write all code for Sign on Generator.
}
