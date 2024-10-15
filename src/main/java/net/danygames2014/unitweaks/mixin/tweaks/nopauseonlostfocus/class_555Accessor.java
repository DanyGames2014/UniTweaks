package net.danygames2014.unitweaks.mixin.tweaks.nopauseonlostfocus;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameRenderer.class)
public interface class_555Accessor {
    @Accessor("lastInactiveTime")
    void setLastActiveTime(long value);
}
