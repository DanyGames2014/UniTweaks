package net.danygames2014.unitweaks.util.compat;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class CompatEntry implements ModInitializer {
    public static boolean sprint = false;
    @Override
    public void onInitialize() {
        sprint = FabricLoader.getInstance().isModLoaded("babric_sprint");
    }
}
