package net.danygames2014.unitweaks.mixin.tweaks.cleartextfield;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
    @Shadow
    public boolean enabled;

    @Shadow
    @Final
    private int x;

    @Shadow
    @Final
    private int width;

    @Shadow
    @Final
    private int y;

    @Shadow
    @Final
    private int height;

    @Shadow
    private String text;

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    public void clearTextOnRightClick(int mouseX, int mouseY, int button, CallbackInfo ci) {
        if (enabled && mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height && button == 1) {
            text = "";
        }

    }
}
