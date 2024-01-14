package net.danygames2014.unitweaks.tweaks.fov;

import net.minecraft.client.option.Option;

public class FovData {
    public static Option fovOption;
    public static float fovValue;

    public static int getRealFov(){
        return Math.round(70.0f + fovValue * 40.0f);
    }
}
