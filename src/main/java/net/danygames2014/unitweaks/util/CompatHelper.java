package net.danygames2014.unitweaks.util;

import java.util.ArrayList;
import java.util.function.BiFunction;

public class CompatHelper {
    public static ArrayList<BiFunction<Float, Float, Float>> fovMethods = new ArrayList<>();

    public static void registerFovCompat(BiFunction<Float, Float, Float> fovFunction) {
        fovMethods.add(fovFunction);
    }
}
