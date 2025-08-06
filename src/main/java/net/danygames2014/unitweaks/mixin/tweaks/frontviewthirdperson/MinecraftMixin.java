package net.danygames2014.unitweaks.mixin.tweaks.frontviewthirdperson;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.frontviewthirdperson.FrontViewMode;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameOptions;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapOperation(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;thirdPerson:Z", opcode = Opcodes.PUTFIELD))
    public void aVoid(GameOptions gameOptions, boolean value, Operation<Void> original){
        if (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson ==  FrontViewMode.NORMAL) {
            if (gameOptions.thirdPerson) {
                if (ModOptions.frontView) {
                    // Front View -> First Person
                    ModOptions.frontView = false;
                    gameOptions.thirdPerson = false;
                } else {
                    // Third Person -> Front View
                    ModOptions.frontView = true;
                }
            } else {
                // First Person -> Third Person
                gameOptions.thirdPerson = true;
                ModOptions.frontView = false;
            }
            return;
        }
        
        ModOptions.frontView = false;
        original.call(gameOptions, value);
    }
}
