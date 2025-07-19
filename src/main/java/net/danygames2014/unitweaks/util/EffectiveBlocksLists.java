package net.danygames2014.unitweaks.util;

import net.minecraft.block.Block;

import java.util.ArrayList;

public class EffectiveBlocksLists {
    public static ArrayList<Block> pickaxeBlocks = new ArrayList<>();
    
    public static ArrayList<Block> axeBlocks = new ArrayList<>();

    public static ArrayList<Block> shovelBlocks = new ArrayList<>();
    
    static {
        pickaxeBlocks.add(Block.DISPENSER);
        pickaxeBlocks.add(Block.BRICKS);
        pickaxeBlocks.add(Block.POWERED_RAIL);
        pickaxeBlocks.add(Block.DETECTOR_RAIL);
        pickaxeBlocks.add(Block.RAIL);
        pickaxeBlocks.add(Block.FURNACE);
        pickaxeBlocks.add(Block.LIT_FURNACE);
        pickaxeBlocks.add(Block.COBBLESTONE_STAIRS);
        pickaxeBlocks.add(Block.STONE_PRESSURE_PLATE);
        pickaxeBlocks.add(Block.IRON_DOOR);
        pickaxeBlocks.add(Block.REDSTONE_ORE);
        pickaxeBlocks.add(Block.LIT_REDSTONE_ORE);
        pickaxeBlocks.add(Block.BUTTON);
        pickaxeBlocks.add(Block.SPAWNER);

        axeBlocks.add(Block.CRAFTING_TABLE);
        axeBlocks.add(Block.WOODEN_STAIRS);
        axeBlocks.add(Block.JUKEBOX);
        axeBlocks.add(Block.SIGN);
        axeBlocks.add(Block.WALL_SIGN);
        axeBlocks.add(Block.WOODEN_PRESSURE_PLATE);
        axeBlocks.add(Block.LADDER);
        axeBlocks.add(Block.DOOR);
        axeBlocks.add(Block.TRAPDOOR);
        axeBlocks.add(Block.PUMPKIN);
        axeBlocks.add(Block.JACK_O_LANTERN);
        axeBlocks.add(Block.FENCE);
        axeBlocks.add(Block.NOTE_BLOCK);
        
        shovelBlocks.add(Block.SOUL_SAND);
    }

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
