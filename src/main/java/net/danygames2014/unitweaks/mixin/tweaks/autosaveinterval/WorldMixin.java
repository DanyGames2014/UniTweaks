package net.danygames2014.unitweaks.mixin.tweaks.autosaveinterval;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static net.danygames2014.unitweaks.UniTweaks.GENERAL_CONFIG;

@Mixin(World.class)
public class WorldMixin {

    @Shadow
    protected int saveInterval;

    @WrapOperation(
            method = "<init>(Lnet/minecraft/world/dimension/DimensionData;Ljava/lang/String;JLnet/minecraft/world/dimension/Dimension;)V",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/world/World;saveInterval:I"
            )
    )
    public void test(World instance, int value, Operation<Void> original) {
        this.saveInterval = GENERAL_CONFIG.autosaveInterval * 20;
        UniTweaks.logger.info("Autosave Interval set to " + GENERAL_CONFIG.autosaveInterval + " seconds (" + GENERAL_CONFIG.autosaveInterval * 20 + " ticks)");
    }
//
//    @Inject(method = "method_195", at = @At(value = "HEAD"))
//    public void aaa(boolean arg, class_62 par2, CallbackInfo ci) {
//        System.out.println(this.properties.getName() + " SAVED");
//    }
}
