package net.danygames2014.unitweaks.mixin.tweaks.nopauseonlostfocus;

import net.minecraft.class_555;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_555.class)
public interface class_555Accessor {
    @Accessor("field_2334")
    void setLastActiveTime(long value);
}
