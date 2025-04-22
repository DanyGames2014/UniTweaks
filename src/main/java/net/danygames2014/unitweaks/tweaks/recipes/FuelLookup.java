package net.danygames2014.unitweaks.tweaks.recipes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.HashMap;

public class FuelLookup {
    public record FuelLookupEntry(int meta, int fuelTime) {
        public FuelLookupEntry(int fuelTime) {
            this(-1, fuelTime);
        }
    }

    public static HashMap<Item, FuelLookupEntry> lookup = new HashMap<>() {{
        // 1200t = 60s
        put(Item.BOAT, new FuelLookupEntry(1200));
        
        // 300t = 15s
        put(Item.BOW, new FuelLookupEntry(300));
        put(Item.FISHING_ROD, new FuelLookupEntry(300));
        put(Item.ITEMS[Block.LADDER.id], new FuelLookupEntry(300));
        
        // 200t = 10s
        put(Item.WOODEN_AXE, new FuelLookupEntry(200));
        put(Item.WOODEN_HOE, new FuelLookupEntry(200));
        put(Item.WOODEN_PICKAXE, new FuelLookupEntry(200));
        put(Item.WOODEN_SHOVEL, new FuelLookupEntry(200));
        put(Item.WOODEN_SWORD, new FuelLookupEntry(200));
        put(Item.SIGN, new FuelLookupEntry(200));
        put(Item.WOODEN_DOOR, new FuelLookupEntry(200));
        
        // 150t = 7.5s
        put(Item.ITEMS[Block.SLAB.id], new FuelLookupEntry(150));
        
        // 100t = 5s
        put(Item.BOWL, new FuelLookupEntry(100));
    }};
}
