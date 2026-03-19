package net.danygames2014.unitweaks.util;

import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class CompatHelper {
    // FOV
    public static ArrayList<BiFunction<Float, Float, Float>> fovMethods = new ArrayList<>();

    public static void registerFovCompat(BiFunction<Float, Float, Float> fovFunction) {
        fovMethods.add(fovFunction);
    }
    
    // Shift Placing
    public static ArrayList<Block> shiftPlacingBlacklist = new ArrayList<>();
    
    public static void addToShiftPlacingBlacklist(Block block) {
        shiftPlacingBlacklist.add(block);
    }
}
