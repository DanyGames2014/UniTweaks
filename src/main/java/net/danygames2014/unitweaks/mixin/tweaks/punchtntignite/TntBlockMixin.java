package net.danygames2014.unitweaks.mixin.tweaks.punchtntignite;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.TntBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TntBlock.class)
public class TntBlockMixin extends Block {
    public TntBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        if (!world.isRemote) {
            if (UniTweaks.OLD_FEATURES_CONFIG.punchTntToIgnite) {
                TntEntity tntEntity = new TntEntity(world, (float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
                world.spawnEntity(tntEntity);
                world.playSound(tntEntity, "random.fuse", 1.0F, 1.0F);
            }
        }
    }

    @WrapWithCondition(method = "onMetadataChange", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/TntBlock;dropStack(Lnet/minecraft/world/World;IIILnet/minecraft/item/ItemStack;)V"))
    public boolean cancelTntDrop(TntBlock instance, World world, int x, int y, int z, ItemStack itemStack) {
        return !UniTweaks.OLD_FEATURES_CONFIG.punchTntToIgnite;
    }
}
