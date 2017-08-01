package com.grizz.generators;

/**
 * Created by Gbtank.
 */
public class GeneratorManager {

    // Singleton Structure

    private static GeneratorManager gm = new GeneratorManager();

    protected GeneratorManager() {}

    public static GeneratorManager get() {
        return gm;
    }

}
