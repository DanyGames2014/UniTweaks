package net.danygames2014.unitweaks.mixin.bugfixes.lastdurabilityfix;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.InteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.SingleplayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(SingleplayerInteractionManager.class)
public class SingleplayerInteractionManagerMixin extends InteractionManager {
    @Unique
    private boolean afterBreakHandled = false;

    public SingleplayerInteractionManagerMixin(Minecraft minecraft) {
        super(minecraft);
    }

    @Inject(method = "breakBlock", at = @At(value = "HEAD"))
    public void resetFlag(int x, int y, int z, int direction, CallbackInfoReturnable<Boolean> cir){
        afterBreakHandled = false;
    }
    
    @Inject(method = "breakBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;onRemoved(Lnet/minecraft/entity/player/PlayerEntity;)V", shift = At.Shift.BEFORE))
    public void callAfterBreak(int x, int y, int z, int direction, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 4) int var5, @Local(ordinal = 5) int var6, @Local(ordinal = 6) int var7, @Local(ordinal = 7) int var9) {
        if (!UniTweaks.BUGFIXES_CONFIG.lastDurabilityFix) {
            return;
        }
        
        if (var7 == 1 && var9 == 1) {
            Block.BLOCKS[var5].afterBreak(this.minecraft.world, this.minecraft.player, x, y, z, var6);
            afterBreakHandled = true;
        }
    }
    
    @WrapWithCondition(method = "breakBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;afterBreak(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;IIII)V"))
    public boolean cancelAfterBreak(Block instance, World world, PlayerEntity player, int x, int y, int z, int meta) {
        if (!UniTweaks.BUGFIXES_CONFIG.lastDurabilityFix) {
            return true;
        }
        
        return !afterBreakHandled;
    }
}
