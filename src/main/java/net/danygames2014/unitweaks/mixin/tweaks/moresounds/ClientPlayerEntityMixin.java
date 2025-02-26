package net.danygames2014.unitweaks.mixin.tweaks.moresounds;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ChestSoundsEnum;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {


    @Inject(method = "openChestScreen", at = @At(value = "TAIL"))
    public void chestOpeningSound(Inventory inventory, CallbackInfo ci) {
        if(0 < UniTweaks.FEATURES_CONFIG.moreSoundsChests.ordinal()){
            if (ChestSoundsEnum.DOOR == UniTweaks.FEATURES_CONFIG.moreSoundsChests) {
                PlayerEntity player = (PlayerEntity.class.cast(this));
                player.world.playSound(player, "random.door_open", 0.3F, 1.0F);
            } else if (ChestSoundsEnum.MODERN == UniTweaks.FEATURES_CONFIG.moreSoundsChests) {
                PlayerEntity player = (PlayerEntity.class.cast(this));
                player.world.playSound(player, "unitweaks:random.chestopen", 0.3F, 1.0F);
            } else if (ChestSoundsEnum.BLOCK == UniTweaks.FEATURES_CONFIG.moreSoundsChests) {
                PlayerEntity player = (PlayerEntity.class.cast(this));
                player.world.playSound(player, "step.wood", 0.3F, 1.0F);
            } else if (ChestSoundsEnum.BUTTON == UniTweaks.FEATURES_CONFIG.moreSoundsChests) {
                PlayerEntity player = (PlayerEntity.class.cast(this));
                player.world.playSound(player, "random.click", 0.3F, 0.6F);
            }
        }
    }

    @Inject(method = "closeHandledScreen", at = @At(value = "HEAD"))
    public void chestClosingSound(CallbackInfo ci) {
        if(0 < UniTweaks.FEATURES_CONFIG.moreSoundsChests.ordinal()){
            if (ChestSoundsEnum.DOOR == UniTweaks.FEATURES_CONFIG.moreSoundsChests) {
                PlayerEntity player = (PlayerEntity.class.cast(this));
                if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                    player.world.playSound(player, "random.door_close", 0.3F, 1.0F);
                }
            } else if (ChestSoundsEnum.MODERN == UniTweaks.FEATURES_CONFIG.moreSoundsChests) {
                PlayerEntity player = (PlayerEntity.class.cast(this));
                if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                    player.world.playSound(player, "unitweaks:random.chestclosed", 0.3F, 1.0F);
                }
            } else if (ChestSoundsEnum.BLOCK == UniTweaks.FEATURES_CONFIG.moreSoundsChests) {
                PlayerEntity player = (PlayerEntity.class.cast(this));
                if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                    player.world.playSound(player, "step.wood", 0.3F, 0.8F);
                }
            } else if (ChestSoundsEnum.BUTTON == UniTweaks.FEATURES_CONFIG.moreSoundsChests) {
                PlayerEntity player = (PlayerEntity.class.cast(this));
                if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                    player.world.playSound(player, "random.click", 0.3F, 0.5F);
                }
            }
        }
    }
}
