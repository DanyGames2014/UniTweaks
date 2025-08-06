package net.danygames2014.unitweaks.util;

import net.fabricmc.loader.api.FabricLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class ModConflicts {
    public static HashMap<String, ModConflictEntry> conflicts = new HashMap<>();
    public static ArrayList<ModConflictEntry> detectedConflicts = new ArrayList<>();

    static {
        conflicts.put("annoyancefix", new ModConflictEntry("AnnoyanceFix", "Entirely Replaced"));
        conflicts.put("mostlymodernrecipes", new ModConflictEntry("Mostly Modern Recipes", "Entirely Replaced"));
        conflicts.put("betatweaks", new ModConflictEntry("Beta Tweaks", "Entirely Replaced"));
        conflicts.put("finalbeta", new ModConflictEntry("Final Beta", "Entirely Replaced"));
        conflicts.put("gameplayessentials", new ModConflictEntry("Gameplay Essentials", "Entirely Replaced"));
        conflicts.put("clientsideessentials", new ModConflictEntry("Clientside Essentials", "Entirely Replaced"));
        conflicts.put("photomode", new ModConflictEntry("Photo Mode", "Entirely Replaced"));
        conflicts.put("thirdpersonfix", new ModConflictEntry("Third Person Fix", "Entirely Replaced"));
        
        conflicts.put("extremeview", new ModConflictEntry("Extreme View", "Conflicting Feature"));
    }

    public static void detect() {
        detectedConflicts.clear();
        for (var c : ModConflicts.conflicts.entrySet()) {
            if (FabricLoader.getInstance().isModLoaded(c.getKey())) {
                ModConflicts.detectedConflicts.add(c.getValue());
            }
        }
    }

    public record ModConflictEntry(String modName, String comment) {
    }
}

