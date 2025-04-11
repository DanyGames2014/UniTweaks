package net.danygames2014.unitweaks.mixin.bugfixes.containeritemrendering;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {

    @Shadow
    protected abstract void drawForeground();

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;drawForeground()V"))
    public void cancelDrawForeground(HandledScreen instance, Operation<Void> original) {
        if (!UniTweaks.BUGFIXES_CONFIG.itemstackRenderingFix) {
            original.call(instance);
        }
    }

    @Inject(method = "render", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/entity/player/ClientPlayerEntity;inventory:Lnet/minecraft/entity/player/PlayerInventory;"))
    public void injectDrawForeground(int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (UniTweaks.BUGFIXES_CONFIG.itemstackRenderingFix) {
            drawForeground();
        }
    }
}
