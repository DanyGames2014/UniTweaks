package net.danygames2014.unitweaks.mixin.tweaks.improvedcontrols;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.gui.widget.EntryListWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntryListWidget.class)
public class EntryListWidgetMixin {
    @Final
    @Shadow
    public int width;

//    @ModifyConstant(method = "render", constant = @Constant(intValue = 124))
//    public int l(int constant){
//        if(KeybindListWidget.class.isAssignableFrom(this.getClass())){
//            return -(this.width / 2) + this.width - 6;
//        }
//        return constant;
//    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z", remap = false, ordinal = 0))
    public void anInt(int mouseX, int mouseY, float f, CallbackInfo ci, @Local(ordinal = 3) LocalIntRef start, @Local(ordinal = 4) LocalIntRef end) {
//        if(KeybindListWidget.class.isAssignableFrom(this.getClass())){
//            start.set(this.width - 6);
//            end.set(this.width);
//        }
    }
}
