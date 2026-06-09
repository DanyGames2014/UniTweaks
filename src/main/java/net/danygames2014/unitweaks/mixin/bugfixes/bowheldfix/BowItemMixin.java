package net.danygames2014.unitweaks.mixin.bugfixes.bowheldfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.item.BowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BowItem.class)
public class BowItemMixin extends ItemMixin {
    @Override
    protected void unitweaks$isHandheld(CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.BUGFIXES_CONFIG.bowHeldFix) {
            cir.setReturnValue(true);
        }
    }
}
