package net.danygames2014.unitweaks.tweaks.rawinput;

import java.awt.*;

public class RawMouseHelper extends net.minecraft.client.Mouse {

    public RawMouseHelper(Component parent) {
        super(parent);
    }

    @Override
    public void poll() {
        this.deltaX = RawInputHandler.dx;
        RawInputHandler.dx = 0;
        this.deltaY = -RawInputHandler.dy;
        RawInputHandler.dy = 0;
    }

    @Override
    public void lockCursor() {
        super.lockCursor();
        RawInputHandler.dx = 0;
        RawInputHandler.dy = 0;
    }
}
