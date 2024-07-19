package net.danygames2014.unitweaks.mixin.tweaks.packetspeedup;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.class_117$4")
public class Connection$4Mixin {
    @ModifyConstant(method = "run", constant = @Constant(longValue = 100L), remap = false)
    public long decreasePacketDelay(long constant) {
        return 2L;
    }
}
