package net.danygames2014.unitweaks.mixin.tweaks.stackablechests;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.ChestBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {
    @WrapOperation(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;shouldSuffocate(III)Z"))
    public boolean aBoolean(World world, int x, int y, int z, Operation<Boolean> original) {
        if (UniTweaks.TWEAKS_CONFIG.stackableChests) {
            if (FabricLoader.getInstance().isModLoaded("stationapi")) {
                if (isBlockAboveChestStation(world, x, y, z)) {
                    return false;
                }
            } else {
                if (isBlockAboveChestVanilla(world, x, y, z)) {
                    return false;
                }
            }
        }
        return original.call(world, x, y, z);
    }

    @Unique
    public boolean isBlockAboveChestStation(World world, int x, int y, int z) {
        return world.getBlockState(x, y, z).getBlock() instanceof ChestBlock;
    }

    @Unique
    public boolean isBlockAboveChestVanilla(World world, int x, int y, int z) {
        return world.getBlockId(x, y, z) == Block.CHEST.id;
    }
}
