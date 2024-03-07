package net.danygames2014.unitweaks.mixin.tweaks.moresounds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Unique
    private static final Random random = new Random();

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;increaseStat(Lnet/minecraft/stat/Stat;I)V", shift = At.Shift.AFTER))
    public void playSoundOnBreak(int amount, Entity entity, CallbackInfo ci){
        PlayerEntity player = (PlayerEntity) entity;

        player.world.playSound(player, "random.break", 0.5f, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
    }
}
