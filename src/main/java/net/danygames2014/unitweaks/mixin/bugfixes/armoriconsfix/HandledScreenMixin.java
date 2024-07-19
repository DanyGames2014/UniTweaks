package net.danygames2014.unitweaks.mixin.bugfixes.armoriconsfix;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class HandledScreenMixin extends Screen {
    @Inject(method = "drawSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/TextureManager;bindTexture(I)V", shift = At.Shift.BEFORE), cancellable = true)
    public void addArmorSlotIcon(Slot slot, CallbackInfo ci) {
        // I cant do an instanceof checks because its anonymous
        // I cant do an check if its anonymous because some other mod might add a anonymous slot
        // Therefore i decided to plant this bomb :tf: Nyaaa!
        if(slot.getBackgroundTextureId() == 7355608){
            this.minecraft.textureManager.bindTexture(this.minecraft.textureManager.getTextureId("/assets/unitweaks/textures/gui/armor_icons.png"));
            this.drawTexture(slot.x, slot.y, 0, (slot.id - 5) * 16, 16, 16);
            GL11.glEnable(2896);
            ci.cancel();
        }
    }
}
