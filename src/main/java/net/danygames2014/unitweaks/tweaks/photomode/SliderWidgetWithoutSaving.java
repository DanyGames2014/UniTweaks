package net.danygames2014.unitweaks.tweaks.photomode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.SliderWidget;
import org.lwjgl.opengl.GL11;

public class SliderWidgetWithoutSaving extends SliderWidget {
    public SliderWidgetWithoutSaving(int id, int x, int y, String text, float value) {
        super(id, x, y, null, text, value);
    }

    @Override
    protected void renderBackground(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.visible) {
            if (this.dragging) {
                this.value = (float) (mouseX - (this.x + 4)) / (float) (this.width - 8);
                if (this.value < 0.0F) {
                    this.value = 0.0F;
                }

                if (this.value > 1.0F) {
                    this.value = 1.0F;
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexture(this.x + (int) (this.value * (float) (this.width - 8)), this.y, 0, 66, 4, 20);
            this.drawTexture(this.x + (int) (this.value * (float) (this.width - 8)) + 4, this.y, 196, 66, 4, 20);
        }
    }

    @Override
    public boolean isMouseOver(Minecraft minecraft, int mouseX, int mouseY) {
        if (this.active && (mouseX >= this.x) && (mouseY >= this.y) && (mouseX < (this.x + this.width)) && (mouseY < (this.y + this.height))) {
            this.value = (float) (mouseX - (this.x + 4)) / (float) (this.width - 8);
            if (this.value < 0.0F) {
                this.value = 0.0F;
            }

            if (this.value > 1.0F) {
                this.value = 1.0F;
            }

            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }
}
