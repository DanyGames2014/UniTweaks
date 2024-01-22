package net.danygames2014.unitweaks.mixin.tweaks.improvedcontrols;

import net.minecraft.client.gui.widget.EntryListWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author calmilamsy
 */
@Mixin(EntryListWidget.class)
public interface EntryListWidgetAccessor {

    @Accessor("scrollAmount")
    float getScrollAmount();

    @Accessor("scrollAmount")
    void setScrollAmount(float value);
}
