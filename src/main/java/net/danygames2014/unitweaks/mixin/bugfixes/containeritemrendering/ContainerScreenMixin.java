package net.danygames2014.unitweaks.mixin.bugfixes.containeritemrendering;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.gui.screen.container.ContainerScreen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerScreen.class)
public abstract class ContainerScreenMixin {

    @Shadow
    protected abstract void drawForeground();

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/container/ContainerScreen;drawForeground()V"))
    public void cancelDrawForeground(ContainerScreen instance) {
        if (!UniTweaks.BUGFIXES_CONFIG.itemstackRenderingFix) {
            drawForeground();
        }
    }

    @Inject(method = "render", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/entity/player/ClientPlayerEntity;inventory:Lnet/minecraft/entity/player/PlayerInventory;"))
    public void injectDrawForeground(int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (UniTweaks.BUGFIXES_CONFIG.itemstackRenderingFix) {
            drawForeground();
        }
    }
}
