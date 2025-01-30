package net.danygames2014.unitweaks.util.gui;

import net.minecraft.client.gui.widget.ButtonWidget;

import java.util.function.Consumer;

public class CallbackButtonWidget extends ButtonWidget {
    private final Consumer<Void> callback;
    
    public CallbackButtonWidget(int x, int y, int width, int height, String text, Consumer<Void> callback) {
        super(-1, x, y, width, height, text);
        this.callback = callback;
    }
    
    public void doAction() {
        callback.accept(null);
    }
}
