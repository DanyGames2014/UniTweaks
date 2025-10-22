package net.danygames2014.unitweaks.mixin.bugfixes.lastdurabilityfix;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import org.checkerframework.checker.index.qual.LengthOf;
import org.lwjgl.opengl.ARBVertexAttrib64bit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.SERVER)
@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Shadow
    public PlayerEntity player;
    @Shadow
    private ServerWorld world;
    @Unique
    private boolean afterBreakHandled = false;
    
    @Inject(method = "tryBreakBlock", at = @At(value = "HEAD"))
    public void resetFlag(int x, int y, int z, CallbackInfoReturnable<Boolean> cir){
        afterBreakHandled = false;
    }
    
    @Inject(method = "tryBreakBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;onRemoved(Lnet/minecraft/entity/player/PlayerEntity;)V", shift = At.Shift.BEFORE))
    public void callAfterBreak(int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 3) int var4, @Local(ordinal = 4) int var5, @Local(ordinal = 5) int var6) {
        if (!UniTweaks.BUGFIXES_CONFIG.lastDurabilityFix) {
            return;
        }
        
        if (var6 == 1 && this.player.canHarvest(Block.BLOCKS[var4])) {
            Block.BLOCKS[var4].afterBreak(this.world, this.player, x, y, z, var5);
        }
    }
    
    @WrapWithCondition(method = "tryBreakBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;afterBreak(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;IIII)V"))
    public boolean cancelAfterBreak(Block instance, World world, PlayerEntity player, int x, int y, int z, int meta) {
        if (!UniTweaks.BUGFIXES_CONFIG.lastDurabilityFix) {
            return true;
        }
        
        return !afterBreakHandled;
    }
}
