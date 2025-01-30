package net.danygames2014.unitweaks.mixin.tweaks.improvedcontrols;

import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.danygames2014.unitweaks.tweaks.controls.KeybindListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeybindListWidget.class)
public class KeybindListWidgetMixin extends EntryListWidgetMixin {
    @Override
    public void anInt(int mouseX, int mouseY, float f, CallbackInfo ci, LocalIntRef start, LocalIntRef end) {
        int scrollBarX = this.width / 2 + 175;
        start.set(scrollBarX);
        end.set(scrollBarX + 6);
    }
}
