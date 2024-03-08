package net.danygames2014.unitweaks.mixin.tweaks.moresounds;

import net.danygames2014.unitweaks.UniTweaks;
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


    @Inject(method = "method_486", at = @At(value = "TAIL"))
    public void chestOpeningSound(Inventory inventory, CallbackInfo ci) {
        if(UniTweaks.TWEAKS_CONFIG.moreSounds){
            PlayerEntity player = (PlayerEntity.class.cast(this));
            player.world.playSound(player, "random.chestopen", 0.3F, 1.0F);
        }
    }

    @Inject(method = "closeScreen", at = @At(value = "HEAD"))
    public void chestClosingSound(CallbackInfo ci) {
        if(UniTweaks.TWEAKS_CONFIG.moreSounds){
            PlayerEntity player = (PlayerEntity.class.cast(this));
            if (player.container instanceof GenericContainerScreenHandler) {
                player.world.playSound(player, "random.chestclosed", 0.3F, 1.0F);
            }
        }
    }
}
