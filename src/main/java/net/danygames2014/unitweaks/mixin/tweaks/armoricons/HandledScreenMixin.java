package net.danygames2014.unitweaks.mixin.tweaks.armoricons;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.interfaces.ArmorSlotDuck;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class HandledScreenMixin extends Screen {
    @Inject(method = "drawSlot", at = @At(value = "HEAD"), cancellable = true)
    public void addArmorSlotIcon(Slot slot, CallbackInfo ci) {
        // I cant do an instanceof checks because its anonymous
        // I cant do an check if its anonymous because some other mod might add a anonymous slot
        // Therefore i decided to plant this bomb :tf: Nyaaa!

        // ^ *Bomb has been defused. Counterterrorists win*

        if (!UniTweaks.BUGFIXES_CONFIG.armorIconsFix) return;

        if (((ArmorSlotDuck) slot).uniTweaks$isArmorSlot() && !slot.hasStack()) {
            int textureId = this.minecraft.textureManager.getTextureId("/assets/unitweaks/textures/gui/armor_icons.png");
            this.minecraft.textureManager.bindTexture(textureId);

            int iconIndex = (slot.id - 5);
            this.drawTexture(slot.x, slot.y, 0, iconIndex * 16, 16, 16);

            ci.cancel();
        }
    }
}
