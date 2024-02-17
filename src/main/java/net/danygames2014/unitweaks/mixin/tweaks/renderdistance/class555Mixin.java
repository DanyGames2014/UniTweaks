package net.danygames2014.unitweaks.mixin.tweaks.renderdistance;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.class_555;
import net.minecraft.client.option.GameOptions;
import org.lwjgl.input.Keyboard;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_555.class)
public class class555Mixin {
    @Shadow
    private float field_2350;

    @Inject(
            method = "method_1840",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/class_555;field_2350:F",
                    shift = At.Shift.AFTER
            )
    )
    public void overrideFarPlaneDistance(float i, int par2, CallbackInfo ci) {
        this.field_2350 = (16 * ModOptions.getRenderDistanceChunks()) * ModOptions.getFogMultiplier();
    }

    // EXPERIMENT
//    @Redirect(method = "method_1837", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;viewDistance:I"))
//    public int test(GameOptions instance){
//        return 2;
//    }

    @Redirect(method = "method_1841", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;viewDistance:I"))
    public int skyFog(GameOptions instance){
        return ModOptions.getRenderDistanceChunks() > 7 ? 0 : 3;
    }

//    @Redirect(method = "method_1852", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;viewDistance:I"))
//    public int test3(GameOptions instance){
//        return 2;
//    }
}
