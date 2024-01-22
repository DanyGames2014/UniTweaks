package net.danygames2014.unitweaks.mixin.tweaks.shiftplacing;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.class_70;
import net.minecraft.class_73;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_70.class)
public class class_70Mixin {
    @Shadow private class_73 field_2310;

    @Shadow public PlayerEntity field_2309;

    @Redirect(method = "method_1832", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I"))
    public int shiftPlacing(World world, int x, int y, int z){
        if(this.field_2309.method_1373() && !(this.field_2309.getHand() == null) && UniTweaks.GAMEPLAY_CONFIG.shiftPlacing){
            return 0;
        }
        return world.getBlockId(x, y, z);
    }
}
