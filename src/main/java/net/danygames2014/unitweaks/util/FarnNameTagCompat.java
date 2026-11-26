package net.danygames2014.unitweaks.util;

import farn.nametag.NameTagMain;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import static farn.nametag.NameTagMain.CUSTOM_NAME_NBT_KEY;

public class FarnNameTagCompat {
    public static void checkForCustomNametagAndAddToResult(ItemStack stack1, ItemStack stack2, ItemStack result)
    {
        if (NameTagMain.itemHasCustomName(stack1)) {
            NbtCompound newNbt = result.getStationNbt();
            newNbt.putString(CUSTOM_NAME_NBT_KEY, stack1.getStationNbt().getString(CUSTOM_NAME_NBT_KEY));
        } else if (NameTagMain.itemHasCustomName(stack2)) {
            NbtCompound newNbt = result.getStationNbt();
            newNbt.putString(CUSTOM_NAME_NBT_KEY, stack2.getStationNbt().getString(CUSTOM_NAME_NBT_KEY));
        }
    }
}
