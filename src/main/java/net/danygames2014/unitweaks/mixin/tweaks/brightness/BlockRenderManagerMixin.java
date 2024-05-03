package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.block.Block;
import net.minecraft.block.RailBlock;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockRenderManager.class)
public abstract class BlockRenderManagerMixin {
    @WrapOperation(method = "renderFlat", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderFlat(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderSmooth", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderSmooth(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderCross(Lnet/minecraft/block/Block;III)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderCross(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderBed", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderBed(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderTorch", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderTorch(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }


    @WrapOperation(method = "renderRepeater", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderRepeater(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderLever", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderLever(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderPistonHead", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderPistonHead(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderFire", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderFire(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderRedstoneDust", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderRedstoneDust(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/RailBlock;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderRail(RailBlock instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderLadder", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderLadder(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderCrop(Lnet/minecraft/block/Block;III)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderCrop(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderFluid(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderFallingBlockEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderFallingBlockEntity(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderCactus(Lnet/minecraft/block/Block;IIIFFF)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderCactus(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }

    @WrapOperation(method = "renderDoor", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brightnessRenderDoor(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }


}
