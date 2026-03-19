package net.danygames2014.unitweaks.mixin.tweaks.shiftplacing;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.CompatHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.InteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(InteractionManager.class)
public class InteractionManagerMixin {
    @Shadow
    @Final
    protected Minecraft minecraft;

    @WrapOperation(method = "interactBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I"))
    public int shiftPlacing(World world, int x, int y, int z, Operation<Integer> original) {
        int blockId = original.call(world, x, y, z);
        Block block = Block.BLOCKS[blockId];

        if (!UniTweaks.GAMEPLAY_CONFIG.shiftPlacing || this.minecraft.player.getHand() == null || block == null || CompatHelper.shiftPlacingBlacklist.contains(block)) {
            return blockId;
        }

        return this.minecraft.player.isSneaking() ? 0 : blockId;
    }
}
