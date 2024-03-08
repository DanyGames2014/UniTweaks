package net.danygames2014.unitweaks.mixin.tweaks.moresounds;

import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Unique
    private static final Random random = new Random();

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;increaseStat(Lnet/minecraft/stat/Stat;I)V", shift = At.Shift.AFTER))
    public void playSoundOnToolBreak(int amount, Entity entity, CallbackInfo ci){
        if(UniTweaks.TWEAKS_CONFIG.moreSounds){
            PlayerEntity player = (PlayerEntity) entity;

            player.world.playSound(player, "random.break", 1.0f, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
        }
    }

    @Inject(method = "damage", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/item/ItemStack;count:I", ordinal = 0, shift = At.Shift.BEFORE))
    public void playSoundOnArmorBreak(int amount, Entity entity, CallbackInfo ci){
        if(UniTweaks.TWEAKS_CONFIG.moreSounds){
            if(this.getItem() instanceof ArmorItem){
                if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
                    Minecraft minecraft = (Minecraft)FabricLoader.getInstance().getGameInstance();
                    PlayerEntity player = minecraft.player;
                    player.world.playSound(player, "random.break", 1.0f, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
                }
            }
        }
    }
}
