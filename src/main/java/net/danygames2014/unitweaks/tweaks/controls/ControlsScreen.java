package net.danygames2014.unitweaks.tweaks.controls;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.resource.language.TranslationStorage;
import net.modificationstation.stationapi.api.util.Formatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

@Environment(EnvType.CLIENT)
public class ControlsScreen extends Screen {

    // Generaů
    public Screen parent;
    public String title = "Controls";
    public String debugText = "[EXPERIMENTAL]";
    public GameOptions options;
    public final TranslationStorage translations;

    // Keybind Buttons
    public ArrayList<ButtonWidget> keybindButtons;

    // Widgets
    public KeybindListWidget keybindListWidget;
    public TextFieldWidget searchTextField; int searchX; int searchY; int searchWidth; int searchHeight;
    public ButtonWidget doneButton;

    // Selected
    public ButtonWidget selectedButton;

    // Keybinds
    /**
     * ArrayList with all Keybind Entries, this is never rendered directly
     */
    private final ArrayList<KeybindEntry> keybinds = new ArrayList<>();


    /**
     * Filtered ArrayList of keybinds according to the current search term and filtration rules
     */
    public ArrayList<KeybindEntry> filteredKeybinds = new ArrayList<>();


    public ControlsScreen(Screen parent, GameOptions gameOptions) {
        this.parent = parent;
        this.options = gameOptions;
        this.translations = TranslationStorage.getInstance();
        this.keybindButtons = new ArrayList<>(20);
    }


    @SuppressWarnings("unchecked")
    @Override
    public void init() {
        // Keybind List
        this.keybindListWidget = new KeybindListWidget(this, minecraft, options);

        // Search Field
        searchTextField = new TextFieldWidget(this, textRenderer, (this.width / 2) - 110, this.height - 30, 100, 20, "");
        searchTextField.setMaxLength(32);
        searchX = (this.width / 2) - 110;
        searchY = this.height - 30;
        searchWidth = 100;
        searchHeight = 20;

        // Done Button
        doneButton = new ButtonWidget(1000, (this.width / 2) + 10, this.height - 30, 100, 20, "Done");
        this.buttons.add(doneButton);

        // Init Keybinds
        initKeybinds();

        // Initial Keybind Filter
        filterKeybinds();

        // Refresh Key Labels
        refreshKeyLabels();
    }

    /**
     * Initializes keybinds ArrayList with entries of all keybinds
     */
    public void initKeybinds() {
        keybindButtons.clear();
        keybinds.clear();

        for (int i = 0; i < options.allKeys.length; i++) {
            KeyBinding keyBinding = options.allKeys[i];

            // Init Buttons
            ButtonWidget keyButton = new ButtonWidget(i, -1, -1, 100, 20, Keyboard.getKeyName(keyBinding.code));

            keybinds.add(new KeybindEntry(keyButton, keyBinding));
            this.keybindButtons.add(keyButton);
        }
    }


    /**
     * Filters keybinds based on search and filtration rules into the filteredKeybinds ArrayList
     */
    public void filterKeybinds() {
        filteredKeybinds.clear();

        String searchTerm = searchTextField.getText().toLowerCase();

        for (var item : keybinds) {
            if (translations.get(item.keyBinding.translationKey).toLowerCase().contains(searchTerm)) {
                filteredKeybinds.add(item);
            }
        }
    }

    /**
     * Refreshes keybind labels and if there is a conflict with any other key, colors them red
     */
    public void refreshKeyLabels() {
        for (KeybindEntry keybindEntry : filteredKeybinds) {
            Formatting formatting = Formatting.WHITE;

            // Look for conflicts
            for (int j = 0; j < options.allKeys.length; j++) {
                if (!(options.allKeys[j].translationKey.equals(keybindEntry.keyBinding.translationKey)) && (options.allKeys[j].code == keybindEntry.keyBinding.code)) {
                    formatting = Formatting.RED;
                }
            }

            keybindEntry.keyButton.text = formatting + Keyboard.getKeyName(keybindEntry.keyBinding.code) + Formatting.WHITE;
        }
    }


    // Interaction Events
    /**
     * @author calmilamsy
     * Scrolling
     */
    @Override
    public void onMouseEvent() {
        super.onMouseEvent();
        float dWheel = Mouse.getDWheel();
        if (dWheel != 0) {
            keybindListWidget.scroll(-(dWheel / 10));
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);

        selectedButton = null;
        refreshKeyLabels();

        searchTextField.mouseClicked(mouseX, mouseY, button);
        if(button == 1){
            filterKeybinds();
        }

        mouseClickedKeybind(mouseX, mouseY, button);
    }

    public void mouseClickedKeybind(int mouseX, int mouseY, int mouseButton) {
        // Check if left mouse button
        if (mouseButton != 0) {
            return;
        }

        // Out Of Bounds Check
        if (mouseY < 40 || mouseY > (this.height - 40)) {
            return;
        }

        // Loop over filtered keybinds
        for (var keybindEntry : filteredKeybinds) {
            ButtonWidget button = keybindEntry.keyButton;
            if (button.isMouseOver(minecraft, mouseX, mouseY)) {
                minecraft.soundManager.method_2009("random.click", 1.0F, 1.0F);
                buttonClicked(button);
                System.out.println("Clicked button " + button.text + " with id " + button.id);
            }
        }
    }

    @Override
    protected void keyPressed(char character, int keyCode) {
        // If search field focused, send the keyCode there
        if (searchTextField.focused) {
            searchTextField.keyPressed(character, keyCode);
            filterKeybinds();
            return;
        }

        for (var item : filteredKeybinds) {
            if (item.keyButton == selectedButton) {
                // If key is escape, unbind the key
                if (keyCode == Keyboard.KEY_ESCAPE) {
                    item.keyBinding.code = Keyboard.KEY_NONE;
                } else {
                    item.keyBinding.code = keyCode;
                }

                // Binding done, unselect button and refrsh label
                selectedButton = null;
                refreshKeyLabels();

                // Save and Return
                options.save();
                return;
            }
        }
    }

    // Button Clik
    @Override
    protected void buttonClicked(ButtonWidget button) {
        if (button.id == 1000) {
            minecraft.setScreen(this.parent);
        } else if (keybindButtons.contains(button)) {
            selectedButton = button;
            button.text = "> " + button.text + " <";
        }
    }

    // Rendering
    @Override
    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        keybindListWidget.render(mouseX, mouseY, delta);
        this.drawTextWithShadow(textRenderer, title, 10, 10, Color.white.getRGB());
        this.drawTextWithShadow(textRenderer, debugText, 10, 20, Color.white.getRGB());
        searchTextField.render();
        doneButton.render(minecraft, mouseX, mouseY);
    }

    public static class KeybindEntry {
        public final ButtonWidget keyButton;
        public KeyBinding keyBinding;

        public KeybindEntry(ButtonWidget keyButton, KeyBinding keyBinding) {
            this.keyButton = keyButton;
            this.keyBinding = keyBinding;
        }
    }
}

