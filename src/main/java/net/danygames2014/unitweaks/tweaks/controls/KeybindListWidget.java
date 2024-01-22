package net.danygames2014.unitweaks.tweaks.controls;

import net.danygames2014.unitweaks.mixin.tweaks.improvedcontrols.EntryListWidgetAccessor;
import net.danygames2014.unitweaks.tweaks.controls.ControlsScreen.KeybindEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.Tessellator;

import java.awt.*;

public class KeybindListWidget extends EntryListWidget {
    private final Minecraft minecraft;
    private final ControlsScreen parent;

    public KeybindListWidget(ControlsScreen parent, Minecraft minecraft, GameOptions options) {
        super(minecraft, parent.width, parent.height, 40, parent.height - 40, 20);
        this.minecraft = minecraft;
        this.parent = parent;
    }

    @Override
    protected int getEntryCount() {
        return parent.filteredKeybinds.size();
    }

    /**
     * @author calmilamsy
     */
    public void scroll(float value) {
        EntryListWidgetAccessor baseAccessor = ((EntryListWidgetAccessor) this);
        baseAccessor.setScrollAmount(baseAccessor.getScrollAmount() + value);
    }

    @Override
    protected void entryClicked(int index, boolean doubleClick) {

    }

    @Override
    protected boolean isSelectedEntry(int index) {
        return false;
    }

    @Override
    protected void renderBackground() {
    }

    private int mouseX;
    private int mouseY;

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;

//        parent.filter();

        super.render(mouseX, mouseY, delta);
    }

    @Override
    protected void renderEntry(int index, int x, int y, int l, Tessellator tesselator) {
        KeybindEntry keybindEntry = parent.filteredKeybinds.get(index);

        minecraft.textRenderer.drawWithShadow(parent.translations.get(parent.filteredKeybinds.get(index).keyBinding.translationKey), x, y + 5, Color.white.getRGB());

        ButtonWidget keyButton = keybindEntry.keyButton;
        keyButton.x = x + 100;
        keyButton.y = y;
        keyButton.render(minecraft, mouseX, mouseY);
    }
}
