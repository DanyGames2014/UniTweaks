package net.danygames2014.unitweaks.tweaks.controls;

import net.danygames2014.unitweaks.mixin.tweaks.improvedcontrols.EntryListWidgetAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.EntryListWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.resource.language.TranslationStorage;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class KeybindListWidget extends EntryListWidget {
    private final Minecraft minecraft;
    private final GameOptions options;
    private final ControlsScreen parent;
    private final TranslationStorage translations;

    public KeybindListWidget(ControlsScreen parent, Minecraft minecraft, GameOptions options) {
        super(minecraft, parent.width, parent.height, 40, parent.height - 40, 20);
        this.minecraft = minecraft;
        this.options = options;
        this.parent = parent;
        this.translations = TranslationStorage.getInstance();
    }

    public final HashMap<KeyBinding, KeybindEntry> keybinds = new HashMap<>();
    public ArrayList<KeybindEntry> filteredKeybinds = new ArrayList<>();

    @Override
    protected int getEntryCount() {
        return filteredKeybinds.size();
    }

    public void filter() {
        filteredKeybinds.clear();

        String searchTerm = parent.searchTextField.getText().toLowerCase();

        for (var item : keybinds.entrySet()) {
            if(translations.get(item.getKey().translationKey).toLowerCase().contains(searchTerm)){
                filteredKeybinds.add(item.getValue());
            }
        }
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

        filter();

        super.render(mouseX, mouseY, delta);
    }

    @Override
    protected void renderEntry(int index, int x, int y, int l, Tessellator tesselator) {
        KeyBinding keyBinding = filteredKeybinds.get(index).keyBinding;
        KeybindEntry keybindEntry = filteredKeybinds.get(index);

        minecraft.textRenderer.drawWithShadow(translations.get(filteredKeybinds.get(index).keyBinding.translationKey), x, y + 5, Color.white.getRGB());

        ButtonWidget keyButton = keybindEntry.keyButton;
        keyButton.x = x + 100;
        keyButton.y = y;
        keyButton.render(minecraft, mouseX, mouseY);
    }

    public static class KeybindEntry {
        private final ButtonWidget keyButton;
        public KeyBinding keyBinding;

        public KeybindEntry(ButtonWidget keyButton, KeyBinding keyBinding) {
            this.keyButton = keyButton;
            this.keyBinding = keyBinding;
        }

        public ButtonWidget getKeyButton() {
            return keyButton;
        }
    }
}
