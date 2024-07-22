package net.danygames2014.unitweaks.util;

public class EffectiveBlocksLists {
    //This will be moved into config when config stops crashing rendering this list
    //@ConfigName("Pickaxe Effective Blocks")
    public static String[] pickaxeEffectiveAgainst = new String[]{
            "minecraft:dispenser",
            "minecraft:bricks",
            "minecraft:powered_rail",
            "minecraft:detector_rail",
            "minecraft:rail",
            "minecraft:furnace",
            "minecraft:furnace_lit",
            "minecraft:cobblestone_stairs",
            "minecraft:oak_pressure_plate",
            "minecraft:iron_door",
            "minecraft:redstone_ore",
            "minecraft:redstone_ore_lit",
            "minecraft:stone_button",
            "minecraft:spawner"
    };

    public static String[] axeEffectiveAgainst = new String[]{
            "minecraft:crafting_table",
            "minecraft:oak_stairs",
            "minecraft:jukebox",
            "minecraft:oak_sign",
            "minecraft:oak_wall_sign",
            "minecraft:stone_pressure_plate",
            "minecraft:ladder",
            "minecraft:oak_door",
            "minecraft:oak_trapdoor",
            "minecraft:carved_pumpkin",
            "minecraft:jack_o_lantern",
            "minecraft:oak_fence",
            "minecraft:note_block"
    };

    public static String[] shovelEffectiveAgainst = new String[]{
            "minecraft:soul_sand"
    };
}
