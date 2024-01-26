package net.danygames2014.unitweaks.mixin.tweaks.laddergaps;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "method_932", at = @At("HEAD"), cancellable = true)
    public void allowLadderGaps(CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.OLD_FEATURES_CONFIG.ladderGaps) {
            int x = MathHelper.floor(this.x);
            int footY = MathHelper.floor(this.boundingBox.minY);
            int z = MathHelper.floor(this.z);

            cir.setReturnValue((this.world.getBlockId(x, footY, z) == Block.LADDER.id) || (this.world.getBlockId(x, footY + 1, z) == Block.LADDER.id));
        }
    }
}
