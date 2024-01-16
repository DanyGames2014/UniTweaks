package net.danygames2014.unitweaks.mixin.tweaks.punchtntignite;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TntBlock.class)
public class TntBlockMixin extends Block {
    public TntBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        if (UniTweaks.OLD_FEATURES_CONFIG.punchTntToIgnite) {
            TntEntity tntEntity = new TntEntity(world, (float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
            world.method_210(tntEntity);
            world.playSound(tntEntity, "random.fuse", 1.0F, 1.0F);
        }
    }
}
