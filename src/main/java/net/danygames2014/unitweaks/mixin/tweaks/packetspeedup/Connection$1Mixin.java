package net.danygames2014.unitweaks.mixin.tweaks.packetspeedup;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.network.Connection$1")
public class Connection$1Mixin {
    @ModifyConstant(method = "run", constant = @Constant(longValue = 100L))
    public long decreasePacketDelay(long constant){
        return 2L;
    }
}
