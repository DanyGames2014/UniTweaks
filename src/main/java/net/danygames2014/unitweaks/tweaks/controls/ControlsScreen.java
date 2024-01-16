package net.danygames2014.unitweaks.tweaks.controls;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class ControlsScreen extends Screen {
    public Screen parent;
    public String title = "Controls";
    public GameOptions options;

    // Widgets
    public KeybindListWidget keybindListWidget;
    public TextFieldWidget searchTextField;
    public ButtonWidget doneButton;

    public ControlsScreen(Screen parent, GameOptions gameOptions) {
        this.parent = parent;
        this.options = gameOptions;
    }

    @Override
    public void init() {
        this.keybindListWidget = new KeybindListWidget(this, minecraft, options);

        searchTextField = new TextFieldWidget(this, textRenderer, (this.width / 2) - 110, this.height-30, 100, 20, "");
        searchTextField.setMaxLength(32);

        doneButton = new ButtonWidget(1000, (this.width / 2)+10, this.height-30, 100, 20, "Done");
        this.buttons.add(doneButton);

        for (int i = 0; i < options.allKeys.length; i++) {
            KeyBinding item = options.allKeys[i];
            keybindListWidget.keybinds.put(item, new KeybindListWidget.KeybindEntry(new ButtonWidget(i, -1, -1, 100, 20, Keyboard.getKeyName(item.code)), item));
        }
    }

    @Override
    protected void keyPressed(char character, int keyCode) {
        if (searchTextField.focused) {
            searchTextField.keyPressed(character, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        searchTextField.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void buttonClicked(ButtonWidget button) {
        System.out.println(button.id);
        if(button.id == 1000){
            minecraft.setScreen(this.parent);
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        keybindListWidget.render(mouseX, mouseY, delta);

        this.drawTextWithShadow(textRenderer, title, 10, 10, Color.white.getRGB());

        searchTextField.render();
        doneButton.render(minecraft, mouseX, mouseY);
    }
}
